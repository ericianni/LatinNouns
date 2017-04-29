package fishpondproductions.latinnouns;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

import fishpondproductions.latinnouns.database.DBSchema;

/**
 * Created by ericianni on 4/12/17.
 */

public class CustomSM2 {
    private static final String TAG = "CustomSM2";

    private static final double Q_MAX = 5.0;
    private static final double Q_MIN = 0.0;
    private static final double EF_MIN = 1.3;
    private static final int INTERVAL_MIN = 1;

    public static void updateNounMastery(NounMastery nounMastery, double q) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int interval;
        int n = nounMastery.getN();
        if (q < 3.0) {
            n = 1;
            interval = INTERVAL_MIN;
        } else {
            double newEF = calculateNewEF(nounMastery, q);
            nounMastery.setEF(newEF);
            interval = calculateNewInterval(nounMastery);
            n += 1;
        }
        nounMastery.setN(n);
        nounMastery.setInterval(interval);
        nounMastery.setLastReviewed(date);
        Log.d(TAG, "new values n: " + n + " interval: " + interval + " date: " + date);
        LandingPageFragment.dbHelper.updateNounMasteryEF(nounMastery.getConceptId(), nounMastery.getEF());
        LandingPageFragment.dbHelper.updateNounMasteryInterval(nounMastery.getConceptId(), nounMastery.getInterval());
        LandingPageFragment.dbHelper.updateNounMasteryN(nounMastery.getConceptId(), nounMastery.getN());
        LandingPageFragment.dbHelper.updateNounMasteryLastReviewed(nounMastery.getConceptId(), nounMastery.getLastReviewed());
    }

    private static double calculateNewEF(NounMastery nounMastery, double q) {
        double newEF = nounMastery.getEF() + (0.1 - (Q_MAX - q) * (0.08 + (Q_MAX - q) * 0.02));
        if (newEF < EF_MIN) {
            newEF = EF_MIN;
        }
        return newEF;
    }

    private static int calculateNewInterval(NounMastery nounMastery) {
        int interval = 1;
        if (nounMastery.getN() == 2) {
            interval = 6;
        } else if (nounMastery.getN() > 2) {
            //need to handle early review
            try {
                long lastReviewed = new SimpleDateFormat("yyyy-MM-dd").parse(nounMastery.getLastReviewed()).getTime();
                long today = new Date().getTime();
                int numDays = (int) (today - lastReviewed) / (1000 * 60 * 60 * 24);
                if ( numDays < 1) {
                    numDays = 1;
                }
                interval = (int) Math.round(numDays * nounMastery.getEF());
            } catch (ParseException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        return interval;
    }

}
