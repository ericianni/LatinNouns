package fishpondproductions.latinnouns;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ericianni on 4/8/17.
 */

public class NounConceptQuizFragment extends Fragment {
    private final String TAG = "NounConceptQuizFragment";
    private static final String NOUN_MASTERIES = "noun_masteries";
    private static final String NOUN_FORMS = "noun_forms";
    private static final int REQUEST_IS_CORRECT = 0;
    private static final String EXTRA_QUIZ_DONE = "quiz_done";
    private static final int MAX_QUESTIONS = 20;

    private ArrayList<NounMastery> mNounMasteries;
    private ArrayList<NounForm> mNounForms;
    private ArrayList<NounForm> mQuestionsToAsk;
    private int mNumberOfQuestions;
    private NounForm mCurrentNounForm;
    private Map<Integer, Double> mQuizConceptsQ;

    private ProgressBar mProgressBar;

    public static NounConceptQuizFragment newInstance(ArrayList<NounMastery> nounMasteries,
                                                      ArrayList<NounForm> nounForms) {
        Bundle args = new Bundle();
        if (nounMasteries == null) {
            Log.d("NCQUIZ.newInstance: ", "nounMasteries is null");
        }
        if (nounForms == null) {
            Log.d("NCQUIZ.newInstance: ", "nounForms is null");
        }
        args.putParcelableArrayList(NOUN_MASTERIES, nounMasteries);
        args.putParcelableArrayList(NOUN_FORMS, nounForms);
        NounConceptQuizFragment fragment = new NounConceptQuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.noun_concept_quiz_fragment, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.noun_conceopt_quiz_progressbar);
        mProgressBar.setMax(mNumberOfQuestions);
        mProgressBar.getProgressDrawable().setColorFilter(
                ContextCompat.getColor(getActivity(), R.color.button_selected),
                PorterDuff.Mode.SRC_IN);
        mProgressBar.setProgress(0);
        loadNextQuestion();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNounMasteries = getArguments().getParcelableArrayList(NOUN_MASTERIES);
        mNounForms = getArguments().getParcelableArrayList(NOUN_FORMS);
        Collections.shuffle(mNounForms);
        Log.d(TAG, "Number of Concepts: " + mNounMasteries.size());
        Log.d(TAG, "Number of forms: " + mNounForms.size());

        mNumberOfQuestions = mNounForms.size() < MAX_QUESTIONS ? mNounForms.size() : MAX_QUESTIONS;
        mQuestionsToAsk = new ArrayList<>(mNounForms.subList(0, mNumberOfQuestions));
        mQuizConceptsQ = new HashMap<>();
        for (NounForm nounForm : mQuestionsToAsk) {
            mQuizConceptsQ.put(nounForm.getConceptId(), 5.0);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.decline_noun_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.decline_noun_menu_exit:
                sendResult(Activity.RESULT_OK, false);
                getFragmentManager().popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadNextQuestion() {
        if (mQuestionsToAsk.size() > 0) {
            mCurrentNounForm = mQuestionsToAsk.get(0);
        } else {
            return;
        }
        FragmentManager fm = getFragmentManager();
        Fragment fragment = DeclineNounFragment.newInstance(mCurrentNounForm);
        fragment.setTargetFragment(this, REQUEST_IS_CORRECT);
        fm.beginTransaction()
                .replace(R.id.quiz_question_container, fragment)
                .addToBackStack(TAG)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_IS_CORRECT) {
            Log.d(TAG, "onActivity Result");
            getFragmentManager().popBackStack();
            boolean isCorrect = data.getBooleanExtra(DeclineNounFragment.EXTRA_IS_CORRECT, false);
            if (isCorrect) {
                Log.d(TAG, "Answer Correct");
                mQuestionsToAsk.remove(0);
                updateProgressBar();
            } else {
                Log.d(TAG, "Answer Incorrect");
                updateConceptQ();
                Collections.shuffle(mQuestionsToAsk);
            }
            if (mQuestionsToAsk.size() == 0) {
                updateNounMasteries();
                sendResult(Activity.RESULT_OK, true);
            } else {
                loadNextQuestion();
            }
        }
    }

    private void updateProgressBar() {
        int progress = mNumberOfQuestions - mQuestionsToAsk.size();
        mProgressBar.setProgress(progress);
    }

    private void updateConceptQ() {
        Integer ncId = mCurrentNounForm.getConceptId();
        Double q = mQuizConceptsQ.get(ncId) - 0.5;
        Log.d(TAG, "NCID: " + ncId + " q: " + q);
        if (q < 0.0) {
            q = 0.0;
        }
        mQuizConceptsQ.put(ncId, q);
    }

    private void updateNounMasteries() {
        for (int ncId : mQuizConceptsQ.keySet()) {
            double q = mQuizConceptsQ.get(ncId);
            for (NounMastery nounMastery : mNounMasteries) {
                if (ncId == nounMastery.getConceptId()) {
                    Log.d(TAG, "update ncid: " + ncId + " q: " + q);
                    CustomSM2.updateNounMastery(nounMastery, q);
                }
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
