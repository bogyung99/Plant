package com.example.plant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class ReviewActivity extends AppCompatActivity {
    private TextView textwrong,textQuest;
    private int questionID=0;
    private Button button1, button2;
    private ImageButton buttonPre,buttonNext;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        textwrong=(TextView)  findViewById(R.id.wrong);
        textQuest=(TextView) findViewById(R.id.quest);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        buttonPre=(ImageButton) findViewById(R.id.imageButton1);
        buttonNext=(ImageButton) findViewById(R.id.imageButton2);


        firebaseAuth = FirebaseAuth.getInstance();
        final String uID=firebaseAuth.getUid();
        SharedPreferences sf = getSharedPreferences(uID,MODE_PRIVATE);
        final int wrong = sf.getInt("wrong",0);
        textwrong.setText("틀린개수 : "+wrong+"개");
        getUser(uID,questionID);

        buttonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
                if(questionID>=1){
                    questionID--;
                    getUser(uID, questionID);
                }
                else{
                    Toast.makeText(getApplicationContext(),"첫번째 문제입니다.",Toast.LENGTH_LONG).show();
                }

            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing the button text to other method
                // to check whether the anser is correct or not
                // same for all three buttons
                if(questionID<wrong-1){
                    questionID++;
                    getUser(uID,questionID);
                }
                else{
                    Toast.makeText(getApplicationContext(),"마지막 문제입니다.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void main(View o){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void getUser(String uid,int questionID ){
        databaseReference.child("users").child(uid).child("quest"+questionID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String,String> quest= (HashMap<String, String>) dataSnapshot.getValue();
                button1.setText(quest.get("optA"));
                button2.setText(quest.get("optB"));
                textQuest.setText("답 : "+quest.get("answer"));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("getFirebaseDatabase","loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}
