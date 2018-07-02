package jp.co.atschool.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nttr on 2017/12/07.
 */

public class Quiz implements Serializable {
    //問題
    String content;
    //選択肢
    String[] options;
    //解答
    String answer;

    ArrayList<String> objectList = new ArrayList<>();

    ArrayList<String> contentList = new ArrayList<>();
    ArrayList<String> answerList = new ArrayList<>();

    int contentIndex;

    int object_num = 3;

    public void addContentList(String content, int answer_index) {
        this.contentList.add(content);
        this.answerList.add(this.objectList.get(answer_index));
    }

    void createQuiz() {
        //問題の作成
        for (int i = 0; i < object_num; i++) {
            addContentList("左から" + (i + 1) + "番目の記号は？", i);
            addContentList("右から" + (i + 1) + "番目の記号は？", object_num - 1 - i);
        }
    }

    public void createQuestion(){
        reset();

        options = new String[3];
        this.options[0] = "○";
        this.options[1] = "△";
        this.options[2] = "□";

        Random random = new Random();
        for(int i = 0; i < object_num; i++) {
            int num = random.nextInt(3);
            objectList.add(this.options[num]);
        }
    }

    public void setContentAndAnswer(){
        Random random = new Random();
        int question_index = random.nextInt(object_num);
        content = contentList.get(question_index);
        answer = answerList.get(question_index);
    }

    public void start(){
        contentIndex = 0;
        reset();
    }

    public void reset(){
        if(0 < contentIndex && (contentIndex % 5 ) == 0){
            object_num++;
        }
        if(object_num > 20){
            object_num = 20;
        }
        objectList.clear();
        contentList.clear();
        answerList.clear();
        content = "";
        answer = "";
    }
}
