package ca.we_love_different_things.bubbletea;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private ArrayList<Pair> order;
    private ArrayList<Pair> buttons;
    private int numOrders;
    private final int maxOrders = 10;
    private Button mIngredientButton0;
    private Button mIngredientButton1;
    private Button mIngredientButton2;
    private Button mIngredientButton3;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mIngredientButton0 = (Button) findViewById(R.id.button0);
        mIngredientButton1 = (Button) findViewById(R.id.button1);
        mIngredientButton2 = (Button) findViewById(R.id.button2);
        mIngredientButton3 = (Button) findViewById(R.id.button3);

        setNewOrder();
        setButtons(model.getButtons());

        timer();
    }

    public void timer(){
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
                progressBar.incrementProgressBy(1);

                if(progressBar.getProgress() >= 100){
                    gameOver();
                }
            }
        };

        timer.schedule(task, 10, 1000);
    }

    /**
     * Gives the View the required display and return settings for the button.
     * @return the set of buttons.
     */
    public ArrayList<Pair> getButtons(){
        return buttons;
    }

    public void setNewOrder(){
        order = model.getOrder();

        String string = "";
        for (Pair pair : order) string += pair.getName() + " ";

        TextView textView = (TextView) findViewById(R.id.order);
        textView.setText(string);

        TextView score = (TextView) findViewById(R.id.score);

        score.setText("" + model.getPoints());

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

    public void onIngredientClick(View button) {
        Button b = (Button) button;
        //add image
        model.update(b.getText().toString());
        if (model.getStage() == 0) {
            setNewOrder();
            //create an empty cup.
        }
        setButtons(model.getButtons());
    }
}
