package ef.com.testgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private RecyclerView rcvTable;
    private Dialog dialog;
    private int tablesize;
    private List<Integer> list = new ArrayList<>();
    private int positionEmpty;
    CountDownTimer w;
    private TextView moveCounter;
    private TextView feedbackText;
    MediaPlayer mediaPlayer;
    private ListAdapter adapter;
    ImageView load;
    TextView level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        moveCounter = (TextView) findViewById(R.id.MoveCounter);
        feedbackText = (TextView) findViewById(R.id.FeedbackText);
        level = (TextView) findViewById(R.id.level);
        Button mix = (Button) findViewById(R.id.start);
        Button back1 = (Button) findViewById(R.id.bacl);
        load = (ImageView) findViewById(R.id.loa);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                mediaPlayer.stop();
            }
        });

        //loa
        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.nhacnen);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.release();
            mediaPlayer.pause();
            load.setImageResource(R.drawable.ic_volume_off_black_24dp);
        } else {
            mediaPlayer.start();
            load.setImageResource(R.drawable.volume);
        }
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    load.setImageResource(R.drawable.ic_volume_off_black_24dp);
                } else {
                    mediaPlayer.start();
                    load.setImageResource(R.drawable.volume);
                }
            }
        });

        // Thời gian
        final TextView tv1 = (TextView) findViewById(R.id.tv);
        w = new CountDownTimer(6000, 1000) {
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
                adapter.setListChange(list);
            }
        });

        rcvTable = findViewById(R.id.activity_main_rcv_table);

        Intent mIntent = getIntent();
        tablesize = mIntent.getIntExtra("TABLE_SIZE", 0);

        randomList();
        adapter = new ListAdapter(list, tablesize, new ListAdapter.ListAdapterListener() {
            @Override
            public void onItemClick(int positon) {
                handleWhenClickItem(positon);
                checkEndGame();
            }
        });
        rcvTable.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, tablesize);
        rcvTable.setLayoutManager(gridLayoutManager);
        rcvTable.setAdapter(adapter);
    }

    private void randomList() {
        list.clear();
        int size = tablesize * tablesize - 1;
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }
        Collections.shuffle(list);
        list.add(0);
        positionEmpty = list.indexOf(0);
        moveCounter.setText("0");
        feedbackText.setText("No feedback yet!");
        feedbackText.setTextColor(Color.BLUE);

    }

    private void handleWhenClickItem(int potision) {
        if (((positionEmpty + 1) % tablesize != 1 && (potision == positionEmpty - 1))
                || ((positionEmpty + 1) % tablesize != 0 && (potision == positionEmpty + 1))
                || potision == positionEmpty + tablesize
                || potision == positionEmpty - tablesize) {

            list.set(positionEmpty, list.get(potision));
            list.set(potision, 0);
            positionEmpty = potision;
            adapter.setListChange(list);
            moveCounter.setText(Integer.toString(Integer.parseInt((String) moveCounter.getText()) + 1));
            moveCounter.setTextColor(Color.BLACK);
            feedbackText.setText("Move OK");
            feedbackText.setTextColor(Color.GREEN);

        } else {

            feedbackText.setText("Move Not Allowed");
            feedbackText.setTextColor(Color.RED);
        }
    }

    private void checkEndGame() {
        int size = tablesize * tablesize;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) != i + 1) {
                break;
            } else if (i == size - 2) {
                Toast.makeText(this, "End Game", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Ketthuc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
        builder.setCancelable(false);
        builder.setTitle("Bạn đã thua cuộc");
        builder.setMessage("Hãy lựa chọn bên dưới để xác nhân");
        builder.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Intent intent = new Intent(GameActivity.this,ChooseGameModeActivity.class);
                GameActivity.this.startActivity(intent);
                mediaPlayer.stop();

            }
        });
        builder.show();
    }

    public int getlevel() {
        level.setText(String.valueOf(tablesize));
        switch (tablesize) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 4;
            case 7:
                return 5;
            case 8:
                return 6;
                default: return 6;
        }
    }


}






















































