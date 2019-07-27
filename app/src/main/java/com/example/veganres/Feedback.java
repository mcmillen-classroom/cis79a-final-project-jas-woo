package com.example.veganres;

class Feedback
{
    private int mFeedbackResId;

    public Feedback(int feedbackResId) {
        mFeedbackResId = feedbackResId;
    }

    public int getFeedbackResId()
    {
        return mFeedbackResId;
    }

    public void setFeedbackResId(int feedbackResId)
    {
        mFeedbackResId = feedbackResId;
    }
}
