package com.example.plant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    private Question currentQ;

    private TextView textGetCoin, textRight, textWrong;
    private ArrayList<Question> wrongList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        firebaseAuth = FirebaseAuth.getInstance();
        String uID=firebaseAuth.getUid();

        SharedPreferences sharedf = getSharedPreferences(uID,MODE_PRIVATE);
        int coin = sharedf.getInt("coin",0);

        Bundle b=getIntent().getExtras();
        int wrong=b.getInt("wrong");
        int right=b.getInt("right");

//        Intent intent=getIntent();
//        wrongList=(ArrayList<Question>) intent.getSerializableExtra("list");

        SharedPreferences sf = getSharedPreferences(uID,MODE_PRIVATE);
        SharedPreferences.Editor editor= sf.edit();
        editor.putInt("coin",right+coin);
        editor.putBoolean("test",true);
        editor.putInt("wrong",wrong);
        editor.commit();

        Intent intent=getIntent();
        wrongList=(ArrayList<Question>) intent.getSerializableExtra("list");

        int size=wrongList.size();
        for(int i=0;i<size;i++){
            currentQ=wrongList.get(i);
            Map<String, Object> quest=new HashMap<>();
            quest.put("optA",currentQ.getOPTA());
            quest.put("optB",currentQ.getOPTB());
            quest.put("answer",currentQ.getANSWER());
            databaseReference.child("users").child(uID).child("quest"+i).updateChildren(quest);
        }

        textGetCoin=(TextView)findViewById(R.id.textGetCoin);
        textRight=(TextView)findViewById(R.id.textRight);
        textWrong=(TextView)findViewById(R.id.textWrong);

        textGetCoin.setText("코인 "+right+"개 획득");
        textRight.setText("맞은 개수 : "+right+"개");
        textWrong.setText("틀린 개수 : "+wrong+"개");
    }
    public void review(View o){
        Intent intent=new Intent(this, ReviewActivity.class);
        intent.putExtra("list",  wrongList);
        startActivity(intent);
        finish();
    }
    public void main(View o){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
