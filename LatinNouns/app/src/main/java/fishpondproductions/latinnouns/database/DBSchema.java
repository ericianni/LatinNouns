package fishpondproductions.latinnouns.database;

/**
 * Created by ericianni on 4/8/17.
 */

public class DBSchema {
    static final String NAME = "LatinNouns.db";

    static final class NounsTable {
        static final String NAME = "nouns";
        static final class Cols {
            static final String NID = "nId";
            static final String PP1 = "pp1";
            static final String PP2 = "pp2";
            static final String GENDER = "gender";
            static final String DECL = "decl";
        }
    }

    static final class NounConceptsTable {
        static final  String NAME = "noun_concepts";
        static final class Cols {
            static final String NCID = "ncId";
            static final String CASE = "nCase";
            static final String NUM = "num";
            static final String GENDER = "gender";
            static final String DECL = "decl";
        }
    }

    static final class NounFormsTable {
        static final String NAME = "noun_forms";
        static final class Cols {
            static final String NFID = "nfId";
            static final String FORM = "form";
            static final String NCID = "ncId";
            static final String NID = "nId";
        }
    }

    static final class NounMasteriesTable {
        static final String NAME = "noun_masteries";
        static final class Cols {
            static final String NMID = "nmId";
            static final String NCID = "ncId";
            static final String EF = "ef";
            static final String INTERVAL = "interval";
            static final String N = "n";
            static final String LASTREVIEWED = "lastReviewed";
        }
    }
}
