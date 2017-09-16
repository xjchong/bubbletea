package ca.we_love_different_things.bubbletea;

import android.util.Log;

/**
 * Created by Jiashu on 2017-09-16.
 */

public class Pair {

    private String name;
    private String color;

    public Pair(String name, String color){

        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isMatch(String ingredient){
        Log.d("onclick", "in isMatch");
        return name.equals(ingredient);
    }

    @Override
    public String toString() {
        return getName();
    }
}
