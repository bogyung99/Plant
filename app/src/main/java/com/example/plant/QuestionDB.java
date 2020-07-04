package com.example.plant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class QuestionDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spelling";
    private static final String TABLE_QUEST = "quest";
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "qid";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";

    private SQLiteDatabase database;

    public QuestionDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public QuestionDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_ANSWER + " TEXT)";
        db.execSQL(sql);
        addQuestion();
    }

    private void addQuestion() {
        Question q1 = new Question("A.이음매 없는 물안경", "B.이음새 없는 물안경", "A");
        addQuestion(q1);
        Question q2 = new Question("A.드디어 선수 교체가 이루어졌다.", "B.드디어 선수 교채가 이루어졌다.", "A");
        addQuestion(q2);
        Question q3 = new Question("A.마을의 골치거리가 되고 있다.", "B.마을의 골칫거리가 되고 있다.", "B");
        addQuestion(q3);
        Question q4 = new Question("A.반죽을 넓적하게 펴다.", "B.반죽을 넙적하게 펴다.", "A");
        addQuestion(q4);
        Question q5 = new Question("A.좁다란 골목을 지나면 된다.", "B.좁따란 골목을 지나면 된다.", "A");
        addQuestion(q5);
        Question q6 = new Question("A.치맛단을 스치다.", "B.치맛단을 시치다.", "B");
        addQuestion(q6);
        Question q7 = new Question("A.솜사탕이 달디달다.", "B.솜사탕이 다디달다.", "B");
        addQuestion(q7);
        Question q8 = new Question("A.속속들이 뒤지다.", "B.속속이 뒤지다.", "A");
        addQuestion(q8);
        Question q9 = new Question("A.서점에 들렸다 바로 갈게.", "B.서점에 들렀다 바로 갈게.", "B");
        addQuestion(q9);
        Question q10 = new Question("A.괜히 돌멩이를 발로 찼다.", "B.괜히 돌맹이를 발로 찼다.", "A");
        addQuestion(q10);

        Question q11 = new Question("A.수익이 꽤 짭짤하다.", "B.수익이 꽤 짭잘하다.", "A");
        addQuestion(q11);
        Question q12 = new Question("A.아둥바둥 살고있다.", "B.아등바등 살고있다.", "B");
        addQuestion(q12);
        Question q13 = new Question("A.녹슨 못", "B.녹슬은 못", "A");
        addQuestion(q13);
        Question q14 = new Question("A.깨방정 그만떨어!", "B.개방정 그만떨어!", "B");
        addQuestion(q14);
        Question q15 = new Question("A.시끌벅쩍한 잔치가 열렸다.", "B.시끌벅적한 잔치가 열렸다.", "B");
        addQuestion(q15);
        Question q16 = new Question("A.생각보다 금새 괜찮아졌다.", "B.생각보다 금세 괜찮아졌다.", "B");
        addQuestion(q16);
        Question q17 = new Question("A.모자란 돈은 아르바이트로 마련했어요.", "B.모자른 돈은 아르바이트로 마련했어요.", "A");
        addQuestion(q17);
        Question q18 = new Question("A.산소량이 점점 줄어든다.", "B.산소양이 점점 줄어든다.", "A");
        addQuestion(q18);
        Question q19 = new Question("A.손톱깎이로 손톱을 잘라냈다.", "B.손톱깍기로 손톱을 잘라냈다.", "A");
        addQuestion(q19);
        Question q20 = new Question("A.네 얼굴 보기가 창피하다.", "B.네 얼굴 보기가 챙피하다.", "A");
        addQuestion(q20);

        Question q21 = new Question("A.감동적인 머릿말을 읽었어요.", "B.감동적인 머리말을 읽었어요.", "B");
        addQuestion(q21);
        Question q22 = new Question("A.편지 봉투에 우표를 붙이다.", "B.편지 봉투에 우표를 붙히다.", "A");
        addQuestion(q22);
        Question q23 = new Question("A.염불보다 잿밥", "B.염불보다 젯밥", "A");
        addQuestion(q23);
        Question q24 = new Question("A.혈혈단신의 몸으로 상경했다", "B.홀홀단신의 몸으로 상경했다.", "A");
        addQuestion(q24);
        Question q25 = new Question("A.그 일은 제가 맡아서 할께요", "B.그 일은 제가 맡아서 할게요.", "B");
        addQuestion(q25);
        Question q26 = new Question("A.오늘은 구름양이 많다", "B.오늘은 구름량이 많다.", "A");
        addQuestion(q26);
        Question q27 = new Question("A.정답을 알아맞쳤다.", "B.정답을 알아맞혔다.", "B");
        addQuestion(q27);
        Question q28 = new Question("A.추워서 입술이 퍼래요.", "B.추워서 입술이 퍼레요.", "B");
        addQuestion(q28);
        Question q29 = new Question("A.해코지라도 하려는 거야?", "B.해꼬지라도 하려는 거야?", "A");
        addQuestion(q29);
        Question q30 = new Question("A.아이의 볼은 붉은빛을 띤다.", "B.아이의 볼을 붉은빛을 띈다.", "A");
        addQuestion(q30);
    }

    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        // Inserting Row
        database.insert(TABLE_QUEST, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        onCreate(db);
    }



    public ArrayList<Question> getQuestions(int day) {
        ArrayList<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToPosition(day)) {
            for (int i = 0; i < 10; i++) {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setOPTA(cursor.getString(1));
                quest.setOPTB(cursor.getString(2));
                quest.setANSWER(cursor.getString(3));
                quesList.add(quest);
                cursor.moveToNext();
            }
        }
        // return quest list
        return quesList;
    }
}