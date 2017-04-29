package fishpondproductions.latinnouns;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fishpondproductions.latinnouns.database.DBHelper;

/**
 * Created by ericianni on 4/8/17.
 */

public class LandingPageFragment extends Fragment{

    public static final String DECLINE_NOUN_TAG = "decline_noun_tag";
    public static final String CUSTOM_QUIZ_TAG = "custom_quiz_tag";
    public static final String QUIZ_TAG = "quiz_tag";

    private final String TAG = "LandingPageFragment";
    private final int REQUEST_QUIZ_COMPLETE = 0;
    private final int NUM_QUESTIONS_PER_CONCEPT = 10;
    public static DBHelper dbHelper;

    View mLandingPageView;

    //Pop up window
    View mInstructionsView;
    Button mInstructionsButton;
    PopupWindow mInstructionsPopupWindow;
    View mAboutView;
    Button mAboutButton;
    PopupWindow mAboutPopupWindow;



    //Custom Practice Button
    Button mCustomPracticeButton;

    //1st Decl Masc/Fem Buttons
    Button mNomSing1stButton;
    Button mGenSing1stButton;
    Button mDatSing1stButton;
    Button mAccSing1stButton;
    Button mAblSing1stButton;
    Button mVocSing1stButton;
    Button mNomPlur1stButton;
    Button mGenPlur1stButton;
    Button mDatPlur1stButton;
    Button mAccPlur1stButton;
    Button mAblPlur1stButton;
    Button mVocPlur1stButton;

    //2nd Decl Masc Buttons
    Button mNomSing2ndMascButton;
    Button mGenSing2ndMascButton;
    Button mDatSing2ndMascButton;
    Button mAccSing2ndMascButton;
    Button mAblSing2ndMascButton;
    Button mVocSing2ndMascButton;
    Button mNomPlur2ndMascButton;
    Button mGenPlur2ndMascButton;
    Button mDatPlur2ndMascButton;
    Button mAccPlur2ndMascButton;
    Button mAblPlur2ndMascButton;
    Button mVocPlur2ndMascButton;

    //2nd Decl Neut Buttons
    Button mNomSing2ndNeutButton;
    Button mGenSing2ndNeutButton;
    Button mDatSing2ndNeutButton;
    Button mAccSing2ndNeutButton;
    Button mAblSing2ndNeutButton;
    Button mVocSing2ndNeutButton;
    Button mNomPlur2ndNeutButton;
    Button mGenPlur2ndNeutButton;
    Button mDatPlur2ndNeutButton;
    Button mAccPlur2ndNeutButton;
    Button mAblPlur2ndNeutButton;
    Button mVocPlur2ndNeutButton;

    //3rd Decl Masc/Fem Buttons
    Button mNomSing3rdMascFemButton;
    Button mGenSing3rdMascFemButton;
    Button mDatSing3rdMascFemButton;
    Button mAccSing3rdMascFemButton;
    Button mAblSing3rdMascFemButton;
    Button mVocSing3rdMascFemButton;
    Button mNomPlur3rdMascFemButton;
    Button mGenPlur3rdMascFemButton;
    Button mDatPlur3rdMascFemButton;
    Button mAccPlur3rdMascFemButton;
    Button mAblPlur3rdMascFemButton;
    Button mVocPlur3rdMascFemButton;

    //3rd Decl Neut Buttons
    Button mNomSing3rdNeutButton;
    Button mGenSing3rdNeutButton;
    Button mDatSing3rdNeutButton;
    Button mAccSing3rdNeutButton;
    Button mAblSing3rdNeutButton;
    Button mVocSing3rdNeutButton;
    Button mNomPlur3rdNeutButton;
    Button mGenPlur3rdNeutButton;
    Button mDatPlur3rdNeutButton;
    Button mAccPlur3rdNeutButton;
    Button mAblPlur3rdNeutButton;
    Button mVocPlur3rdNeutButton;

    //4th Decl Buttons
    Button mNomSing4thButton;
    Button mGenSing4thButton;
    Button mDatSing4thButton;
    Button mAccSing4thButton;
    Button mAblSing4thButton;
    Button mVocSing4thButton;
    Button mNomPlur4thButton;
    Button mGenPlur4thButton;
    Button mDatPlur4thButton;
    Button mAccPlur4thButton;
    Button mAblPlur4thButton;
    Button mVocPlur4thButton;

