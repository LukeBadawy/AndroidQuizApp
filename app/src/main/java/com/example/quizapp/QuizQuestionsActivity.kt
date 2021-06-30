package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 0
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestions()

        setQuestion()

        tv_answer_one.setOnClickListener(this)
        tv_answer_two.setOnClickListener(this)
        tv_answer_three.setOnClickListener(this)
        tv_answer_four.setOnClickListener(this)

        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion() {

        val question: Question? = mQuestionsList!![mCurrentPosition]

        defaultAnswerView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }

        progress_bar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition / ${progress_bar.max}"

        tv_question.text = question!!.question
        iv_question_image.setImageResource(question.image)
        tv_answer_one.text = question.answerOne
        tv_answer_two.text = question.answerTwo
        tv_answer_three.text = question.answerThree
        tv_answer_four.text = question.answerFour
    }

    private fun defaultAnswerView() {
        val answers = ArrayList<TextView>()
        answers.add(0, tv_answer_one)
        answers.add(1, tv_answer_two)
        answers.add(2, tv_answer_three)
        answers.add(3, tv_answer_four)

        for (answer in answers) {
            answer.setTextColor(Color.parseColor("#7A8089"))
            answer.typeface = Typeface.DEFAULT
            answer.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_answer_one -> {
                selectedAnswerView(tv_answer_one, 1)
            }
            R.id.tv_answer_two -> {
                selectedAnswerView(tv_answer_two, 2)
            }
            R.id.tv_answer_three -> {
                selectedAnswerView(tv_answer_three, 3)
            }
            R.id.tv_answer_four -> {
                selectedAnswerView(tv_answer_four, 4)
            }
            R.id.btn_submit -> {
                if (mSelectedOption == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition + 1 <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition)

                    // Users answer was incorrect
                    if (question!!.correctAnswer != mSelectedOption) {
                        answerView(mSelectedOption, R.drawable.wrong_option_border_bg)
                    } else {
                        // Users answer was correct, Add one to correct answers
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition + 1 == mQuestionsList!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "NEXT QUESTION"
                    }

                    mSelectedOption = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_answer_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_answer_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_answer_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tv_answer_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

        }
    }

    private fun selectedAnswerView(selectedTv: TextView, selectedOptionNum: Int) {
        defaultAnswerView()
        mSelectedOption = selectedOptionNum

        selectedTv.setTextColor(Color.parseColor("#363A43"))
        selectedTv.setTypeface(selectedTv.typeface, Typeface.BOLD)
        selectedTv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}