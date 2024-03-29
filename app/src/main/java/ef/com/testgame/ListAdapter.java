package ef.com.testgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Integer> list;
    private ListAdapterListener listener;
    private int sizeTable;
    private Context context;

    ListAdapter(Context context, List<Integer> list, int sizeTable,
                ListAdapterListener listener) {
        this.list = list;
        this.listener = listener;
        this.sizeTable=sizeTable;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layout = 0;
        switch (sizeTable){
            case 3:
                layout=R.layout.item_value_3x3;
                break;
            case 4:
                layout=R.layout.item_value_4x4;
                break;
            case 5:
                layout=R.layout.item_value_5x5;
                break;
            case 6:
                layout=R.layout.item_value_6x6;
                break;
            case 7:
                layout=R.layout.item_value_7x7;
                break;
            case 8:
                layout=R.layout.item_value_8x8;
                break;
        }

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        int value = list.get(i);
            if(value==0){
                holder.tvValue.setText("");
                holder.tvValue.setBackgroundColor(Color.TRANSPARENT);
            }else{
                holder.tvValue.setText(String.valueOf(value));
                holder.tvValue.setBackground(AppUtil.getBackground(context, sizeTable));

            }



    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvValue;

        @SuppressLint("ClickableViewAccessibility")
        private ViewHolder(View itemView) {
            super(itemView);
            tvValue = itemView.findViewById(R.id.item_value_tv_value);
            tvValue.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(listener!=null){
                      listener.onItemClick(tvValue,getAdapterPosition());
                    }
                    return false;
                }
            });



        }

    }

    public interface ListAdapterListener{
        void onItemClick(TextView view, int positon);
    }
    public void setListChange(List<Integer> newList){
        this.list= newList;
        notifyDataSetChanged();

    }


}
