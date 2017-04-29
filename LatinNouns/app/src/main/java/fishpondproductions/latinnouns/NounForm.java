package fishpondproductions.latinnouns;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by ericianni on 4/8/17.
 */

public class NounForm implements Parcelable{

    private final String TAG = "NounForm";

    private int mFormId;
    private int mConceptId;
    private String mForm;
    private String mPP1;
    private String mPP2;
    private String mGender;
    private int mDecl;
    private String mCase;
    private String mNumber;

    public NounForm() {

    }

    public int getFormId() {
        return mFormId;
    }

    public void setFormId(int formId) {
        mFormId = formId;
    }

    public int getConceptId() {
        return mConceptId;
    }

    public void setConceptId(int conceptId) {
        mConceptId = conceptId;
    }

    public String getForm() {
        return mForm;
    }

    public void setForm(String form) {
        mForm = form;
    }

    public String getPP1() {
        return mPP1;
    }

    public void setPP1(String PP1) {
        mPP1 = PP1;
    }

    public String getPP2() {
        return mPP2;
    }

    public void setPP2(String PP2) {
        mPP2 = PP2;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public int getDecl() {
        return mDecl;
    }

    public void setDecl(int decl) {
        mDecl = decl;
    }

    public String getCase() {
        return mCase;
    }

    public void setCase(String aCase) {
        mCase = aCase;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.v(TAG, "WritetoParcel " + i);
        parcel.writeInt(mFormId);
        parcel.writeInt(mConceptId);
        parcel.writeString(mForm);
        parcel.writeString(mPP1);
        parcel.writeString(mPP2);
        parcel.writeString(mGender);
        parcel.writeInt(mDecl);
        parcel.writeString(mCase);
        parcel.writeString(mNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public NounForm createFromParcel(Parcel in) {
            return new NounForm(in);
        }

        @Override
        public NounForm[] newArray(int size) {
            return new NounForm[size];
        }
    };

    private NounForm(Parcel in) {
        mFormId = in.readInt();
        mConceptId = in.readInt();
        mForm = in.readString();
        mPP1 = in.readString();
        mPP2 = in.readString();
        mGender = in.readString();
        mDecl = in.readInt();
        mCase = in.readString();
        mNumber = in.readString();
    }


}
