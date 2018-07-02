package jp.co.atschool.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    //問題番号
    TextView countTextView;
    //問題文
    TextView contentTextView;

    Button[] optionButtons;

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz = (Quiz)getIntent().getSerializableExtra("QUIZ");

        countTextView = (TextView) findViewById(R.id.countText2);
        contentTextView = (TextView) findViewById(R.id.contentTextView);

        optionButtons = new Button[3];
        optionButtons[0] = findViewById(R.id.option1);
        optionButtons[1] = findViewById(R.id.option2);
        optionButtons[2] = findViewById(R.id.option3);

        quiz.createQuiz();
        showQuiz();
    }

    void showQuiz(){
        //問題の記号列
        quiz.setContentAndAnswer();

        countTextView.setText("第"+ ( quiz.contentIndex + 1 ) + "問");
        contentTextView.setText(quiz.content);
        optionButtons[0].setText(quiz.options[0]);
        optionButtons[1].setText(quiz.options[1]);
        optionButtons[2].setText(quiz.options[2]);

        for(Button button: optionButtons){
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        //ボタンの値の取得
        Button clickedButton = (Button) v;

        // インフレータを取得する
        LayoutInflater inflater = getLayoutInflater();
        // カスタムToast用のViewを取得する
        View layout = inflater.inflate(R.layout.toast_layout, null);

        // Toastにカスタマイズしたレイアウトを設定して表示する
        Toast toast = new Toast(this);
        toast.setView(layout);
        toast.show();

        if (TextUtils.equals(clickedButton.getText(), quiz.answer)) {
            // 次の問題へ。
            quiz.contentIndex++;
            // ImageViewを取得して任意のイメージを設定する
            ImageView image = (ImageView) layout.findViewById(R.id.image);
            image.setImageResource(R.mipmap.result_maru);

            // TextViewを取得して任意のテキストを設定する
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("正解");

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("QUIZ", quiz);
            // startActivityで、Intentの内容を発行します。
            startActivity(intent);
        }else{
            // ImageViewを取得して任意のイメージを設定する
            ImageView image = (ImageView) layout.findViewById(R.id.image);
            image.setImageResource(R.mipmap.result_batu);
            // TextViewを取得して任意のテキストを設定する
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("はずれ\n"+ "正解は" + quiz.answer);
            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("QUIZ", quiz);
            // startActivityで、Intentの内容を発行します。
            startActivity(intent);
        }
    }
}
