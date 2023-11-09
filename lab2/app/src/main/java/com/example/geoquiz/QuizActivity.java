package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class QuizActivity extends AppCompatActivity {
    private TextView mTextQuiz;
    private TextView mQuizCounter;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private ArrayList<Question> mQuestionBank = new ArrayList<>(Arrays.asList(
            new Question(R.string.question_1, R.string.answer_1, false),
            new Question(R.string.question_2, R.string.answer_2, true),
            new Question(R.string.question_3, R.string.answer_3, false),
            new Question(R.string.question_4, R.string.answer_4, false)
    ));
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mTextQuiz = (TextView) findViewById(R.id.TextQuiz);
        mQuizCounter = (TextView) findViewById(R.id.QuizCounter);
        setNextQuestion();
        addListenerOnClickButtonTrueFalse();
    }

    public void addListenerOnClickButtonTrueFalse() {
        Button mTrueButton = (Button) findViewById(R.id.buttonTrue);
        Button mFalseButton = (Button) findViewById(R.id.buttonFalse);
        ImageButton mNextButton = (ImageButton) findViewById(R.id.buttonNext);
        ImageButton mPrevButton = (ImageButton) findViewById(R.id.buttonPrev);
        mTextQuiz = (TextView) findViewById(R.id.TextQuiz);
        mTrueButton.setOnClickListener(view -> checkAnswer(true));
        mFalseButton.setOnClickListener(view -> checkAnswer(false));
        mNextButton.setOnClickListener(view -> {
            setNextQuestion();
        });
        mPrevButton.setOnClickListener(view -> {
            setNextQuestion();
        });
    }

    private void setNextQuestion(){
        Optional<Question> newQuestion = mQuestionBank
                .stream()
                .filter(q -> !q.isAnswered() && !q.equals(mQuestionBank.get(mCurrentIndex)))
                .findAny();

        if (newQuestion.isPresent()) {
            mCurrentIndex = mQuestionBank.indexOf(newQuestion.get());
            int questionResId = mQuestionBank.get(mCurrentIndex).getQuestionResId();
            mTextQuiz.setText(questionResId);
        } else {
            mTextQuiz.setText(R.string.no_more_questions);
        }

        mTextQuiz.setTextColor(Color.BLACK);
    }

    private void updateAnswer(boolean isAnswerCorrect) {
        int answerResId = mQuestionBank.get(mCurrentIndex).getAnswerResId();
        mTextQuiz.setText(answerResId);
        int answerColor = isAnswerCorrect ? Color.GREEN : Color.RED;
        mTextQuiz.setTextColor(answerColor);
    }

    private void checkAnswer(boolean userPressedTrue) {
        Question currentQuestion = mQuestionBank.get(mCurrentIndex);

        if (currentQuestion.isAnswered()) {
            setNextQuestion();
            return;
        }

        boolean userAnsweredCorrectly = userPressedTrue == currentQuestion.isAnswerTrue();
        int messageResId = userAnsweredCorrectly ? R.string.toast_true : R.string.toast_false;

        int currentCounterValue = Integer.parseInt(mQuizCounter.getText().toString());
        int newValue = userAnsweredCorrectly ? currentCounterValue + 1 : 0;

        mQuizCounter.setText(String.format(Locale.getDefault(),"%d", newValue));

        currentQuestion.setAnswered();
        updateAnswer(userAnsweredCorrectly);

        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0); // not working after API 30
        toast.show();
    }
}