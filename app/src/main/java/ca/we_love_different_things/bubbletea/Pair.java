package ca.we_love_different_things.bubbletea;

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

    public void setName(String n){
        this.name = n;
    }

    public void setColor(String c) {
        this.color = c;
    }
}
