package ef.com.testgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Integer> list;
    private ListAdapterListener listener;

    ListAdapter(List<Integer> list,
                ListAdapterListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_value, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        AppUtil.setEffectView(holder.tvValue);

        int value = list.get(i);
            if(value==0){
                holder.tvValue.setText("");
            }else holder.tvValue.setText(String.valueOf(value));




    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvValue;

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
