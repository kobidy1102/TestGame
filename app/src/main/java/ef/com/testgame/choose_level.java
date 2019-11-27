package ef.com.testgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choose_level extends AppCompatActivity {
    Button bt3, bt4, bt5, bt6, bt7, bt8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        bt3 = (Button)findViewById(R.id.bt3x3);
        bt4 = (Button)findViewById(R.id.bt4x4);
        bt5 = (Button)findViewById(R.id.bt5x5);
        bt6 = (Button)findViewById(R.id.bt6x6);
        bt7 = (Button)findViewById(R.id.bt7x7);
        bt8 = (Button)findViewById(R.id.bt8x8);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_level.this, MainActivity.class);
                myIntent.putExtra("TABLE_SIZE",3);
                startActivity(myIntent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_level.this, MainActivity.class);
                myIntent.putExtra("TABLE_SIZE",4);
                startActivity(myIntent);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_level.this, MainActivity.class);
                myIntent.putExtra("TABLE_SIZE",5);
                startActivity(myIntent);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_level.this, MainActivity.class);
                myIntent.putExtra("TABLE_SIZE",6);
                startActivity(myIntent);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_level.this, MainActivity.class);
                myIntent.putExtra("TABLE_SIZE",7);
                startActivity(myIntent);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_level.this, MainActivity.class);
                myIntent.putExtra("TABLE_SIZE",8);
                startActivity(myIntent);
            }
        });
    }
}
