package ef.com.testgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvTable;
    private Dialog dialog;
    private int tablesize;
    private List<Integer> list = new ArrayList<>();
    private int positionEmpty;
    CountDownTimer w;
    MediaPlayer mediaPlayer;
    private ListAdapter valuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mix = (Button)findViewById(R.id.start);
        Button back1 = (Button)findViewById(R.id.bacl);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                mediaPlayer.stop();
            }
        });

        // Thời gian
        final TextView tv1 = (TextView) findViewById(R.id.tv);
        w = new CountDownTimer(600000, 1000) {
            public void onTick(long mil) {
                tv1.setText("Seconds remaining: " + mil / 1000);
            }

            public void onFinish() {
                tv1.setText("Seconds remaining: 0");
                Ketthuc();

            }
        }.start();

        mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomList();
            }
        });

        rcvTable= findViewById(R.id.activity_main_rcv_table);

        Intent mIntent = getIntent();
        tablesize = mIntent.getIntExtra("TABLE_SIZE", 0);

        randomList();
        valuesAdapter = new ListAdapter(list, tablesize, new ListAdapter.ListAdapterListener() {
            @Override
            public void onItemClick(int positon) {
                handleWhenClickItem(positon);
                checkEndGame();
            }
        });
        rcvTable.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, tablesize);
        rcvTable.setLayoutManager(gridLayoutManager);
        rcvTable.setAdapter(valuesAdapter);
    }

    private void randomList() {
        list.clear();
        int size= tablesize * tablesize -1;
        for (int i = 0; i < size; i++) {
            list.add(i+1);
        }
        Collections.shuffle(list);
        list.add(0);
        positionEmpty= list.indexOf(0);

    }

    private void handleWhenClickItem(int potision){
        if(((positionEmpty+1)%tablesize !=1 && (potision==positionEmpty-1))
                || ((positionEmpty+1)%tablesize !=0 && (potision==positionEmpty+1))
                || potision==positionEmpty+tablesize
                || potision==positionEmpty-tablesize){

            list.set(positionEmpty,list.get(potision));
            list.set(potision,0);
            positionEmpty=potision;
            valuesAdapter.setListChange(list);
        }else{
            Toast.makeText(this, "Move Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkEndGame(){
        int size= tablesize * tablesize;
        for(int i=0; i<size-1; i++){
            if(list.get(i)!=i+1){
                break;
            }else if(i==size-2){
                Toast.makeText(this, "End Game", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void Ketthuc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
        builder.setTitle("Bạn đã thua cuộc");
        builder.setMessage("Hãy lựa chọn bên dưới để xác nhân");
        builder.setIcon(android.R.drawable.ic_notification_overlay);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        builder.show();
    }
}
