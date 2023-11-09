package com.example.geoquiz;

public class Question {
    private int mQuestionResId;

    private int mAnswerResId;

    private boolean mAnswerTrue;

    private boolean mAnsweredTrue = false;

    public Question(int questionResId, int answerResId, boolean answerTrue) {
        mQuestionResId = questionResId;
        mAnswerResId = answerResId;
        mAnswerTrue = answerTrue;
    }

    public int getQuestionResId() {
        return mQuestionResId;
    }

    public void setQuestionResId(int textResId) {
        mQuestionResId = textResId;
    }

    public int getAnswerResId() {
        return mAnswerResId;
    }

    public void setAnswerResId(int textResId) {
        mAnswerResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswered() {
        return mAnsweredTrue;
    }

    public void setAnswered() {
        mAnsweredTrue = true;
    }
}
