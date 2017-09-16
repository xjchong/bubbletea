package ca.we_love_different_things.bubbletea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        String score = intent.getStringExtra(Controller.EXTRA_SCORE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Game over!\nYou scored:\n" + score);
    }

    public void button(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
