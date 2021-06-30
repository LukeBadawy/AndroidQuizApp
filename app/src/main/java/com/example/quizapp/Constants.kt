package com.example.quizapp

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        var questionList = ArrayList<Question>()

        val question1 =
            Question(
                1,
                "Which video game hero is this",
                R.drawable.ic_question_image_link,
                "Zelda",
                "Link",
                "Ganon",
                "Hyrule",
                2
            )

        questionList.add(question1)

        val question2 =
            Question(
                1,
                "Which company owns this logo",
                R.drawable.ic_question_image_xbox,
                "Xbox",
                "Sony",
                "Microsoft",
                "Nintendo",
                3
            )

        questionList.add(question2)

        val question3 =
            Question(
                1,
                "Who is beshulet",
                R.drawable.ic_question_image_question_mark2,
                "John",
                "Adele",
                "Natalie",
                "Roman",
                3
            )

        questionList.add(question3)

        val question4 =
            Question(
                1,
                "What is the capital city of Switzerland",
                R.drawable.ic_question_image_question_mark2,
                "Zalbamont",
                "Uster",
                "Berlin",
                "Zulric",
                4
            )

        questionList.add(question4)

        val question5 =
            Question(
                1,
                "How many rotations per year does earths moon make",
                R.drawable.ic_question_image_question_mark2,
                "The moon does not rotate",
                "364",
                "1",
                "700",
                1
            )

        questionList.add(question5)

        return questionList
    }
}