package com.example.plant;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button buttonQuest, buttonReview, buttonLogout;
    private TextView textDay,textLevel, textCoin;
    private FirebaseAuth firebaseAuth;
    private ImageView plant;
    private int day, level, coin,levelNum;
    private boolean test;
    public static Context ct;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat mFormat=new SimpleDateFormat("yyyy/MM/dd");
        String time=mFormat.format(date);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mainmusic); // 메인 음악 틀기
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        String uID=firebaseAuth.getUid();
        SharedPreferences sf = getSharedPreferences(uID,MODE_PRIVATE);
        test = sf.getBoolean("test",false);
        day=sf.getInt("day",1);
        level=sf.getInt("level",1);
        coin=sf.getInt("coin",0);
        String recentDay=sf.getString("recentDay","");

        if(!recentDay.equals(time)&&test){
            day++;
            test=false;
        }

        textDay=(TextView) findViewById(R.id.day);
        textLevel=(TextView) findViewById(R.id.level);
        textCoin=(TextView) findViewById(R.id.coin);
        buttonQuest = (Button) findViewById(R.id.buttonQuest);
        buttonReview = (Button) findViewById(R.id.buttonRev);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        plant = (ImageView) findViewById(R.id.plant);

        if(level==1){
            textLevel.setText("Level1 씨앗");
            plant.setImageResource(R.drawable.level1);
        }else if(level==2){
            textLevel.setText("Level2 새싹");
            plant.setImageResource(R.drawable.level2);
        }else if(level==3){
            textLevel.setText("Level3 꽃");
            plant.setImageResource(R.drawable.level3);
        }else if(level==4){
            textLevel.setText("Level4 열매");
            plant.setImageResource(R.drawable.level4);
        }
        textCoin.setText(String.valueOf(coin));
        textDay.setText("Day"+day);

        SharedPreferences sharedf = getSharedPreferences(uID,MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedf.edit();
        editor.putInt("day",day);
        editor.putBoolean("test",test);
        editor.putString("recentDay",time);
        editor.putInt("level",level);
        editor.commit();

        Intent intent = new Intent(MainActivity.this, NewAppWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        MainActivity.this.sendBroadcast(intent);


        buttonQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!test) {
                    Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("day", day);
                    b.putInt("level", level);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"오늘의 문제를 다 풀었습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReviewActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.clearUserName(MainActivity.this); //저장한거 없애기

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(MainActivity.this, FirstAuthActivity.class);
                startActivity(I);
                finish();
            }
        });
        ct = this;
    }
    public void levelUp(View o){
        if(level==1){
            if(coin>=10){
                level++;
                coin-=10;
                textCoin.setText(String.valueOf(coin));
                textLevel.setText("Level2 새싹");
                plant.setImageResource(R.drawable.level2);
                Toast.makeText(getApplicationContext(),"Level UP! 싹이 자랐네요!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"코인이 부족합니다.", Toast.LENGTH_LONG).show();
            }
        }
        else if(level==2){
            if(coin>=20){
                level++;
                coin-=20;
                textCoin.setText(String.valueOf(coin));
                textLevel.setText("Level3 꽃");
                plant.setImageResource(R.drawable.level3);
                Toast.makeText(getApplicationContext(),"Level UP! 꽃이 피었어요!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"코인이 부족합니다.", Toast.LENGTH_LONG).show();
            }
        }
        else if(level==3){
            if(coin>=30){
                level++;
                coin-=30;
                textCoin.setText(String.valueOf(coin));
                textLevel.setText("Level4 열매");
                plant.setImageResource(R.drawable.level4);
                Toast.makeText(getApplicationContext(),"Level UP! 열매가 생겼어요!", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(getApplicationContext(),"코인이 부족합니다.", Toast.LENGTH_LONG).show();
            }
        }
        else if(level==4){
            Toast.makeText(getApplicationContext(),"더이상 자랄 수 없어요.", Toast.LENGTH_LONG).show();
        }
        firebaseAuth = FirebaseAuth.getInstance();
        String uID=firebaseAuth.getUid();
        SharedPreferences sf = getSharedPreferences(uID,MODE_PRIVATE);
        SharedPreferences.Editor editor= sf.edit();
        editor.putInt("coin",coin);
        editor.putInt("level",level);
        editor.commit();
    }

}
