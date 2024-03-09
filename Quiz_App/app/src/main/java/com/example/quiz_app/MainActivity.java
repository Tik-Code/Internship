package com.example.quiz_app;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextview;
    TextView TotalQuestionTextview;
    Button ansA,ansB,ansC,ansD;
    Button btn_submit;

    int score=0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex =0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TotalQuestionTextview = findViewById(R.id.Total_Question);
        questionTextview = findViewById(R.id.Question);
        ansA=findViewById(R.id.answer_a);
        ansB=findViewById(R.id.answer_b);
        ansC=findViewById(R.id.answer_c);
        ansD=findViewById(R.id.answer_d);
        btn_submit= findViewById(R.id.btn_sub);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        TotalQuestionTextview.setText("Total question:"+totalQuestion);

        loadNewQuestion();
    }

    private void loadNewQuestion(){
        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
        questionTextview.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer="";

    }

    private void finishQuiz(){
        String passStatus;
        if (score>=totalQuestion*0.6){
            passStatus="passed";
        }
        else {
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is"+score+"out of"+totalQuestion)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz() ))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view){
        ansA.setBackgroundColor(Color.GRAY);
        ansB.setBackgroundColor(Color.GRAY);
        ansC.setBackgroundColor(Color.GRAY);
        ansD.setBackgroundColor(Color.GRAY);

        Button clickedButton = (Button) view;

        if (clickedButton.getId()== R.id.btn_sub){
            if (!selectedAnswer.isEmpty()){
                if (selectedAnswer.equals(QuestionAnswer.correctAns[currentQuestionIndex] )){
                    score++;
                }
                else {
                    clickedButton.setBackgroundColor(Color.BLUE);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }
            else
            {

            }
        }
        else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GREEN);
        }
    }
}
