package com.bhardwaj.abhishek.payrollapp.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bhardwaj.abhishek.payrollapp.Model.Earnings;
import com.bhardwaj.abhishek.payrollapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerListAdapter extends FirestoreRecyclerAdapter<Earnings, RecyclerListAdapter.RecyclerListViewHolder> {
    private OnItemClickListener listener;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<Earnings> mEarnings;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerListAdapter(@androidx.annotation.NonNull FirestoreRecyclerOptions<Earnings> options,Context context) {
        super(options);
        mContext = context;
    }


    @NonNull
    @Override
    public RecyclerListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);
        return new RecyclerListViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerListViewHolder holder, int position, @NonNull Earnings model) {

        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.item_animation_slide_from_bottom));
        holder.date.setText(String.valueOf(model.getDate()));
        holder.payStubId.setText(model.getPayStubId());
        holder.amount.setText(String.valueOf(model.getAmount()));
    }



    public class RecyclerListViewHolder extends RecyclerView.ViewHolder {

        TextView amount,payStubId,date;
        private int mPosition;
        LinearLayout container;

        public RecyclerListViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            payStubId = itemView.findViewById(R.id.uniqueId);
            date = itemView.findViewById(R.id.date);
            container =itemView.findViewById(R.id.recycler_item);
            mContext=itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION && listener!=null){
                        listener.onItemClick(getSnapshots().getSnapshot(pos),pos);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void  setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}
