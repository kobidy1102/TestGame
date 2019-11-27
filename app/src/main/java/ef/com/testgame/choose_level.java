package ef.com.testgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choose_level extends AppCompatActivity {
    Button bt3x3, bt4x4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        bt3x3 = (Button)findViewById(R.id.bt3x3);
        bt4x4 = (Button)findViewById(R.id.bt4x4);
        bt3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(choose_level.this, MainActivity.class);
                myIntent.putExtra("TABLE_SIZE",3);
                startActivity(myIntent);
            }
        });
    }
}
