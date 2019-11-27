package ef.com.testgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv8x8;
    private static final int TABLE_SIZE = 3;
    private List<Integer> list = new ArrayList<>();
    private int positionEmpty;
    private ListAdapter valuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv8x8= findViewById(R.id.activity_main_rcv_8x8);
        randomList();

        valuesAdapter = new ListAdapter(list, new ListAdapter.ListAdapterListener() {
            @Override
            public void onItemClick(int positon) {
                handleWhenClickItem(positon);
                checkEndGame();
            }
        });
        rcv8x8.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, TABLE_SIZE);
        rcv8x8.setLayoutManager(gridLayoutManager);
        rcv8x8.setAdapter(valuesAdapter);
    }

    private void randomList() {
        int size= TABLE_SIZE * TABLE_SIZE;
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        positionEmpty= list.indexOf(0);

    }

    private void handleWhenClickItem(int potision){
        if(((positionEmpty+1)%TABLE_SIZE !=1 && (potision==positionEmpty-1))
                || ((positionEmpty+1)%TABLE_SIZE !=0 && (potision==positionEmpty+1))
                || potision==positionEmpty+TABLE_SIZE
                || potision==positionEmpty-TABLE_SIZE){

            list.set(positionEmpty,list.get(potision));
            list.set(potision,0);
            positionEmpty=potision;
            valuesAdapter.setListChange(list);
        }else{
            Toast.makeText(this, "Move Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkEndGame(){
        int size= TABLE_SIZE * TABLE_SIZE;
        for(int i=0; i<size-1; i++){
            if(list.get(i)!=i+1){
                break;
            }else if(i==size-2){
                Toast.makeText(this, "End Game", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
