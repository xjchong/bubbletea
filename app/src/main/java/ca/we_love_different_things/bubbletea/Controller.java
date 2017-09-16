package ca.we_love_different_things.bubbletea;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Jiashu on 2017-09-16.
 */

public class Controller extends AppCompatActivity implements Runnable {

    private OrderModel model = new OrderModel();
    private ArrayList<Pair> order;
    private ArrayList<Pair> buttons;
    private int numOrders;
    private final int maxOrders = 10;
    private boolean running = true;
    private Button mIngredientButton0;
    private Button mIngredientButton1;
    private Button mIngredientButton2;
    private Button mIngredientButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mIngredientButton0 = (Button) findViewById(R.id.button0);
        mIngredientButton1 = (Button) findViewById(R.id.button1);
        mIngredientButton2 = (Button) findViewById(R.id.button2);
        mIngredientButton3 = (Button) findViewById(R.id.button3);
    }



    /**
     * Gives the View the required display and return settings for the button.
     * @return the set of buttons.
     */
    public ArrayList<Pair> getButtons(){
        return buttons;
    }

    @Override
    public void run() {

         class Timer extends CountDownTimer {

            public Timer(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);

                //create an icon in view
                //gameView.createTimer();
            }

            @Override
            public void onFinish() {
                running = false;
                numOrders++;
                if(numOrders <= maxOrders)
                    new Timer(5000,1000);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                //gameView.updateTimer();

            }
        }

        while(running){
            Timer timer = new Timer(6000,1000);


        }
    }


    public void setButtons(ArrayList<Pair> ingredients) {
        mIngredientButton0.setText(ingredients.get(0).getName());
        mIngredientButton1.setText(ingredients.get(1).getName());
        mIngredientButton2.setText(ingredients.get(2).getName());
        mIngredientButton3.setText(ingredients.get(3).getName());
    }

    public void onIngredientClick(View button) {
        Button b = (Button) button;
        model.update(b.getText().toString());
        order = model.getOrder();
        setButtons(model.getButtons());

    }


    /*Game view...
    createTimer... the icons XD;
    setButtons();
        set the name of button and the color...
    onClick();
        notify Controller somehow of button press...
    */
    /*GAME LOOP

    if(match){
    game over!

    */


}
