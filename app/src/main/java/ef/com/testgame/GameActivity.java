package ef.com.testgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private RecyclerView rcvTable;
    private int tablesize;
    private List<Integer> list = new ArrayList<>();
    private int positionEmpty;
    CountDownTimer w;
    private TextView moveCounter;
    private TextView feedbackText;
    MediaPlayer mediaPlayer;
    private ListAdapter adapter;
    ImageView load;
    TextView tvFakeAnimation, level;
    LinearLayout llRoot;

    private  static int MOVE_LEFT=1;
    private  static int MOVE_TOP=2;
    private  static int MOVE_RIGHT=3;
    private  static int MOVE_BOTTOM=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        moveCounter = (TextView) findViewById(R.id.MoveCounter);
        feedbackText = (TextView) findViewById(R.id.FeedbackText);
        Button mix = (Button)findViewById(R.id.start);
        Button back1 = (Button)findViewById(R.id.bacl);
        level = (TextView)findViewById(R.id.level);

        load = (ImageView)findViewById(R.id.loa);
        tvFakeAnimation= findViewById(R.id.game_activity_tv_fake_animation);
        llRoot= findViewById(R.id.game_activity_ll_root);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent123 = new Intent(GameActivity.this, ChooseGameModeActivity.class);
                GameActivity.this.startActivity(intent123);
                mediaPlayer.stop();
            }
        });

        //loa
        mediaPlayer= MediaPlayer.create(GameActivity.this,R.raw.nhacnen1);
        if(mediaPlayer.isPlaying()){
            mediaPlayer.release();
            mediaPlayer.pause();
            load.setImageResource(R.drawable.ic_volume_off_black_24dp);
        }
        else {
            mediaPlayer.start();
            load.setImageResource(R.drawable.ic_volume_up_black_24dp);
        }
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    load.setImageResource(R.drawable.ic_volume_off_black_24dp);
                }
                else {
                    mediaPlayer.start();
                    load.setImageResource(R.drawable.ic_volume_up_black_24dp);
                }
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
                adapter.setListChange(list);
                tvFakeAnimation.setVisibility(View.VISIBLE);
            }
        });

        rcvTable= findViewById(R.id.activity_main_rcv_table);

        Intent mIntent = getIntent();
        tablesize = mIntent.getIntExtra("TABLE_SIZE", 0);

        level.setText("Level " + getLevel(tablesize));

        randomList();

        adapter = new ListAdapter(this,list, tablesize, new ListAdapter.ListAdapterListener() {
            @Override
            public void onItemClick(TextView view, int positon) {
                handleWhenClickItem(view, positon);
            }
        });
        rcvTable.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, tablesize);
        rcvTable.setLayoutManager(gridLayoutManager);
        rcvTable.setAdapter(adapter);
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
        moveCounter.setText("0");
        feedbackText.setText("No feedback yet!");
        feedbackText.setTextColor(Color.BLUE);

    }

    private void handleWhenClickItem(TextView view, int potision){
        MediaPlayer mediaPlayer1= MediaPlayer.create(GameActivity.this,R.raw.dichuyen);
        if((positionEmpty+1)%tablesize !=1 && (potision==positionEmpty-1)){
            startAnimation(potision,MOVE_RIGHT);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setText("");
            mediaPlayer1.start();
        }else if((positionEmpty+1)%tablesize !=0 && (potision==positionEmpty+1)){
            startAnimation(potision,MOVE_LEFT);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setText("");
            mediaPlayer1.start();
        }else if( potision==positionEmpty+tablesize){
            startAnimation(potision,MOVE_BOTTOM);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setText("");
            mediaPlayer1.start();
        }else if(potision==positionEmpty-tablesize){
            startAnimation(potision,MOVE_TOP);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setText("");
            mediaPlayer1.start();
        }else{
            feedbackText.setText("Move Not Allowed");
            feedbackText.setTextColor(Color.RED);

        }
    }


    private void moveSuccess(int potision){
        list.set(positionEmpty,list.get(potision));
        list.set(potision,0);
        positionEmpty=potision;
        adapter.setListChange(list);
        moveCounter.setText(Integer.toString(Integer.parseInt((String) moveCounter.getText())+1));
        moveCounter.setTextColor(Color.BLACK);
        feedbackText.setText("Move OK");
        feedbackText.setTextColor(Color.GREEN);
    }

    private void checkEndGame(){
        int size= tablesize * tablesize;
        for(int i=0; i<size-1; i++){
            if(list.get(i)!=i+1){
                break;
            }else if(i==size-2){
                Intent myIntent = new Intent(this, GameActivity.class);
                myIntent.putExtra("TABLE_SIZE",tablesize+1);
                startActivity(myIntent);
                mediaPlayer.stop();
            }
        }
    }
    public void Ketthuc() {
        if(isFinishing()){
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
        builder.setCancelable(false);
        builder.setTitle("Bạn đã thua cuộc");
        builder.setMessage("Hãy lựa chọn bên dưới để xác nhân");
        builder.setIcon(android.R.drawable.ic_notification_overlay);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intento = new Intent(GameActivity.this,ChooseGameModeActivity.class);
                GameActivity.this.startActivity(intento);
                mediaPlayer.stop();
            }
        });
        builder.show();
    }



    private void startAnimation(final int positionClick, final int derectionMove) {
        tvFakeAnimation.setText(String.valueOf(list.get(positionClick)));
        tvFakeAnimation.setBackground(AppUtil.getBackground(this, tablesize));
        tvFakeAnimation.setVisibility(View.VISIBLE);

        int locationX= positionClick% tablesize;
        int locationY= positionClick/tablesize;
        int leftMarginDefault= (llRoot.getWidth()-rcvTable.getWidth())/2+ (int)AppUtil.convertDpToPixel(4,GameActivity.this); //padding 2 + margin 2
        int topMarginDefault= (int)AppUtil.convertDpToPixel(4,GameActivity.this); //padding 2 + margin 2

        final int leftMargin= leftMarginDefault+ (locationX* ((int)AppUtil.convertDpToPixel(getSizeItem()+4,GameActivity.this)));

        final int topMargin=  topMarginDefault+ (locationY* ((int)AppUtil.convertDpToPixel(getSizeItem()+4,GameActivity.this)));
        Log.e("check", "checksetHalfIcon");
        ValueAnimator widthAnimator = ValueAnimator.ofInt(0,(int)AppUtil.convertDpToPixel(getSizeItem()+4,GameActivity.this));
        widthAnimator.setDuration(500);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)AppUtil.convertDpToPixel(getSizeItem(),GameActivity.this), (int)AppUtil.convertDpToPixel(getSizeItem(),GameActivity.this));
//truyền  vào witdth và height của view
                if(derectionMove==MOVE_LEFT){
                    lp.setMargins(leftMargin - animatedValue, topMargin, 0, 0);

                }else if(derectionMove==MOVE_TOP){
                    lp.setMargins(leftMargin, topMargin + animatedValue, 0, 0);

                }else if(derectionMove==MOVE_RIGHT){
                    lp.setMargins(leftMargin +animatedValue, topMargin,  0, 0);

                }else if(derectionMove==MOVE_BOTTOM){
                    lp.setMargins(leftMargin, topMargin- animatedValue, 0,0);

                }

                tvFakeAnimation.setLayoutParams(lp);
            }
        });
        widthAnimator.start();
        widthAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                moveSuccess(positionClick);
                checkEndGame();

            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
    }

    private int getLevel( int tablesize){
        switch (tablesize){
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
                default: return 0;
        }
    }

    private int getSizeItem(){
        switch (tablesize){
            case 3:
                return 100;
            case 4:
                return  75;
            case 5:
                return  60;
            case 6:
                return  50;
            case 7:
                return  42;
            case 8:
                return  37;
            default: return 100;
        }
    }
}






















































