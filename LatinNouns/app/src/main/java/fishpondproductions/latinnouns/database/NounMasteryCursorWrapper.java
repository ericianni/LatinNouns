package fishpondproductions.latinnouns.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import fishpondproductions.latinnouns.NounMastery;

/**
 * Created by ericianni on 4/8/17.
 */

public class NounMasteryCursorWrapper extends CursorWrapper {

    public NounMasteryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public NounMastery getNounMastery() {
        int masteryId = getInt(getColumnIndex(DBSchema.NounMasteriesTable.Cols.NMID));
        int conceptId = getInt(getColumnIndex(DBSchema.NounMasteriesTable.Cols.NCID));
        int n = getInt(getColumnIndex(DBSchema.NounMasteriesTable.Cols.N));
        double ef = getDouble(getColumnIndex(DBSchema.NounMasteriesTable.Cols.EF));
        int interval = getInt(getColumnIndex(DBSchema.NounMasteriesTable.Cols.INTERVAL));
        String lastReviewed = getString(getColumnIndex(DBSchema.NounMasteriesTable.Cols.LASTREVIEWED));

        NounMastery nounMastery = new NounMastery();
        nounMastery.setMasteryId(masteryId);
        nounMastery.setConceptId(conceptId);
        nounMastery.setN(n);
        nounMastery.setEF(ef);
        nounMastery.setInterval(interval);
        nounMastery.setLastReviewed(lastReviewed);

        return nounMastery;
    }

}
