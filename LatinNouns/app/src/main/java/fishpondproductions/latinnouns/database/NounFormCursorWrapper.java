package fishpondproductions.latinnouns.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import fishpondproductions.latinnouns.NounForm;

/**
 * Created by ericianni on 4/8/17.
 */

public class NounFormCursorWrapper extends CursorWrapper {

    NounFormCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public NounForm getNounForm() {

        int formId = getInt(getColumnIndex(DBSchema.NounFormsTable.Cols.NFID));
        int conceptID = getInt(getColumnIndex(DBSchema.NounFormsTable.Cols.NCID));
        String form = getString(getColumnIndex(DBSchema.NounFormsTable.Cols.FORM));

        String pp1 = getString(getColumnIndex(DBSchema.NounsTable.Cols.PP1));
        String pp2 = getString(getColumnIndex(DBSchema.NounsTable.Cols.PP2));
        String gender = getString(getColumnIndex(DBSchema.NounsTable.Cols.GENDER));
        int decl = getInt(getColumnIndex(DBSchema.NounsTable.Cols.DECL));
        String nCase = getString(getColumnIndex(DBSchema.NounConceptsTable.Cols.CASE));
        String number = getString(getColumnIndex(DBSchema.NounConceptsTable.Cols.NUM));

        NounForm nounForm = new NounForm();
        nounForm.setFormId(formId);
        nounForm.setConceptId(conceptID);
        nounForm.setForm(form);
        nounForm.setPP1(pp1);
        nounForm.setPP2(pp2);
        nounForm.setGender(gender);
        nounForm.setDecl(decl);
        nounForm.setCase(nCase);
        nounForm.setNumber(number);

        return nounForm;
    }
}
