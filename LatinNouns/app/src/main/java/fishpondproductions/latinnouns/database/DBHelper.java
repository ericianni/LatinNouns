package fishpondproductions.latinnouns.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fishpondproductions.latinnouns.NounForm;
import fishpondproductions.latinnouns.NounMastery;

/**
 * Created by ericianni on 4/8/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    final String TAG = "DBHelper";
    private static String DB_PATH = null;
    private static final String DB_NAME = DBSchema.NAME;
    private static final int VERSION = 1;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.mContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        //Log.e(TAG, this.DB_PATH);
    }

    public void createDatabase() {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                Log.e(TAG, "Error copy database: " + e.getMessage());
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Log.e(TAG, "Error on checkDatabase: " + e.getMessage());
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDatabase() throws IOException {
        InputStream input = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream output = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();;
        output.close();
        input.close();
    }

    public void openDatabase() {
        String path = DB_PATH + DB_NAME;
        this.mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDatabase();
            } catch (IOException e) {
                Log.e(TAG, "Error on Upgrade: " + e.getMessage());
            }
        }
    }

    public List<NounMastery> selectNounConceptMasteriesByParts(String nCase,
                                                               String number,
                                                               String gender,
                                                               int decl) {
               String tableNames = DBSchema.NounConceptsTable.NAME + "," + DBSchema.NounMasteriesTable.NAME;
        String[] cols = {
                DBSchema.NounMasteriesTable.NAME + "." + DBSchema.NounMasteriesTable.Cols.NCID,
                DBSchema.NounMasteriesTable.Cols.NMID,
                DBSchema.NounMasteriesTable.Cols.N,
                DBSchema.NounMasteriesTable.Cols.EF,
                DBSchema.NounMasteriesTable.Cols.INTERVAL,
                DBSchema.NounMasteriesTable.Cols.LASTREVIEWED};
        String whereClause =
                DBSchema.NounConceptsTable.NAME + "." + DBSchema.NounConceptsTable.Cols.NCID +
                " = " +
                DBSchema.NounMasteriesTable.NAME + "." + DBSchema.NounMasteriesTable.Cols.NCID +
                " AND " +
                DBSchema.NounConceptsTable.NAME + "." + DBSchema.NounConceptsTable.Cols.CASE +
                " == ? AND " +
                DBSchema.NounConceptsTable.NAME + "." + DBSchema.NounConceptsTable.Cols.NUM +
                " == ? AND " +
                DBSchema.NounConceptsTable.NAME + "." + DBSchema.NounConceptsTable.Cols.GENDER +
                " == ? AND " +
                DBSchema.NounConceptsTable.NAME + "." + DBSchema.NounConceptsTable.Cols.DECL +
                " == ?";
        String[] whereArgs = {nCase, number, gender, "" + decl};

        NounMasteryCursorWrapper cursor = new NounMasteryCursorWrapper(mDatabase.query(
                tableNames,
                cols,
                whereClause,
                whereArgs,
                null,
                null,
                null
        ));

        List<NounMastery> nounMasteries = new ArrayList<>();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                NounMastery nounMastery = cursor.getNounMastery();
                //Log.d(TAG, nounMastery.getMasteryId() + " " + nounMastery.getConceptId());
                nounMasteries.add(nounMastery);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return nounMasteries;
    }

    public List<NounForm> selectNounFormsByNCID(int ncId, int limit) {

        String tableNames = DBSchema.NounFormsTable.NAME + "," + DBSchema.NounsTable.NAME + "," + DBSchema.NounConceptsTable.NAME;
        String[] cols = {
                DBSchema.NounFormsTable.Cols.FORM,
                DBSchema.NounFormsTable.Cols.NFID,
                DBSchema.NounFormsTable.NAME + "." + DBSchema.NounFormsTable.Cols.NCID,
                DBSchema.NounsTable.Cols.PP1,
                DBSchema.NounsTable.Cols.PP2,
                DBSchema.NounsTable.NAME + "." + DBSchema.NounsTable.Cols.GENDER,
                DBSchema.NounsTable.NAME + "." + DBSchema.NounsTable.Cols.DECL,
                DBSchema.NounConceptsTable.Cols.CASE,
                DBSchema.NounConceptsTable.Cols.NUM};
        String whereClause =
                DBSchema.NounFormsTable.NAME + "." + DBSchema.NounFormsTable.Cols.NID +
                " = " +
                DBSchema.NounsTable.NAME + "." + DBSchema.NounsTable.Cols.NID +
                " AND " +
                DBSchema.NounFormsTable.NAME + "." + DBSchema.NounFormsTable.Cols.NCID +
                " == ?" +
                " AND " +
                DBSchema.NounConceptsTable.NAME + "." + DBSchema.NounConceptsTable.Cols.NCID +
                " == ? ";
        String[] whereArgs = {"" + ncId, "" + ncId};
        NounFormCursorWrapper cursor = new NounFormCursorWrapper(mDatabase.query(
                tableNames,
                cols,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                "" + limit
        ));
        List<NounForm> nounForms = new ArrayList<>();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NounForm nounForm = cursor.getNounForm();
                //Log.d(TAG, nounForm.getForm());
                nounForms.add(nounForm);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return nounForms;
    }

    public void updateNounMasteryInterval(int nmId, double interval) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.NounMasteriesTable.Cols.INTERVAL, interval);
        long result = mDatabase.update(DBSchema.NounMasteriesTable.NAME,
                values,
                DBSchema.NounMasteriesTable.Cols.NMID + " == " + nmId, //where clause
                null);
        Log.d(TAG, "updateNounMasteryInterval result: " + result);
    }

    public void updateNounMasteryEF(int nmId, double ef) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.NounMasteriesTable.Cols.EF, ef);
        long result = mDatabase.update(DBSchema.NounMasteriesTable.NAME,
                values,
                DBSchema.NounMasteriesTable.Cols.NMID + " == " + nmId, //where clause
                null);
        Log.d(TAG, "updateNounMasteryEF result: " + result);
    }

    public void updateNounMasteryN(int nmId, int n) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.NounMasteriesTable.Cols.N, n);
        long result = mDatabase.update(DBSchema.NounMasteriesTable.NAME,
                values,
                DBSchema.NounMasteriesTable.Cols.NMID + " == " + nmId, //where clause
                null);
        Log.d(TAG, "updateNounMasteryN result: " + result);
    }

    public void updateNounMasteryLastReviewed(int nmId, String lastReviewed) {
        ContentValues values = new ContentValues();
        values.put(DBSchema.NounMasteriesTable.Cols.LASTREVIEWED, lastReviewed);
        long result = mDatabase.update(DBSchema.NounMasteriesTable.NAME,
                values,
                DBSchema.NounMasteriesTable.Cols.NMID + " == " + nmId, //where clause
                null);
        Log.d(TAG, "updateNounMasteryLastReviewed result: " + result);
    }

    public List<NounMastery> loadConcepts(String[] cases,
                                           String[] numbers,
                                           String[] genders,
                                           int[] decls) {
        List<NounMastery> nounMasteries = new ArrayList<>();
        for (String nCase : cases) {
            for (String number : numbers) {
                for (String gender : genders) {
                    for (int decl : decls) {
                        nounMasteries.addAll(selectNounConceptMasteriesByParts(nCase,
                                number,
                                gender,
                                decl));
                    }
                }
            }
        }
        return nounMasteries;
    }

    public List<NounForm> loadForms(int conceptId, int limit) {
        return selectNounFormsByNCID(conceptId, limit);
    }

}
