package ef.com.testgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChooseGameModeActivity extends AppCompatActivity {
    Button bt3, bt4, bt5, bt6, bt7, bt8;
    ImageView backhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game_mode);
        bt3 = (Button)findViewById(R.id.bt3x3);
        backhome = (ImageView)findViewById(R.id.back);
        bt4 = (Button)findViewById(R.id.bt4x4);
        bt5 = (Button)findViewById(R.id.bt5x5);
        bt6 = (Button)findViewById(R.id.bt6x6);
        bt7 = (Button)findViewById(R.id.bt7x7);
        bt8 = (Button)findViewById(R.id.bt8x8);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChooseGameModeActivity.this, GameActivity.class);
                myIntent.putExtra("TABLE_SIZE",3);
                startActivity(myIntent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChooseGameModeActivity.this, GameActivity.class);
                myIntent.putExtra("TABLE_SIZE",4);
                startActivity(myIntent);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChooseGameModeActivity.this, GameActivity.class);
                myIntent.putExtra("TABLE_SIZE",5);
                startActivity(myIntent);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChooseGameModeActivity.this, GameActivity.class);
                myIntent.putExtra("TABLE_SIZE",6);
                startActivity(myIntent);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChooseGameModeActivity.this, GameActivity.class);
                myIntent.putExtra("TABLE_SIZE",7);
                startActivity(myIntent);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChooseGameModeActivity.this, GameActivity.class);
                myIntent.putExtra("TABLE_SIZE",8);
                startActivity(myIntent);
            }
        });
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseGameModeActivity.this,MainActivity.class);
                ChooseGameModeActivity.this.startActivity(intent);
            }
        });
    }
}
