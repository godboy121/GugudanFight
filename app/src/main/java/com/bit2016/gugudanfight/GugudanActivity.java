package com.bit2016.gugudanfight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;



public class GugudanActivity extends AppCompatActivity {
    Button btn[]=new Button[9];//9개 버튼을 배열화
    Integer[] button={R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};//각각 선언

    private static final int TIME_LIMIT=10;
    private static final String TAG="AAA";//로그찍기위해 선언해놓음
    private Timer timer=new Timer();
    private TextView textView;
    private TextView textView2;



    public void show(){
        Random random = new Random();
        int dan=random.nextInt(8)+2;
        int num=random.nextInt(9)+1;
        int value=dan*num;
        textView2=(TextView)findViewById(R.id.textView5);
        textView2.setText(dan+"*"+num+"=");
       hash_set(value);





    }

    public void hash_set(int value){//버튼에 답이 포함된 수를 넣어주는 함수

        Set<Integer> set=new HashSet<Integer>();//hash는 중복된 값을 넣어도 안넣어진다
        set.add(value);//초기 답을 먼저 hash에 저장
        while(set.size()<button.length){//버튼 갯수만큼 돌리고
            Random random = new Random();
           int value1=random.nextInt(80)+2;//난수생성
            set.add(value1);//hash에 저장

        }

        Object[] obj=set.toArray();//obj에 hash값을 배열형태로 저장
        List<Integer> list= new ArrayList<Integer>();//list생성


        for(int i=0;i<button.length;i++){
            list.add((int)obj[i]);//리스트에 hash에 저장된 순서대로 추가
        }
        Collections.shuffle(list);//무작위로 섞는다

        for(int i=0;i<9;i++){
            btn[i]=(Button)findViewById(button[i]);
            btn[i].setText(""+list.get(i));
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gugudan);
        int value;
        show();


        textView=(TextView)findViewById(R.id.textView3);
        timer.schedule(new gugudanTimeTask(),1000,1000);


    }

/////////////////////////타이머//////////////////////////////////////////////////////////////////
    private void update_lastTime(int seconds){
        textView.setText(""+(TIME_LIMIT-seconds));//남은시간 //""를 넣어주어 스트링으로 변환시켜줘야한다.
    }


    private class gugudanTimeTask extends TimerTask{
        private int seconds=0;

        @Override
        public void run() {
            seconds++;
            if(seconds>=TIME_LIMIT){
                timer.cancel();
                Intent intent = new Intent(GugudanActivity.this, ResultActivity.class);
                startActivity(intent);
                finish();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    update_lastTime(seconds);
                }
            });

        }
    }
}
