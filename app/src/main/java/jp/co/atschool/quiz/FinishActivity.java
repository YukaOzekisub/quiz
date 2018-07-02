package jp.co.atschool.quiz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends Activity implements View.OnClickListener {

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        quiz = (Quiz)getIntent().getSerializableExtra("QUIZ");

        TextView contentIndexView = (TextView) findViewById(R.id.contentIndexView2);
        contentIndexView.setText("" + quiz.contentIndex);

        TextView resultView = (TextView) findViewById(R.id.resultView);

        String result = "";

        if(quiz.contentIndex  < 5){ result = "まだまだいける！"; }
        if(5 <= quiz.contentIndex && quiz.contentIndex  < 10){ result = "惜しかったね"; }
        if(10 <= quiz.contentIndex && quiz.contentIndex  < 15){ result = "なかなかやるな"; }
        if(15 <= quiz.contentIndex && quiz.contentIndex  < 20){ result = "すごい!すごい!"; }
        if(20 <= quiz.contentIndex && quiz.contentIndex  < 30){ result = "おめでとう！"; }
        if(30 <= quiz.contentIndex ){ result = "おめでとう！素晴らしい記憶力だね"; }

        resultView.setText(result);

        Button startButton = (Button) findViewById(R.id.startButton2);
        startButton.setOnClickListener(this);

        SharedPreferences prefer = getSharedPreferences("user_data", MODE_PRIVATE);

        SharedPreferences pref = getSharedPreferences("user_data", MODE_PRIVATE);
        int maxPoint = pref.getInt("maxPoint", 0);
        if(maxPoint < quiz.contentIndex) {
            SharedPreferences.Editor editor = prefer.edit();
            editor.putInt("maxPoint", quiz.contentIndex);
            editor.commit();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, TopActivity.class);
        // startActivityで、Intentの内容を発行します。
        startActivity(intent);
    }
}
