package com.example.calculatorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button press0;
    Button press1;
    Button press2;
    Button press3;
    Button press4;
    Button press5;
    Button press6;
    Button press7;
    Button press8;
    Button press9;
    Button divide;
    Button multiply;
    Button subtract;
    Button addition;
    Button fullStop;
    Button addSub;
    Button clear;
    Button clearList;
    Button equalTo;

    TextView input;
    TextView output;

    Button[] buttons;

    String inputText = "";
    String currentNum = "";

    float numFirst = 0;
    float numSecond = 0;
    int cmd = -1;
    float answer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }
    @Override
    public void onClick(View v) {
        int i = getIndex(v);
        if(i > -1){
            Entry(i);
        }
        input.setText(inputText);
    }
    private void Entry(int i){
        if(i == 11){
            if(currentNum.charAt(0) == '-'){
                currentNum = currentNum.substring(1);
            }
            else{
                currentNum = "-" + currentNum;
            }
        }
        else if(i < 16) {
            inputText += buttons[i].getText().toString();

            if(i > 11){
                cmd = i;
                currentNum = "";
            }
            else{ // Numbers
                currentNum +=  buttons[i].getText().toString();
            }
        }
        else if(i == 16){
            if(inputText.length()>0){
                inputText = inputText.substring(0, inputText.length()-1);
            }
            if(currentNum.length()>0){
                currentNum = currentNum.substring(0, currentNum.length()-1);
            }
        }
        else if (i == 17){
            inputText = "";
            output.setText("");
            currentNum = "";
            answer = 0f;
            numFirst = 0f;
            numSecond = 0f;
            cmd = -1;
        }
        else if (i == 18){
            calculate();
            currentNum = "";
        }

        if(!currentNum.equals("") && cmd != -1){
            numSecond = Float.parseFloat(currentNum);
        }
        else if(!currentNum.equals("") && cmd == -1){
            numFirst = Float.parseFloat(currentNum);
        }

        Log.i("current",currentNum);
        Log.i("first",String.valueOf(numFirst));
        Log.i("second",String.valueOf(numSecond));
        Log.i("answer",String.valueOf(answer));
    }

    private void calculate(){
        answer = numFirst;
        try {
            if (cmd == 12) {
                answer = numFirst / numSecond;
            } else if (cmd == 13) {
                answer = numFirst * numSecond;
            } else if (cmd == 14) {
                answer = numFirst - numSecond;
            } else if (cmd == 15) {
                answer = numFirst + numSecond;
            }
            output.setText(String.valueOf(answer));
        }
        catch (Exception e){
            output.setText(R.string.error);
        }
        numFirst = answer;
    }
    private int getIndex(View v){
        for(int i = 0; i < buttons.length; i++){
            if(v.getId() == buttons[i].getId()){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void onSaveInstanceState(Bundle bum) {
        super.onSaveInstanceState(bum);
        bum.putString("inputString", input.getText().toString());
        bum.putString("outputString", output.getText().toString());
        bum.putString("inputText", inputText);
        bum.putFloat("answer", answer);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        input.setText(savedInstanceState.getString("inputString"));
        output.setText(savedInstanceState.getString("outputString"));
        inputText = savedInstanceState.getString("inputText");
        answer = savedInstanceState.getFloat("answer");
    }
    private void setUp(){
        press7 = (Button) findViewById(R.id.r0c0);
        press8 = (Button) findViewById(R.id.r0c1);
        press9 = (Button) findViewById(R.id.r0c2);
        divide = (Button) findViewById(R.id.r0c3);
        press4 = (Button) findViewById(R.id.r1c0);
        press5 = (Button) findViewById(R.id.r1c1);
        press6 = (Button) findViewById(R.id.r1c2);
        multiply = (Button) findViewById(R.id.r1c3);
        press1 = (Button) findViewById(R.id.r2c0);
        press2 = (Button) findViewById(R.id.r2c1);
        press3 = (Button) findViewById(R.id.r2c2);
        subtract = (Button) findViewById(R.id.r2c3);
        fullStop = (Button) findViewById(R.id.r3c0);
        press0 = (Button) findViewById(R.id.r3c1);
        addSub = (Button) findViewById(R.id.r3c2);
        addition = (Button) findViewById(R.id.r3c3);
        clear = (Button) findViewById(R.id.r4c0);
        clearList = (Button) findViewById(R.id.r4c1);
        equalTo = (Button) findViewById(R.id.r4c2);
        input = (TextView) findViewById(R.id.input);
        input.setMaxLines(3);
        output = (TextView) findViewById(R.id.output);
        output.setMaxLines(2);
        buttons = new Button[]{press0, press1, press2, press3, press4, press5, press6, press7, press8, press9, fullStop, addSub, divide, multiply, subtract, addition, clearList, clear, equalTo};
        for(Button butt: buttons){
            butt.setOnClickListener(this);
        }
    }
}