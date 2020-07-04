package com.example.plant;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = QuestionActivity.class.getSimpleName();
    private ArrayList<Question> wrongList= new ArrayList<>();
    private ArrayList<Question> questionList;
    private int questionID ;
    private int quest=1;
    private int right=0;
    private int wrong=0;
    private Question currentQ;
    private TextView textDay, textQuestN, textLevel;
    private Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    QuestionDB db=new QuestionDB(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Bundle b=getIntent().getExtras();
        int day=b.getInt("day");
        int level=b.getInt("level");
        int index=(day-1)*10;
        questionList=db.getQuestions(index);
        currentQ=questionList.get(questionID);

        textDay = (TextView) findViewById(R.id.day);
        textQuestN = (TextView) findViewById(R.id.quest);
        textLevel = (TextView) findViewById(R.id.level);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        textDay.setText("Day " + day);
        textQuestN.setText("Q "+ quest);
        textLevel.setText("Level "+level);
        setQuestionView();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
                getAnswer("A");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer("B");
            }
        });


    }

    private void getAnswer(String AnswerString){
        if(currentQ.getANSWER().equals(AnswerString)){
            right++;
        }
        else{
            wrong++;
            wrongList.add(currentQ);
        }
        if(questionID<10){
            quest++;
            currentQ = questionList.get(questionID);
            setQuestionView();
            textQuestN.setText("Q "+ quest);
        }
        else{
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("right", right);
            b.putInt("wrong",wrong);
            intent.putExtras(b);
            intent.putExtra("list",  wrongList);
            startActivity(intent);
            finish();
        }
    }
    public void main(View o){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void setQuestionView() {
        // the method which will put all things together
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());

        questionID++;
    }
}