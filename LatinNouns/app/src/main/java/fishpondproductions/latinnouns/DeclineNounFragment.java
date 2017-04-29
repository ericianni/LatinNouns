package fishpondproductions.latinnouns;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by ericianni on 4/12/17.
 */

public class DeclineNounFragment extends Fragment {
    private final String TAG = "DeclineNounFragment";
    private static final String NOUN_FORM = "noun_forms";
    public static final String EXTRA_IS_CORRECT = "fishpondproductions.latinnous.is_correct";

    private NounForm mNounForm;

    private TextView mDeclineNounPromptTextView;
    private EditText mDeclineNounCaseEditText;
    private EditText mDeclineNounNumberEditText;
    private EditText mDeclineInputEditText;

    private Button mAMacronButton;
    private Button mEMacronButton;
    private Button mIMacronButton;
    private Button mOMacronButton;
    private Button mUMacronButton;
    private Button mDeclineNounSubmitButton;

    public static DeclineNounFragment newInstance(NounForm nounForm) {
        Bundle args = new Bundle();
        args.putParcelable(NOUN_FORM, nounForm);
        DeclineNounFragment fragment = new DeclineNounFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mNounForm = getArguments().getParcelable(NOUN_FORM);
        Log.d(TAG, "Count: " + getFragmentManager().getBackStackEntryCount());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.decline_noun, container, false);

        mDeclineNounPromptTextView = (TextView) view.findViewById(R.id.decline_noun_dict_textview);
        mDeclineNounCaseEditText = (EditText) view.findViewById(R.id.decline_noun_case_edittext);
        mDeclineNounNumberEditText = (EditText) view.findViewById(R.id.decline_noun_number_edittext);
        mDeclineInputEditText = (EditText) view.findViewById(R.id.decline_noun_input_edittext);
        mDeclineInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    checkAnswer();
                    handled = true;
                }
                return handled;
            }
        });

        mAMacronButton = (Button) view.findViewById(R.id.decline_noun_a_macron_button);
        mAMacronButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeclineInputEditText.setText(mDeclineInputEditText.getText() + "ā");
                mDeclineInputEditText.setSelection(mDeclineInputEditText.getText().length());
            }
        });
        mEMacronButton = (Button) view.findViewById(R.id.decline_noun_e_macron_button);
        mEMacronButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeclineInputEditText.setText(mDeclineInputEditText.getText() + "ē");
                mDeclineInputEditText.setSelection(mDeclineInputEditText.getText().length());
            }
        });
        mIMacronButton = (Button) view.findViewById(R.id.decline_noun_i_macron_button);
        mIMacronButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeclineInputEditText.setText(mDeclineInputEditText.getText() + "ī");
                mDeclineInputEditText.setSelection(mDeclineInputEditText.getText().length());
            }
        });
        mOMacronButton = (Button) view.findViewById(R.id.decline_noun_o_macron_button);
        mOMacronButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeclineInputEditText.setText(mDeclineInputEditText.getText() + "ō");
                mDeclineInputEditText.setSelection(mDeclineInputEditText.getText().length());
            }
        });
        mUMacronButton = (Button) view.findViewById(R.id.decline_noun_u_macron_button);
        mUMacronButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeclineInputEditText.setText(mDeclineInputEditText.getText() + "ū");
                mDeclineInputEditText.setSelection(mDeclineInputEditText.getText().length());
            }
        });

        mDeclineNounSubmitButton = (Button) view.findViewById(R.id.decline_noun_submit_button);
        mDeclineNounSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        loadQuestion(mNounForm);

        return view;
    }



    private void checkAnswer() {
        if (mDeclineInputEditText.getText().toString().equals("")) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.please_enter_answer),
                    Toast.LENGTH_SHORT).show();
        } else {
            if (mDeclineInputEditText.getText().toString()
                    .toLowerCase().equals(mNounForm.getForm())) {
                //correct
                showDialog(true);
                //sendResult(Activity.RESULT_OK, true);
            } else {
                //incorrect
                showDialog(false);
                //sendResult(Activity.RESULT_OK, false);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadQuestion(NounForm question) {
        mDeclineNounPromptTextView.setText(question.getPP1() + ", " +
                question.getPP2() + ", " + question.getGender() + ", ");
        switch (question.getDecl()) {
            case 1:
                mDeclineNounPromptTextView.append("1st");
                break;
            case 2:
                mDeclineNounPromptTextView.append("2nd");
                break;
            case 3:
                mDeclineNounPromptTextView.append("3rd");
                break;
            case 4:
                mDeclineNounPromptTextView.append("4th");
                break;
            case 5:
                mDeclineNounPromptTextView.append("5th");
                break;
        }

        mDeclineNounCaseEditText.setText(mNounForm.getCase());
        mDeclineNounNumberEditText.setText(mNounForm.getNumber());
    }

    private void sendResult(int resultCode, boolean isCorrect) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CORRECT, isCorrect);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                resultCode,
                intent);
    }

    private void showDialog(final boolean isCorrect) {
        String title;
        String message;
        if (isCorrect) {
            title = getResources().getString(R.string.dialog_title_correct);
            message = getResources().getString(R.string.dialog_message_correct);
        } else {
            title = getResources().getString(R.string.dialog_title_incorrect);
            message = getResources().getString(R.string.dialog_message_user_answer) + " " +
            mDeclineInputEditText.getText().toString() + "\n" +
            getResources().getString(R.string.dialog_message_incorrect) + " " + mNounForm.getForm();
        }
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_continue_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK, isCorrect);
                    }
                })
                .show();
    }


}
