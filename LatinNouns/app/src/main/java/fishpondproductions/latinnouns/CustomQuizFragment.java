package fishpondproductions.latinnouns;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fishpondproductions.latinnouns.LandingPageFragment.dbHelper;

/**
 * Created by ericianni on 4/9/17.
 */

public class CustomQuizFragment extends Fragment{

    private final String TAG = "CustomQuizFragment";
    private final int NUM_QUESTIONS_PER_CONCEPT = 10;
    private final int REQUEST_QUIZ_COMPLETE = 0;
    private final String EXTRA_QUIZ_DONE = "quiz_done";

    private Drawable mDefaultButtonColor;
    private Drawable mSelectedButtonColor;

    private View mcustomPracticeView;
    private View mInfoView;
    private Button mInfoButton;
    private PopupWindow mInfoPopupWindow;

    private Button mNomButton;
    private Button mGenButton;
    private Button mDatButton;
    private Button mAccButton;
    private Button mAblButton;
    private Button mVocButton;

    private Button mSingButton;
    private Button mPlurButton;

    private Button mMascButton;
    private Button mFemButton;
    private Button mNeutButton;

    private Button m1stButton;
    private Button m2ndButton;
    private Button m3rdButton;
    private Button m4thButton;
    private Button m5thButton;

    private Button mQuizButton;

    boolean mNomIsSelected = false;
    boolean mGenIsSelected = false;
    boolean mDatIsSelected = false;
    boolean mAccIsSelected = false;
    boolean mAblIsSelected = false;
    boolean mVocIsSelected = false;

    boolean mSingIsSelected = false;
    boolean mPlurIsSelected = false;

    boolean mMascIsSelected = false;
    boolean mFemIsSelected = false;
    boolean mNeutIsSelected = false;

    boolean m1stIsSelected = false;
    boolean m2ndIsSelected = false;
    boolean m3rdIsSelected = false;
    boolean m4thIsSelected = false;
    boolean m5thIsSelected = false;


