package ca.we_love_different_things.bubbletea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jiashu on 2017-09-16.
 */

public class Controller extends AppCompatActivity {

    public final static String EXTRA_SCORE = "ca.we_love_different_things.bubbletea.SCORE";

    private OrderModel model = new OrderModel();

    private Button mIngredientButton0;
    private Button mIngredientButton1;
    private Button mIngredientButton2;
    private Button mIngredientButton3;
    private ProgressBar progressBar;
    private TextView message;
    private TextView score;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mIngredientButton0 = (Button) findViewById(R.id.button0);
        mIngredientButton1 = (Button) findViewById(R.id.button1);
        mIngredientButton2 = (Button) findViewById(R.id.button2);
        mIngredientButton3 = (Button) findViewById(R.id.button3);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        message = (TextView) findViewById(R.id.message);
        score = (TextView) findViewById(R.id.score);
        timer = new Timer();

        setNewOrder();
        setButtons(model.getButtons());

        progressBarTimer();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    /**
     * Manages the progress of the Progress Bar.
     */
    public void progressBarTimer() {
        progressBar.setProgress(0);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                progressBar.incrementProgressBy(1);

                //Game over
                if (progressBar.getProgress() >= 100)   gameOver();
            }
        };

        //schedule starts after 0.01 seconds and repeats every second.
        timer.schedule(task, 10, 1000);
    }

    /**
     *
     */
    public void setNewOrder(){
        //Tell the model to set match == true
        model.setMatch(true);

        //Changes the TextViews
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                message.setText(model.orderMessage());
                score.setText("" + model.getPoints());
            }
        });


        //maybe add an empty cup
    }

    public void addIngredient(){

    }

    public void setButtons(ArrayList<Pair> ingredients) {
        mIngredientButton0.setText(ingredients.get(0).getName());
        mIngredientButton1.setText(ingredients.get(1).getName());
        mIngredientButton2.setText(ingredients.get(2).getName());
        mIngredientButton3.setText(ingredients.get(3).getName());
    }

    public void gameOver(){
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra(EXTRA_SCORE, model.getPoints()+ "");
        startActivity(intent);
        timer.cancel();
    }

    public void onIngredientClick(View view) {
        Button button = (Button) view;
        //add image
        model.update(button.getText().toString());
        if (model.getStage() == 0) {
            if (model.getMatch()){
                progressBar.incrementProgressBy(-5);
            }
            else {
                progressBar.incrementProgressBy(10);
            }
            message.setText(model.finishMessage());
            canClick(false);
            TimerTask pauseTask = new TimerTask() {
                @Override
                public void run() {
                    //redraw
                    //celebrate or somethign....

                    setNewOrder();
                    canClick(true);

                }
            };
            timer.schedule(pauseTask, 1000);
        }
        setButtons(model.getButtons());
    }

    public void canClick(boolean bool){
        mIngredientButton0.setClickable(bool);
        mIngredientButton1.setClickable(bool);
        mIngredientButton2.setClickable(bool);
        mIngredientButton3.setClickable(bool);
    }
}
