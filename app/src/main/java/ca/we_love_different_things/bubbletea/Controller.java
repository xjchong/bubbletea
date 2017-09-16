package ca.we_love_different_things.bubbletea;

import android.os.CountDownTimer;

import java.util.ArrayList;

/**
 * Created by Jiashu on 2017-09-16.
 */

public class Controller implements Runnable{

    private OrderModel model = new OrderModel();
    private ArrayList<Pair> order;
    private ArrayList<Pair> buttons;
    private int numOrders;
    private final int maxOrders = 10;
    private boolean running = true;

    /**
     * Updates the model and get's the new order and set.
     */
    private void update(){

        model.update();
        order = model.getOrder();
        //gameView.setButtons(model.getButtons());

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
            Timer timer = new Timer(5000,1000);


        }
    }

    /*Game view...
    createTimer... the icons XD;
    setButtons();
        set the name of button and the color...
    onClick();
        notify Controller somehow of button press...
    */
    /*GAME LOOP

    if(!match){
    game over!

    */


}
