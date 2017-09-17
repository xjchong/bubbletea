package ca.we_love_different_things.bubbletea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private ImageView[] images = new ImageView[5];
    private ProgressBar progressBar;
    private TextView message;
    private TextView score;
    private Timer timer;

    private final int TOPPING = 0;
    private final int SUGAR = 1;
    private final int FLAVOR = 3;
    private final int MILK = 2;
    private final int LID = 4;

    private boolean pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Find all of the views and get references
        mIngredientButton0 = (Button) findViewById(R.id.button0);
        mIngredientButton1 = (Button) findViewById(R.id.button1);
        mIngredientButton2 = (Button) findViewById(R.id.button2);
        mIngredientButton3 = (Button) findViewById(R.id.button3);

        //images[0] = (ImageView) findViewById(R.id.cup);
        images[TOPPING] = (ImageView) findViewById(R.id.toppings);
        images[SUGAR] = (ImageView) findViewById(R.id.sweetener);
        images[FLAVOR] = (ImageView) findViewById(R.id.tea_flavour);
        images[MILK] = (ImageView) findViewById(R.id.milk);
        images[LID] = (ImageView) findViewById(R.id.lid_and_straw);
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
        pause = true;
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void onResume(){
        super.onResume();
        pause = false;
    }

    /**
     * Manages the progress of the Progress Bar.
     */
    public void progressBarTimer() {
        progressBar.setProgress(0);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(!pause) progressBar.incrementProgressBy(1);

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
                setImages();
            }
        });

        //maybe add an empty cup
    }

    public void setImages(){
        for(ImageView image: images) {
            image.setVisibility(View.INVISIBLE);
        }

        String flavors = model.getOrder().get(FLAVOR).toString();
        switch(flavors) {
            case "Coconut": images[FLAVOR].setImageResource(R.drawable.flavour_coconut); break;
            case "Strawberry": images[FLAVOR].setImageResource(R.drawable.flavour_strawberry); break;
            case "Taro": images[FLAVOR].setImageResource(R.drawable.flavour_taro); break;
            case "Original":images[FLAVOR].setImageResource(R.drawable.flavour_original); break;
        }

        String toppings = model.getOrder().get(TOPPING).toString();
        switch(toppings) {
            case "Mini Pearls": images[TOPPING].setImageResource(R.drawable.toppings_mini_pearls); break;
            case "Panda Pearls": images[TOPPING].setImageResource(R.drawable.toppings_panda_pearls); break;
            case "Regular Pearls":images[TOPPING].setImageResource(R.drawable.toppings_regular_pearls); break;
            case "Grass Jelly": images[TOPPING].setImageResource(R.drawable.toppings_grass_jelly); break;
            case "Fruit Jelly": images[TOPPING].setImageResource(R.drawable.toppings_fruit_jelly); break;
            case "Aloe Jelly": images[TOPPING].setImageResource(R.drawable.toppings_aloe_jelly); break;
            case "No Pearls": images[TOPPING].setImageResource(R.drawable.toppings_no_pearls); break;
        }

        if(!model.getOrder().get(SUGAR).isMatch("No Sugar")){
            images[SUGAR].setImageResource(R.drawable.sugar_quarter);
        }
        else{
            images[SUGAR].setImageResource(R.drawable.toppings_no_pearls);
        }

        images[MILK].setImageResource(R.drawable.milk);

        images[LID].setImageResource(R.drawable.lid_and_straw);
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

        //images
        images[model.getStage()].setVisibility(View.VISIBLE);
        if (images[FLAVOR].getVisibility() == View.VISIBLE){
            images[LID].setVisibility(View.VISIBLE);
        }

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