    //5th Decl Buttons
    Button mNomSing5thButton;
    Button mGenSing5thButton;
    Button mDatSing5thButton;
    Button mAccSing5thButton;
    Button mAblSing5thButton;
    Button mVocSing5thButton;
    Button mNomPlur5thButton;
    Button mGenPlur5thButton;
    Button mDatPlur5thButton;
    Button mAccPlur5thButton;
    Button mAblPlur5thButton;
    Button mVocPlur5thButton;

    public static LandingPageFragment newInstance() {
        return new LandingPageFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLandingPageView = inflater.inflate(R.layout.landing_page, container, false);
        dbHelper = new DBHelper(getActivity());
        dbHelper.createDatabase();
        try {
            dbHelper.openDatabase();;
        } catch (SQLiteException e) {
            Log.e(TAG, "Failed to open Database: " + e.getMessage());
        }
        //Set OnClickListener for Custom Practice
        mCustomPracticeButton = (Button) mLandingPageView.findViewById(R.id.landing_page_custom_practice_button);
        mCustomPracticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                Fragment fragment = CustomQuizFragment.newInstance();
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, CUSTOM_QUIZ_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        build1stDeclMascFem(mLandingPageView);
        build2ndDeclMasc(mLandingPageView);
        build2ndDeclNeut(mLandingPageView);
        build3rdDeclMascFem(mLandingPageView);
        build3rdDeclNeut(mLandingPageView);
        build4thDeclMascFem(mLandingPageView);
        build5thDeclMascFem(mLandingPageView);

        //Popup Instructions Window
        Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        int width = (int) (point.x * 0.8);
        int height = (int) (point.y * 0.6);
        mInstructionsView = inflater.inflate(R.layout.pop_up_instructions, container, false);
        mInstructionsPopupWindow = new PopupWindow(mInstructionsView,
                width,
                height);
        mInstructionsPopupWindow.setOutsideTouchable(true);
        mInstructionsButton = (Button) mInstructionsView.findViewById(R.id.pop_up_instructions_button);
        mInstructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInstructionsPopupWindow.dismiss();
            }
        });

        //Popup About Window
        mAboutView = inflater.inflate(R.layout.pop_up_about, container, false);
        mAboutPopupWindow = new PopupWindow(mAboutView,
                width,
                WindowManager.LayoutParams.WRAP_CONTENT);
        mAboutPopupWindow.setOutsideTouchable(true);
        mAboutButton = (Button) mAboutView.findViewById(R.id.pop_up_about_button);
        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAboutPopupWindow.dismiss();
            }
        });



        return mLandingPageView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.landing_page_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.landing_page_menu_quit:
                getActivity().finish();
                return true;
            case R.id.landing_page_menu_instructions:
                mInstructionsPopupWindow.showAtLocation(mLandingPageView, Gravity.CENTER, 0, 0);
                return true;
            case R.id.landing_page_menu_about:
                mAboutPopupWindow.showAtLocation(mLandingPageView, Gravity.CENTER, 0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_QUIZ_COMPLETE) {
            getFragmentManager().popBackStack();
        }
    }

    private void colorButton(Button button, String[] nCases, String[] numbers, String[] genders, int[] decls) {
        if (dbHelper == null) {
            Log.d(TAG, "colorButton DBhelper is null");
        }
        List<NounMastery> nounMasteries = dbHelper.loadConcepts(nCases, numbers, genders, decls);
        if (!checkIfConceptReviewed(nounMasteries)) {
            button.setBackgroundResource(R.mipmap.ic_not_reviewed);
            return;
        }
        int totalInterval = 0;
        long totalTimeLastReview = 0;
        for (NounMastery nounMastery: nounMasteries) {

            totalInterval += (nounMastery.getInterval() > 0 ? nounMastery.getInterval() : 0);
            try {
                if (nounMastery.getInterval() != -1) {
                    totalTimeLastReview += new SimpleDateFormat("yyyy-MM-dd").parse(nounMastery.getLastReviewed()).getTime();
                    Log.d(TAG, "NCID: " + nounMastery.getConceptId() + " LastReviewedTotal: " + totalTimeLastReview);
                }
            } catch (ParseException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        int avgInterval = (int) Math.round((double) totalInterval / nounMasteries.size());
        int numDays = calcNumDaysFrom(totalTimeLastReview / nounMasteries.size());
        double mastery = (double) numDays / avgInterval;
        Log.d(TAG, "Button Text: " + button.getText().toString() + " Average Interval: " + avgInterval + " numDays: " + numDays + " mastery: " + mastery);
        Drawable masteryColor;
        if (mastery < 0.2) {
            button.setBackgroundResource(R.mipmap.ic_strength_100);
            //masteryColor = ContextCompat.getDrawable(getActivity(), R.color.strength100);
        } else if (mastery < 0.4) {
            button.setBackgroundResource(R.mipmap.ic_strength_80);
            //masteryColor = ContextCompat.getDrawable(getActivity(), R.color.strength80);
        } else if (mastery < 0.6) {
            button.setBackgroundResource(R.mipmap.ic_strength_60);
            //masteryColor = ContextCompat.getDrawable(getActivity(), R.color.strength60);
        } else if (mastery < 0.8) {
            button.setBackgroundResource(R.mipmap.ic_strength_40);
            //masteryColor = ContextCompat.getDrawable(getActivity(), R.color.strength40);
        } else if (mastery < 1.0) {
            button.setBackgroundResource(R.mipmap.ic_strength_20);
            //masteryColor = ContextCompat.getDrawable(getActivity(), R.color.strength20);
        } else {
            button.setBackgroundResource(R.mipmap.ic_strength_0);
            //masteryColor = ContextCompat.getDrawable(getActivity(), R.color.strength0);
        }
        //button.setBackground(masteryColor);
    }

    private int calcNumDaysFrom(long previousDay) {
        long today = new Date().getTime();
        return (int) (today - previousDay) / (1000 * 60 * 60 * 24);
    }

    private boolean checkIfConceptReviewed(List<NounMastery> nounMasteries) {
        for (NounMastery nounMastery : nounMasteries) {
            if (nounMastery.getInterval() != -1) {
                return true;
            }
        }
        return false;
    }

    private void build1stDeclMascFem(View view) {
        //Singular
        mNomSing1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_nom_sing_button);
        colorButton(mNomSing1stButton, new String[] {"nom"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {1});
        mNomSing1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), NUM_QUESTIONS_PER_CONCEPT));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenSing1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_gen_sing_button);
        colorButton(mGenSing1stButton, new String[] {"gen"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {1});
        mGenSing1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatSing1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_dat_sing_button);
        colorButton(mDatSing1stButton, new String[] {"dat"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {1});
        mDatSing1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccSing1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_acc_sing_button);
        colorButton(mAccSing1stButton, new String[] {"acc"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {1});
        mAccSing1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblSing1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_abl_sing_button);
        colorButton(mAblSing1stButton, new String[] {"abl"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {1});
        mAblSing1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocSing1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_voc_sing_button);
        colorButton(mVocSing1stButton, new String[] {"voc"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {1});
        mVocSing1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        //Plural
        mNomPlur1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_nom_plur_button);
        colorButton(mNomPlur1stButton, new String[] {"nom"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {1});
        mNomPlur1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenPlur1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_gen_plur_button);
        colorButton(mGenPlur1stButton, new String[] {"gen"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {1});
        mGenPlur1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatPlur1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_dat_plur_button);
        colorButton(mDatPlur1stButton, new String[] {"dat"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {1});
        mDatPlur1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccPlur1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_acc_plur_button);
        colorButton(mAccPlur1stButton, new String[] {"acc"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {1});
        mAccPlur1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblPlur1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_abl_plur_button);
        colorButton(mAblPlur1stButton, new String[] {"abl"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {1});
        mAblPlur1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocPlur1stButton = (Button) view.findViewById(R.id.landing_page_1st_decl_voc_plur_button);
        colorButton(mVocPlur1stButton, new String[] {"voc"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {1});
        mVocPlur1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {1});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    private void build2ndDeclMasc(View view) {
        //Singular
        mNomSing2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_nom_sing_button);
        colorButton(mNomSing2ndMascButton, new String[] {"nom"},
                new String[] {"sing"},
                new String[] {"m"},
                new int[] {2});
        mNomSing2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"sing"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenSing2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_gen_sing_button);
        colorButton(mGenSing2ndMascButton, new String[] {"gen"},
                new String[] {"sing"},
                new String[] {"m"},
                new int[] {2});
        mGenSing2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"sing"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatSing2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_dat_sing_button);
        colorButton(mDatSing2ndMascButton, new String[] {"dat"},
                new String[] {"sing"},
                new String[] {"m"},
                new int[] {2});
        mDatSing2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"sing"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccSing2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_acc_sing_button);
        colorButton(mAccSing2ndMascButton, new String[] {"acc"},
                new String[] {"sing"},
                new String[] {"m"},
                new int[] {2});
        mAccSing2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"sing"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblSing2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_abl_sing_button);
        colorButton(mAblSing2ndMascButton, new String[] {"abl"},
                new String[] {"sing"},
                new String[] {"m"},
                new int[] {2});
        mAblSing2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"sing"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocSing2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_voc_sing_button);
        colorButton(mVocSing2ndMascButton, new String[] {"voc"},
                new String[] {"sing"},
                new String[] {"m"},
                new int[] {2});
        mVocSing2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"sing"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        //Plural
        mNomPlur2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_nom_plur_button);
        colorButton(mNomPlur2ndMascButton, new String[] {"nom"},
                new String[] {"plur"},
                new String[] {"m"},
                new int[] {2});
        mNomPlur2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"plur"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenPlur2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_gen_plur_button);
        colorButton(mGenPlur2ndMascButton, new String[] {"gen"},
                new String[] {"plur"},
                new String[] {"m"},
                new int[] {2});
        mGenPlur2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"plur"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatPlur2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_dat_plur_button);
        colorButton(mDatPlur2ndMascButton, new String[] {"dat"},
                new String[] {"plur"},
                new String[] {"m"},
                new int[] {2});
        mDatPlur2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"plur"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccPlur2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_acc_plur_button);
        colorButton(mAccPlur2ndMascButton, new String[] {"acc"},
                new String[] {"plur"},
                new String[] {"m"},
                new int[] {2});
        mAccPlur2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"plur"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblPlur2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_abl_plur_button);
        colorButton(mAblPlur2ndMascButton, new String[] {"abl"},
                new String[] {"plur"},
                new String[] {"m"},
                new int[] {2});
        mAblPlur2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"plur"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocPlur2ndMascButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_masc_voc_plur_button);
        colorButton(mVocPlur2ndMascButton, new String[] {"voc"},
                new String[] {"plur"},
                new String[] {"m"},
                new int[] {2});
        mVocPlur2ndMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"plur"},
                        new String[] {"m"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    private void build2ndDeclNeut(View view) {
        //Singular
        mNomSing2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_nom_sing_button);
        colorButton(mNomSing2ndNeutButton, new String[] {"nom"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {2});
        mNomSing2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenSing2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_gen_sing_button);
        colorButton(mGenSing2ndNeutButton, new String[] {"gen"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {2});
        mGenSing2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatSing2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_dat_sing_button);
        colorButton(mDatSing2ndNeutButton, new String[] {"dat"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {2});
        mDatSing2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccSing2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_acc_sing_button);
        colorButton(mAccSing2ndNeutButton, new String[] {"acc"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {2});
        mAccSing2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblSing2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_abl_sing_button);
        colorButton(mAblSing2ndNeutButton, new String[] {"abl"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {2});
        mAblSing2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocSing2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_voc_sing_button);
        colorButton(mVocSing2ndNeutButton, new String[] {"voc"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {2});
        mVocSing2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        //Plural
        mNomPlur2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_nom_plur_button);
        colorButton(mNomPlur2ndNeutButton, new String[] {"nom"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {2});
        mNomPlur2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenPlur2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_gen_plur_button);
        colorButton(mGenPlur2ndNeutButton, new String[] {"gen"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {2});
        mGenPlur2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatPlur2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_dat_plur_button);
        colorButton(mDatPlur2ndNeutButton, new String[] {"dat"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {2});
        mDatPlur2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccPlur2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_acc_plur_button);
        colorButton(mAccPlur2ndNeutButton, new String[] {"acc"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {2});
        mAccPlur2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblPlur2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_abl_plur_button);
        colorButton(mAblPlur2ndNeutButton, new String[] {"abl"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {2});
        mAblPlur2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocPlur2ndNeutButton = (Button) view.findViewById(R.id.landing_page_2nd_decl_neut_voc_plur_button);
        colorButton(mVocPlur2ndNeutButton, new String[] {"voc"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {2});
        mVocPlur2ndNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {2});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    private void build3rdDeclMascFem(View view) {
        //Singular
        mNomSing3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_nom_sing_button);
        colorButton(mNomSing3rdMascFemButton, new String[] {"nom"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {3});
        mNomSing3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenSing3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_gen_sing_button);
        colorButton(mGenSing3rdMascFemButton, new String[] {"gen"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {3});
        mGenSing3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatSing3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_dat_sing_button);
        colorButton(mDatSing3rdMascFemButton, new String[] {"dat"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {3});
        mDatSing3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccSing3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_acc_sing_button);
        colorButton(mAccSing3rdMascFemButton, new String[] {"acc"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {3});
        mAccSing3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblSing3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_abl_sing_button);
        colorButton(mAblSing3rdMascFemButton, new String[] {"abl"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {3});
        mAblSing3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocSing3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_voc_sing_button);
        colorButton(mVocSing3rdMascFemButton, new String[] {"voc"},
                new String[] {"sing"},
                new String[] {"m", "f"},
                new int[] {3});
        mVocSing3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"sing"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        //Plural
        mNomPlur3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_nom_plur_button);
        colorButton(mNomPlur3rdMascFemButton, new String[] {"nom"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {3});
        mNomPlur3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenPlur3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_gen_plur_button);
        colorButton(mGenPlur3rdMascFemButton, new String[] {"gen"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {3});
        mGenPlur3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatPlur3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_dat_plur_button);
        colorButton(mDatPlur3rdMascFemButton, new String[] {"dat"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {3});
        mDatPlur3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccPlur3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_acc_plur_button);
        colorButton(mAccPlur3rdMascFemButton, new String[] {"acc"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {3});
        mAccPlur3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblPlur3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_abl_plur_button);
        colorButton(mAblPlur3rdMascFemButton, new String[] {"abl"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {3});
        mAblPlur3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocPlur3rdMascFemButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_masc_fem_voc_plur_button);
        colorButton(mVocPlur3rdMascFemButton, new String[] {"voc"},
                new String[] {"plur"},
                new String[] {"m", "f"},
                new int[] {3});
        mVocPlur3rdMascFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"plur"},
                        new String[] {"m", "f"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    private void build3rdDeclNeut(View view) {
        //Singular
        mNomSing3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_nom_sing_button);
        colorButton(mNomSing3rdNeutButton, new String[] {"nom"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {3});
        mNomSing3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenSing3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_gen_sing_button);
        colorButton(mGenSing3rdNeutButton, new String[] {"gen"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {3});
        mGenSing3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatSing3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_dat_sing_button);
        colorButton(mDatSing3rdNeutButton, new String[] {"dat"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {3});
        mDatSing3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccSing3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_acc_sing_button);
        colorButton(mAccSing3rdNeutButton, new String[] {"acc"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {3});
        mAccSing3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblSing3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_abl_sing_button);
        colorButton(mAblSing3rdNeutButton, new String[] {"abl"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {3});
        mAblSing3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocSing3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_voc_sing_button);
        colorButton(mVocSing3rdNeutButton, new String[] {"voc"},
                new String[] {"sing"},
                new String[] {"n"},
                new int[] {3});
        mVocSing3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"sing"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        //Plural
        mNomPlur3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_nom_plur_button);
        colorButton(mNomPlur3rdNeutButton, new String[] {"nom"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {3});
        mNomPlur3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenPlur3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_gen_plur_button);
        colorButton(mGenPlur3rdNeutButton, new String[] {"gen"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {3});
        mGenPlur3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatPlur3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_dat_plur_button);
        colorButton(mDatPlur3rdNeutButton, new String[] {"dat"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {3});
        mDatPlur3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccPlur3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_acc_plur_button);
        colorButton(mAccPlur3rdNeutButton, new String[] {"acc"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {3});
        mAccPlur3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblPlur3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_abl_plur_button);
        colorButton(mAblPlur3rdNeutButton, new String[] {"abl"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {3});
        mAblPlur3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocPlur3rdNeutButton = (Button) view.findViewById(R.id.landing_page_3rd_decl_neut_voc_plur_button);
        colorButton(mVocPlur3rdNeutButton, new String[] {"voc"},
                new String[] {"plur"},
                new String[] {"n"},
                new int[] {3});
        mVocPlur3rdNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"plur"},
                        new String[] {"n"},
                        new int[] {3});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    private void build4thDeclMascFem(View view) {
        //Singular
        mNomSing4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_nom_sing_button);
        colorButton(mNomSing4thButton, new String[] {"nom"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mNomSing4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenSing4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_gen_sing_button);
        colorButton(mGenSing4thButton, new String[] {"gen"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mGenSing4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatSing4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_dat_sing_button);
        colorButton(mDatSing4thButton, new String[] {"dat"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mDatSing4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccSing4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_acc_sing_button);
        colorButton(mAccSing4thButton, new String[] {"acc"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mAccSing4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblSing4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_abl_sing_button);
        colorButton(mAblSing4thButton, new String[] {"abl"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mAblSing4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocSing4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_voc_sing_button);
        colorButton(mVocSing4thButton, new String[] {"voc"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mVocSing4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        //Plural
        mNomPlur4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_nom_plur_button);
        colorButton(mNomPlur4thButton, new String[] {"nom"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mNomPlur4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenPlur4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_gen_plur_button);
        colorButton(mGenPlur4thButton, new String[] {"gen"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mGenPlur4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatPlur4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_dat_plur_button);
        colorButton(mDatPlur4thButton, new String[] {"dat"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mDatPlur4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccPlur4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_acc_plur_button);
        colorButton(mAccPlur4thButton, new String[] {"acc"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mAccPlur4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblPlur4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_abl_plur_button);
        colorButton(mAblPlur4thButton, new String[] {"abl"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mAblPlur4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocPlur4thButton = (Button) view.findViewById(R.id.landing_page_4th_decl_voc_plur_button);
        colorButton(mVocPlur4thButton, new String[] {"voc"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {4});
        mVocPlur4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {4});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    private void build5thDeclMascFem(View view) {
        //Singular
        mNomSing5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_nom_sing_button);
        colorButton(mNomSing5thButton, new String[] {"nom"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mNomSing5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenSing5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_gen_sing_button);
        colorButton(mGenSing5thButton, new String[] {"gen"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mGenSing5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatSing5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_dat_sing_button);
        colorButton(mDatSing5thButton, new String[] {"dat"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mDatSing5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccSing5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_acc_sing_button);
        colorButton(mAccSing5thButton, new String[] {"acc"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mAccSing5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblSing5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_abl_sing_button);
        colorButton(mAblSing5thButton, new String[] {"abl"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mAblSing5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocSing5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_voc_sing_button);
        colorButton(mVocSing5thButton, new String[] {"voc"},
                new String[] {"sing"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mVocSing5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"sing"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        //Plural
        mNomPlur5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_nom_plur_button);
        colorButton(mNomPlur5thButton, new String[] {"nom"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mNomPlur5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"nom"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mGenPlur5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_gen_plur_button);
        colorButton(mGenPlur5thButton, new String[] {"gen"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mGenPlur5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"gen"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mDatPlur5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_dat_plur_button);
        colorButton(mDatPlur5thButton, new String[] {"dat"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mDatPlur5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"dat"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAccPlur5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_acc_plur_button);
        colorButton(mAccPlur5thButton, new String[] {"acc"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mAccPlur5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"acc"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mAblPlur5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_abl_plur_button);
        colorButton(mAblPlur5thButton, new String[] {"abl"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mAblPlur5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"abl"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });

        mVocPlur5thButton = (Button) view.findViewById(R.id.landing_page_5th_decl_voc_plur_button);
        colorButton(mVocPlur5thButton, new String[] {"voc"},
                new String[] {"plur"},
                new String[] {"m", "f", "n"},
                new int[] {5});
        mVocPlur5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NounMastery> nounMasteries = dbHelper.loadConcepts(new String[] {"voc"},
                        new String[] {"plur"},
                        new String[] {"m", "f", "n"},
                        new int[] {5});
                List<NounForm> nounForms = new ArrayList<>();
                for (NounMastery mastery : nounMasteries) {
                    nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), 10));
                }
                //Load NounConceptQuizFragment
                FragmentManager fm = getFragmentManager();
                Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                fragment.setTargetFragment(LandingPageFragment.this, REQUEST_QUIZ_COMPLETE);
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }
}
