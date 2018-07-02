package jp.co.atschool.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity implements View.OnClickListener {

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //問題の記号列生成
        quiz = (Quiz)getIntent().getSerializableExtra("QUIZ");
        quiz.createQuestion();

        //問題の表示
        TextView countTextView = (TextView) findViewById(R.id.countText1);
        TextView questionLine = (TextView) findViewById(R.id.questionLine);
        String questionText = "";
        for (String object:
             quiz.objectList) {
            questionText += object;
        }

        countTextView.setText("第" + ( quiz.contentIndex + 1 ) + "問");
        questionLine.setText(questionText);
        Button startButton = (Button) findViewById(R.id.questionStart);
        startButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("QUIZ", quiz);
        // startActivityで、Intentの内容を発行します。
        startActivity(intent);
    }
}
