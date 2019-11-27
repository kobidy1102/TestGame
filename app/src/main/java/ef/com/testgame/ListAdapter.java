package ef.com.testgame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Integer> list;
    private ListAdapterListener listener;
    private int sizeTable;

    ListAdapter(List<Integer> list, int sizeTable,
                ListAdapterListener listener) {
        this.list = list;
        this.listener = listener;
        this.sizeTable=sizeTable;
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
                holder.tvValue.setBackgroundColor(Color.YELLOW);
            }else{
                holder.tvValue.setText(String.valueOf(value));
                holder.tvValue.setBackgroundColor(Color.parseColor("#51C0F3"));

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

            tvValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });



        }

    }

    public interface ListAdapterListener{
        void onItemClick(int positon);
    }

    public void setListChange(List<Integer> newList){
        this.list= newList;
        notifyDataSetChanged();

    }
}
