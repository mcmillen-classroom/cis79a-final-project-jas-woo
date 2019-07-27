package com.example.veganres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FunFactsActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener
{
    private TextView mTextView;
    private TextView mFeedback;
    private TextView mScoreTextView;
    private EditText mEditText;

    private LinearLayout mTrueFalseContainer;
    private LinearLayout mMultipleChoiceContainer;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mHintButton;

    private RadioGroup mMCButtons;
    private RadioButton mAButton;
    private RadioButton mBButton;
    private RadioButton mCButton;
    private RadioButton mDButton;
    private Drawable mDefaultButtonStyle;
    private Drawable mRadioButtonStyle;


    private Question[] mQuestions;
    private int mIndex;
    private int mScore;

    private Feedback[] mFeedbacks;
    private int mFIndex;

    public static Intent newIntent(MainActivity mainActivity)
    {
        Intent ret = new Intent (mainActivity, FunFactsActivity.class);

        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        mTextView = (TextView) findViewById(R.id.text_view);
        mFeedback = (TextView) findViewById(R.id.feedback_textview);
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);

        mTrueFalseContainer = (LinearLayout) findViewById(R.id.true_false_container);
        mMultipleChoiceContainer = (LinearLayout) findViewById(R.id.multiple_choice_container);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mHintButton = (Button) findViewById(R.id.hint_button);

        mMCButtons = (RadioGroup) findViewById(R.id.mc_buttons);
        mAButton = (RadioButton) findViewById(R.id.a_button);
        mBButton = (RadioButton) findViewById(R.id.b_button);
        mCButton = (RadioButton) findViewById(R.id.c_button);
        mDButton = (RadioButton) findViewById(R.id.d_button);

        mDefaultButtonStyle = mTrueButton.getBackground();
        mRadioButtonStyle = mAButton.getBackground();

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);

        mMCButtons.setOnCheckedChangeListener(this);

        //INITIALIZE AN ARRAY OF QUESTIONS
        mQuestions = new Question[3];
        mIndex = 0;
        mScore = 0;
        //INITIALIZE EACH SLOT IN THE ARRAY
        mQuestions[0] = new TrueFalseQuestion(R.string.question_1, R.string.question_1_hint, false);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2, R.string.question_2_hint, true);

        mQuestions[2] = new MultipleChoiceQuestion(R.string.question_3, R.string.question_3_hint, R.array.question_3_answers, 3);

        //Set-up the first question
        setupQuestion();

        mFeedbacks = new Feedback[3];
        mFIndex = 0;

        mFeedbacks[0] = new Feedback(R.string.feedback_1);
        mFeedbacks[1] = new Feedback(R.string.feedback_2);
        mFeedbacks[2] = new Feedback(R.string.feedback_3);

        mFeedback.setText(mFeedbacks[mFIndex].getFeedbackResId());
        mFeedback.setVisibility(View.GONE);

        mScoreTextView.setText(getString(R.string.score, mScore));

        setTitle("Fun\uD83C\uDF31Facts\uD83C\uDF3EQuiz");

    }
    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.true_button)
        {
            if (checkAnswer(true) == true)
            {
                mScore++;
                mScoreTextView.setText(getString(R.string.score, mScore));
                mTrueButton.setBackgroundColor(Color.BLUE);
            }
            else
            {
                mTrueButton.setBackgroundColor(Color.RED);
            }
            mFeedback.setVisibility(View.VISIBLE);
        }
        else if (view.getId() == R.id.false_button)
        {
            if (checkAnswer(false))
            {
                mScore++;
                mScoreTextView.setText(getString(R.string.score, mScore));
                mFalseButton.setBackgroundColor(Color.BLUE);

            }
            else
            {
                mFalseButton.setBackgroundColor(Color.RED);
            }
            mFeedback.setVisibility(View.VISIBLE);
        }
        else if (view.getId() == R.id.next_button)
        {
            mFeedback.setVisibility(View.GONE);

            mIndex++;
            mFIndex++;



            if (mIndex < mQuestions.length)
            {
                mTrueButton.setBackgroundDrawable(mDefaultButtonStyle);
                mFalseButton.setBackgroundDrawable(mDefaultButtonStyle);
                mAButton.setBackgroundDrawable(mRadioButtonStyle);
                mBButton.setBackgroundDrawable(mRadioButtonStyle);
                mCButton.setBackgroundDrawable(mRadioButtonStyle);
                mDButton.setBackgroundDrawable(mRadioButtonStyle);

                setupQuestion();
                mTextView.setText(mQuestions[mIndex].getTextResId());
                mFeedback.setText(mFeedbacks[mFIndex].getFeedbackResId());
            }
            else
            {
                mTrueFalseContainer.setVisibility(View.GONE);
                mMultipleChoiceContainer.setVisibility(View.GONE);
                mNextButton.setVisibility(View.GONE);
                mPreviousButton.setVisibility(View.GONE);
                mHintButton.setVisibility(View.GONE);
                mTextView.setText(R.string.question_end);
                mFeedback.setVisibility(view.VISIBLE);
                mFeedback.setText(R.string.feedback_end);


            }
        }
        else if (view.getId() == R.id.previous_button)
        {

            mFeedback.setVisibility(View.GONE);

            mIndex--;
            mFIndex--;

            mTrueButton.setBackgroundDrawable(mDefaultButtonStyle);
            mFalseButton.setBackgroundDrawable(mDefaultButtonStyle);
            mAButton.setBackgroundDrawable(mDefaultButtonStyle);
            mBButton.setBackgroundDrawable(mDefaultButtonStyle);
            mCButton.setBackgroundDrawable(mDefaultButtonStyle);
            mDButton.setBackgroundDrawable(mDefaultButtonStyle);




            if (mIndex < 0)
            {
                mIndex = 0;
                mFIndex = 0;
                setupQuestion();
            }
            mTextView.setText(mQuestions[mIndex].getTextResId());
            mFeedback.setText(mFeedbacks[mFIndex].getFeedbackResId());

        }
        else if (view.getId() == R.id.hint_button)
        {
            Toast myToast = Toast.makeText(this, mQuestions[mIndex].getHintTextResId(), Toast.LENGTH_LONG);
            myToast.show();
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
//        boolean checked = ((RadioButton) view).isChecked();

        if (checkedId == -1)
        {
            return;
        }

        boolean answerIsCorrect = false;

        switch (checkedId)
        {
            case R.id.a_button:
                if (answerIsCorrect = checkAnswer(0))
                {
                    mAButton.setBackgroundColor(Color.BLUE);
                }
                else
                {
                    mAButton.setBackgroundColor(Color.RED);
                }
                break;
            case R.id.b_button:
                if (answerIsCorrect = checkAnswer(1))
                {
                    mBButton.setBackgroundColor(Color.BLUE);
                }
                else
                {
                    mBButton.setBackgroundColor(Color.RED);
                }
                break;
            case R.id.c_button:
                if (answerIsCorrect = checkAnswer(2))
                {
                    mCButton.setBackgroundColor(Color.BLUE);
                }
                else
                {
                    mCButton.setBackgroundColor(Color.RED);
                }
                break;
            case R.id.d_button:
                if (answerIsCorrect = checkAnswer(3))
                {
                    mDButton.setBackgroundColor(Color.BLUE);
                }
                else
                {
                    mDButton.setBackgroundColor(Color.RED);
                }
                break;
        }

        if (answerIsCorrect)
        {
            mScore++;
            mScoreTextView.setText(getString(R.string.score, mScore));
        }
    }


    public void setupQuestion()
    {
        mTextView.setText(mQuestions[mIndex].getTextResId());

        if (mQuestions[mIndex].isTrueFalseQuestion())
        {
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mMultipleChoiceContainer.setVisibility(View.GONE);
        }
        else if (mQuestions[mIndex].isMultipleChoiceQuestion())
        {
            mMultipleChoiceContainer.setVisibility(View.VISIBLE);
            mTrueFalseContainer.setVisibility(View.GONE);

            int optionsResId = ((MultipleChoiceQuestion) mQuestions[mIndex]).getOptionsResId();
            String[] options = getResources().getStringArray(optionsResId);
            //USE OPTIONS ARRAY TO SET TEXT
            mAButton.setText(options[0]);
            mBButton.setText(options[1]);
            mCButton.setText(options[2]);
            mDButton.setText(options[3]);

        }
    }

    public boolean checkAnswer(int userInput)
    {
        if (mQuestions[mIndex].checkAnswer(userInput))
        {
            Toast myToast = Toast.makeText(this, "🎉YOU ARE CORRECT!🎉", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            return true;
        }
        else
        {
            Toast myToast = Toast.makeText(this, "You are incorrect😟", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            return false;
        }
    }

    public boolean checkAnswer(boolean userInput)
    {

        if (mQuestions[mIndex].checkAnswer(userInput))
        {
            Toast myToast = Toast.makeText(this, "🎉YOU ARE CORRECT!🎉", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            return true;
        }
        else
        {
            Toast myToast = Toast.makeText(this, "You are incorrect😟", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            return false;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {

    }
}