    public static CustomQuizFragment newInstance() {
        return new CustomQuizFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        mInfoPopupWindow.dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mcustomPracticeView = inflater.inflate(R.layout.custom_practice, container, false);

        //Case buttons
        mNomButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_nom_button);
        mNomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNomIsSelected = toggleButton(mNomButton, mNomIsSelected);
            }
        });
        mGenButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_gen_button);
        mGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGenIsSelected = toggleButton(mGenButton, mGenIsSelected);
            }
        });
        mDatButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_dat_button);
        mDatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatIsSelected = toggleButton(mDatButton, mDatIsSelected);
            }
        });
        mAccButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_acc_button);
        mAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAccIsSelected = toggleButton(mAccButton, mAccIsSelected);
            }
        });
        mAblButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_abl_button);
        mAblButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAblIsSelected = toggleButton(mAblButton, mAblIsSelected);
            }
        });
        mVocButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_voc_button);
        mVocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVocIsSelected = toggleButton(mVocButton, mVocIsSelected);
            }
        });

        //Number Buttons
        mSingButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_sing_button);
        mSingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSingIsSelected = toggleButton(mSingButton, mSingIsSelected);
            }
        });
        mPlurButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_plur_button);
        mPlurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlurIsSelected = toggleButton(mPlurButton, mPlurIsSelected);
            }
        });

        //Gender Buttons
        mMascButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_masc_button);
        mMascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMascIsSelected = toggleButton(mMascButton, mMascIsSelected);
            }
        });
        mFemButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_fem_button);
        mFemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFemIsSelected = toggleButton(mFemButton, mFemIsSelected);
            }
        });
        mNeutButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_neut_button);
        mNeutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNeutIsSelected = toggleButton(mNeutButton, mNeutIsSelected);
            }
        });

        //Declension Buttons
        m1stButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_1st_button);
        m1stButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m1stIsSelected = toggleButton(m1stButton, m1stIsSelected);
            }
        });
        m2ndButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_2nd_button);
        m2ndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m2ndIsSelected = toggleButton(m2ndButton, m2ndIsSelected);
            }
        });
        m3rdButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_3rd_button);
        m3rdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m3rdIsSelected = toggleButton(m3rdButton, m3rdIsSelected);
            }
        });
        m4thButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_4th_button);
        m4thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m4thIsSelected = toggleButton(m4thButton, m4thIsSelected);
            }
        });
        m5thButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_5th_button);
        m5thButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m5thIsSelected = toggleButton(m5thButton, m5thIsSelected);
            }
        });

        //Quiz Button
        mQuizButton = (Button) mcustomPracticeView.findViewById(R.id.custom_practice_quiz_button);
        mQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] cases = getCases();
                String [] numbers = getNumbers();
                String [] genders = getGenders();
                int [] decls = getDecls();

                if (cases.length == 0 || numbers.length == 0 ||
                        genders.length == 0 || decls.length == 0) {
                    Toast.makeText(getActivity(), "Select at least one from each category", Toast.LENGTH_SHORT).show();
                } else {
                    List<NounMastery> nounMasteries = dbHelper.loadConcepts(
                            cases, numbers, genders, decls);
                    List<NounForm> nounForms = new ArrayList<>();
                    for (NounMastery mastery : nounMasteries) {
                        nounForms.addAll(dbHelper.loadForms(mastery.getConceptId(), NUM_QUESTIONS_PER_CONCEPT));
                    }
                    if (nounForms.size() == 0) {
                        //display message that no forms for that selection
                        new AlertDialog.Builder(getContext())
                                .setTitle(R.string.custom_practice_warning_title)
                                .setMessage(R.string.custom_practice_warning_message)
                                .setPositiveButton(R.string.custom_practice_warning_button, null)
                                .show();
                    } else {
                        //start NounConceptQuizFragment
                        Log.d(TAG, "Starting Quiz");
                        FragmentManager fm = getFragmentManager();
                        Fragment fragment = NounConceptQuizFragment.newInstance((ArrayList) nounMasteries, (ArrayList) nounForms);
                        fragment.setTargetFragment(CustomQuizFragment.this, REQUEST_QUIZ_COMPLETE);
                        fm.beginTransaction()
                                .replace(R.id.fragment_container, fragment, LandingPageFragment.DECLINE_NOUN_TAG)
                                .addToBackStack(TAG)
                                .commit();
                    }

                }

                Log.d(TAG, "Cases: " + Arrays.toString(cases));
                Log.d(TAG, "Numbers: " + Arrays.toString(numbers));
                Log.d(TAG, "Genders: " + Arrays.toString(genders));
                Log.d(TAG, "Decls: " + Arrays.toString(decls));
            }
        });

        mDefaultButtonColor = ((Drawable) mNomButton.getBackground());
        mSelectedButtonColor = ContextCompat.getDrawable(getActivity(), R.color.buttonSelected);

        //Popup Info Window
        Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        int width = (int) (point.x * 0.8);
        int height = (int) (point.y * 0.6);
        mInfoView = inflater.inflate(R.layout.pop_up_info, container, false);
        mInfoPopupWindow = new PopupWindow(mInfoView,
                width,
                height);
        mInfoPopupWindow.setOutsideTouchable(true);
        mInfoButton = (Button) mInfoView.findViewById(R.id.pop_up_info_button);
        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfoPopupWindow.dismiss();
            }
        });
        return mcustomPracticeView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.custom_quiz_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.custom_quiz_menu_back:
                sendResult(Activity.RESULT_OK, false);
                return true;
            case R.id.custom_quiz_menu_instructions:
                mInfoPopupWindow.showAtLocation(mcustomPracticeView, Gravity.CENTER, 0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean toggleButton(Button button, boolean isSelected) {
        isSelected = !isSelected;
        button.setBackgroundResource(isSelected ? R.drawable.selected_button : R.drawable.unselected_button);
        return isSelected;
    }

    private String[] getCases() {
        List<String> cases = new ArrayList<>();
        if (mNomIsSelected) {
            cases.add("nom");
        }
        if (mGenIsSelected) {
            cases.add("gen");
        }
        if (mDatIsSelected) {
            cases.add("dat");
        }
        if (mAccIsSelected) {
            cases.add("acc");
        }
        if (mAblIsSelected) {
            cases.add("abl");
        }
        if (mVocIsSelected) {
            cases.add("voc");
        }
        return cases.toArray(new String[0]);
    }

    private String[] getNumbers() {
        List<String> numbers = new ArrayList<>();
        if (mSingIsSelected) {
            numbers.add("sing");
        }
        if (mPlurIsSelected) {
            numbers.add("plur");
        }
        return numbers.toArray(new String[0]);
    }

    private String[] getGenders() {
        List<String> genders = new ArrayList<>();
        if (mMascIsSelected) {
            genders.add("m");
        }
        if (mFemIsSelected) {
            genders.add("f");
        }
        if (mNeutIsSelected) {
            genders.add("n");
        }
        return genders.toArray(new String[0]);
    }

    private int[] getDecls() {
        int [] decls = {0,0,0,0,0};
        int count = 0;
        if (m1stIsSelected) {
            decls[0] = 1;
            count++;
        }
        if (m2ndIsSelected) {
            decls[1] = 2;
            count++;
        }
        if (m3rdIsSelected) {
            decls[2] = 3;
            count++;
        }
        if (m4thIsSelected) {
            decls[3] = 4;
            count++;
        }
        if (m5thIsSelected) {
            decls[4] = 5;
            count++;
        }
        int [] result = new int[count];
        count = 0;
        for (int i = 0; i < 5; i++) {
            if (decls[i] != 0) {
                result[count] = decls[i];
                count++;
            }
        }
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_QUIZ_COMPLETE) {
            Log.d(TAG, "Quiz Complete");
            getFragmentManager().popBackStack();
            if (data.getBooleanExtra(EXTRA_QUIZ_DONE, false)) {
                sendResult(resultCode, true);
            } else {
                sendResult(resultCode, false);
            }
        }
    }

    private void sendResult(int resultCode, boolean isDone) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_QUIZ_DONE, isDone);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                resultCode,
                intent);
    }
}
