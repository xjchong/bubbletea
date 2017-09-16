package ca.we_love_different_things.bubbletea;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import static android.R.attr.max;

/**
 * Created by Jiashu on 2017-09-16.
 */

public class OrderModel {

    //Random colors
    private final String RED    = "FF0000";
    private final String YELLOW = "FFFF00";
    private final String BLACK  = "000000";
    private final String GREEN  = "00FF00";
    private final String BLUE   = "0000FF";
    private final String CYAN   = "00FFFF";
    private final String PINK   = "FF00FF";
    private final String WHITE  = "FFFFFF";

    //No magic numbers
    private final int PEARL = 0;
    private final int SUGAR = 1;
    private final int FLAVOR = 2;
    private final int MILK = 3;

    //To list of all types of findIngredients
    private ArrayList<Pair> listPearls = new ArrayList<>();
    private ArrayList<Pair> listSugar = new ArrayList<>();
    private ArrayList<Pair> listFlavor = new ArrayList<>();
    private ArrayList<Pair> listMilk = new ArrayList<>();
    
    //Arrays of 4 to be turned into buttons
    private ArrayList<Pair> buttonPearls;
    private ArrayList<Pair> buttonSugar;
    private ArrayList<Pair> buttonFlavor;
    private ArrayList<Pair> buttonMilk;

    //How far along we are to completing the drink
    private static int stage;

    //The order receipt
    private ArrayList<Pair> order = new ArrayList<Pair>();

    //Whether it's a match or not
    private boolean match = true;

    private int points = -1;

    /**
     * Matches an ingredient with a color.
     */
    public OrderModel(){
        listPearls.add(new Pair("Tapioca", BLACK));
        listPearls.add(new Pair("Jelly", BLUE));
        listPearls.add(new Pair("Exploding", RED));
        listPearls.add(new Pair("None", WHITE));
        listPearls.add(new Pair("Aloe Vera", GREEN));
        listPearls.add(new Pair("Grass Jelly", CYAN));

        //listSugar[0] = new Pair("0%", "White")));
        listSugar.add(new Pair("25%", YELLOW));
        listSugar.add(new Pair("50%", GREEN));
        listSugar.add(new Pair("75%", PINK));
        listSugar.add(new Pair("100%", BLACK));

        listFlavor.add(new Pair("Coconut", WHITE));
        listFlavor.add(new Pair("Strawberry", RED));
        listFlavor.add(new Pair("Blueberry", BLUE));
        listFlavor.add(new Pair("Banana", YELLOW));

        listMilk.add(new Pair("Skim", WHITE));
        listMilk.add(new Pair("1%", RED));
        listMilk.add(new Pair("2%", BLUE));
        listMilk.add(new Pair("Soy", YELLOW));

        startOrder();
    }


    public void randomize(){
        order.add(buttonPearls.get(0));
        order.add(buttonSugar.get(0));
        order.add(buttonFlavor.get(0));
        order.add(buttonMilk.get(0));
    }

    public ArrayList<Pair> getOrder() {
        return order;
    }

    public int getPoints() {
        return points;
    }
    /**
     * Shuffles the ArrayLists.
     * @param list The set of lists to shuffle.
     */
    private void shuffle(boolean list){

        if(list) {
            Collections.shuffle(listPearls);
            Collections.shuffle(listSugar);
            Collections.shuffle(listFlavor);
            Collections.shuffle(listMilk);
        }
        else{
            Collections.shuffle(buttonPearls);
            Collections.shuffle(buttonSugar);
            Collections.shuffle(buttonFlavor);
            Collections.shuffle(buttonMilk);
        }
    }

    /**
     * The available findIngredients to choose from in the buttons.
     */
    private void findIngredients(){

        final boolean LIST = true;
        final boolean BUTTON = false;

        shuffle(LIST);

        buttonPearls = new ArrayList<Pair>(listPearls.subList(0,4));
        buttonSugar  = new ArrayList<Pair>(listSugar.subList(0,4));
        buttonFlavor = new ArrayList<Pair>(listFlavor.subList(0,4));
        buttonMilk   = new ArrayList<Pair>(listMilk.subList(0,4));

        randomize();

        shuffle(BUTTON);

    }


    private void startOrder(){
        stage = 0;
        if(match) points++; else points--;
        match = true;
        //Creates sets of four of the findIngredients and randomizes

        order.clear();
        findIngredients();


        //things can now be called...

    }

    /**
     * Determines which stage slash which array of buttons to return.
     * @return The array of buttons to be returned.
     */
    public ArrayList<Pair> getButtons(){
        switch(stage){
            case PEARL :    return buttonPearls;
            case SUGAR :    return buttonSugar;
            case FLAVOR:    return buttonFlavor;
            case MILK  :    return buttonMilk;
            default    :    return null;
        }
    }

    public void update(String ingredient){


        if (!order.get(stage++).isMatch(ingredient)){
            match = false;
            startOrder();
        }
        else if(stage >= 4){
            startOrder();
        }
        else{

        }

    }

    public int getStage(){
        return stage;
    }

    public boolean orderMatchesCreation(){
        return match;
    }
}
