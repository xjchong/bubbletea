package ca.we_love_different_things.bubbletea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    private final int TOPPING = 0;
    private final int SUGAR = 1;
    private final int MILK = 2;
    private final int FLAVOR = 3;


    //To list of all types of findIngredients
    private ArrayList<Pair> listPearls = new ArrayList<>();
    private ArrayList<Pair> listSugar = new ArrayList<>();
    private ArrayList<Pair> listMilk = new ArrayList<>();
    private ArrayList<Pair> listFlavor = new ArrayList<>();
    
    //Arrays of 4 to be turned into buttons
    private ArrayList<Pair> buttonPearls;
    private ArrayList<Pair> buttonSugar;
    private ArrayList<Pair> buttonMilk;
    private ArrayList<Pair> buttonFlavor;

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
        listPearls.add(new Pair("Mini Pearls", BLACK));
        listPearls.add(new Pair("No Pearls", BLUE));
        listPearls.add(new Pair("Panda Pearls", RED));
        listPearls.add(new Pair("Regular Pearls", WHITE));
        listPearls.add(new Pair("Grass Jelly", GREEN));
        listPearls.add(new Pair("Fruit Jelly", CYAN));
        listPearls.add(new Pair("Aloe Jelly", YELLOW));

        listSugar.add(new Pair("No Sugar", WHITE));
        listSugar.add(new Pair("1/4 Sweet", YELLOW));
        listSugar.add(new Pair("1/2 Sweet", GREEN));
        listSugar.add(new Pair("3/4 Sweet", PINK));
        listSugar.add(new Pair("Regular Sweet", BLACK));

        listMilk.add(new Pair("Skim Milk", WHITE));
        listMilk.add(new Pair("1% Milk", RED));
        listMilk.add(new Pair("2% Milk", BLUE));
        listMilk.add(new Pair("Soy Milk", YELLOW));

        listFlavor.add(new Pair("Coconut", WHITE));
        listFlavor.add(new Pair("Strawberry", RED));
        listFlavor.add(new Pair("Taro", BLUE));
        listFlavor.add(new Pair("Original", YELLOW));

        startOrder();
    }


    public void randomize(){
        order.add(buttonPearls.get(0));
        order.add(buttonSugar.get(0));
        order.add(buttonMilk.get(0));
        order.add(buttonFlavor.get(0));
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
            Collections.shuffle(listMilk);
            Collections.shuffle(listFlavor);
        }
        else{
            Collections.shuffle(buttonPearls);
            Collections.shuffle(buttonSugar);
            Collections.shuffle(buttonMilk);
            Collections.shuffle(buttonFlavor);
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
        buttonMilk   = new ArrayList<Pair>(listMilk.subList(0,4));
        buttonFlavor = new ArrayList<Pair>(listFlavor.subList(0,4));

        randomize();

        shuffle(BUTTON);

    }


    private void startOrder(){
        stage = 0;
        if(match) points++; else points--;
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
            case TOPPING:   return buttonPearls;
            case SUGAR :    return buttonSugar;
            case MILK  :    return buttonMilk;
            case FLAVOR:    return buttonFlavor;
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

    public String orderMessage() {
        // Sentence components
        String greeting[] = new String[4];
        String first[] = new String[5];
        String second[] = new String[2];
        String third[] = new String[3];
        String fourth[] = new String[3];
        String fifth[] = new String[2];

        greeting[0] = "Hi, ";
        greeting[1] = "Hello, ";
        greeting[2] = "";
        greeting[3] = "Yo, ";
        int greetingVal = rand(4);

        first[0] = "I would like to order a";
        if (greetingVal == 2) {
            first[1] = "Can I get a";
        } else {
            first[1] = "can I get a";
        }
        first[2] = "I'll have the";
        if (greetingVal == 2) {
            first[3] = "Could I get a";
        } else {
            first[3] = "could I get a";
        }
        if (greetingVal == 2) {
            first[4] = "Get me the";
        } else {
            first[4] = "get me the";
        }

        second[0] = "bubble tea with";
        second[1] = "tea with";

        third[0] = ",";
        third[1] = ", topped with";
        third[2] = ", and";

        fourth[0] = ", and";
        fourth[1] = ", along with";
        fourth[2] = ", plus";

        fifth[0] = ".";
        fifth[1] = "please.";

        String message = String.format("%s%s %s %s %s %s %s %s %s %s",
                greeting[greetingVal], first[rand(5)], order.get(FLAVOR), second[rand(2)], order.get(TOPPING),
                third[rand(3)], order.get(MILK), fourth[rand(3)], order.get(SUGAR), fifth[rand(2)]);

        return message;
    }

    public String finishMessage(){
        
        String message;
        
        if(match){
            //success message
            
            switch(rand(10)){
                case 0: message = "Thank you!"; break;
                case 1: message = "Thanks..."; break;
                case 2: message = "Yayyy!!!!"; break;
                case 3: message = "Yum!"; break;
                case 4: message = "Yummy!"; break;
                case 5: message = "A solid 10!"; break;
                case 6: message = "10 out of 10!"; break;
                case 7: message = "Arigato gozaimasu~"; break;
                case 8: message = "More please!"; break;
                case 9: message = "Definitely worth the wait!"; break;
                default: message = null;
            }
        }
        else{

            switch(rand(10)){
                case 0: message = "......"; break;
                case 1: message = "Well then..."; break;
                case 2: message = "?!@#!$?#"; break;
                case 3: message = "No! Just... No!"; break;
                case 4: message = "You meanie!"; break;
                case 5: message = "IT'S RAW!?!"; break;
                case 6: message = "Not to worry, I won't EVER come back."; break;
                case 7: message = "Seriously?"; break;
                case 8: message = "What is this???"; break;
                case 9: message = "Would rather die..."; break;
                default: message = null;
            }
        }
        
        return message;
    }
    
    public static int rand(int n){
        Random rand = new Random(); return rand.nextInt(n);
    }

    public void setMatch(boolean match) {
        this.match = match;
    }

    public boolean getMatch() {
        return match;
    }
}
