package com.example.plant;

import android.app.Activity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Question extends Activity implements Serializable {
    private int ID;
    private String OPTA;
    private String OPTB;
    private String ANSWER;


    public Question(){
        ID=0;
        OPTA="";
        OPTB="";
        ANSWER="";

    }
    public Question(String OPTA, String OPTB, String ANSWER ){
        this.OPTA=OPTA;
        this.OPTB=OPTB;
        this.ANSWER=ANSWER;
    }
    public int getID() {
        return ID;
    }
    public String getOPTA() {
        return OPTA;
    }
    public String getOPTB() {
        return OPTB;
    }
    public String getANSWER() {
        return ANSWER;
    }
    public void setID(int id) {
        ID = id;
    }
    public void setOPTA(String  OPTA) {
        this.OPTA = OPTA;
    }
    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }
    public void setANSWER(String ANSWER) { this.ANSWER = ANSWER;}
}
