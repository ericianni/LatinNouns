package fishpondproductions.latinnouns;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by ericianni on 4/4/17.
 */

public class NounMastery implements Parcelable {

    private final String TAG = "NounMastery";

    private int mMasteryId;
    private int mConceptId;
    private int mN;
    private double mEF;
    private int mInterval;
    private String mLastReviewed;

    public NounMastery() {

    }
    public int getMasteryId() {
        return mMasteryId;
    }

    public void setMasteryId(int masteryId) {
        mMasteryId = masteryId;
    }

    public int getConceptId() {
        return mConceptId;
    }

    public void setConceptId(int conceptId) {
        mConceptId = conceptId;
    }

    public int getN() {
        return mN;
    }

    public void setN(int n) {
        mN = n;
    }

    public double getEF() {
        return mEF;
    }

    public void setEF(double EF) {
        mEF = EF;
    }

    public int getInterval() {
        return mInterval;
    }

    public void setInterval(int interval) {
        mInterval = interval;
    }

    public String getLastReviewed() {
        return mLastReviewed;
    }

    public void setLastReviewed(String lastReviewed) {
        mLastReviewed = lastReviewed;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.v(TAG, "writeToParcel " + i);
        parcel.writeInt(mMasteryId);
        parcel.writeInt(mConceptId);
        parcel.writeInt(mN);
        parcel.writeDouble(mEF);
        parcel.writeInt(mInterval);
        parcel.writeString(mLastReviewed);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public NounMastery createFromParcel(Parcel in) {
            return new NounMastery(in);
        }

        @Override
        public NounMastery[] newArray(int size) {
            return new NounMastery[size];
        }
    };

    private NounMastery(Parcel in) {
        mMasteryId = in.readInt();
        mConceptId = in.readInt();
        mN = in.readInt();
        mEF = in.readDouble();
        mInterval = in.readInt();
        mLastReviewed = in.readString();
    }


}
