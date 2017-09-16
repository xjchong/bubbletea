package ca.we_love_different_things.bubbletea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {

    private Button mIngredientButton0;
    private Button mIngredientButton1;
    private Button mIngredientButton2;
    private Button mIngredientButton3;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mIngredientButton0 = (Button) findViewById(R.id.button0);
        mIngredientButton1 = (Button) findViewById(R.id.button1);
        mIngredientButton2 = (Button) findViewById(R.id.button2);
        mIngredientButton3 = (Button) findViewById(R.id.button3);

    }

    public void setButtons(ArrayList<Pair> ingredients) {
        mIngredientButton0.setText(ingredients.get(0).getName());
        mIngredientButton1.setText(ingredients.get(1).getName());
        mIngredientButton2.setText(ingredients.get(2).getName());
        mIngredientButton3.setText(ingredients.get(3).getName());
    }

    public void onIngredientClick(View button) {
        Log.d("Ingredient", button.getTag().toString());
    }

}
