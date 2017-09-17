package ca.we_love_different_things.bubbletea;

import android.util.Log;

/**
 * Created by Jiashu on 2017-09-16.
 */

public class Pair {

    private String name;
    private String color;

    /**
     * Constructor for the pair
     * @param name The name of the ingredient.
     * @param color The color of the ingredient in hexadecimals.
     */
    public Pair(String name, String color){

        this.name = name;
        this.color = color;
    }

    /**
     * Getter for name of ingredient.
     * @return the name of ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for color of ingredient as a hexadecimal.
     * @return the color of ingredient as a hexadecimal.
     */
    public String getColor() {
        return color;
    }

    /**
     * Setter for name of ingredient.
     * @param name the name of ingredient.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter for color of ingredient as a hexadecimal.
     * @param color the color of ingredient as a hexadecimal.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Comparing if pair ingredient is the ingredient that is beign searched for.
     * @param ingredient the ingredient to compare to the pair.
     * @return true if equal, false otherwise.
     */
    public boolean isMatch(String ingredient){
        return name.equals(ingredient);
    }

    @Override
    public String toString() {
        return getName();
    }
}
