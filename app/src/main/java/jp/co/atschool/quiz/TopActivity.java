package jp.co.atschool.quiz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TopActivity extends Activity implements View.OnClickListener {

    Quiz quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(this);

        quiz = new Quiz();
        quiz.start();

        SharedPreferences pref = getSharedPreferences("user_data", MODE_PRIVATE);
        int maxPoint = pref.getInt("maxPoint", 0);
        TextView contentIndexView = (TextView) findViewById(R.id.maxPointView);
        contentIndexView.setText("最高 " + maxPoint + " 問");

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        // startActivityで、Intentの内容を発行します。
        intent.putExtra("QUIZ", quiz);
        startActivity(intent);
    }
}
