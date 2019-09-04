package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.captonesecondstage.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {
    private String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.courses_name_txt)
        TextView mCoursesNameTxt;
        public MyViewHolder(View viewItems) {
            super(viewItems);
            ButterKnife.bind(this, viewItems);
        }
    }
    public CoursesAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CoursesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_material, parent, false);
        return new MyViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mCoursesNameTxt.setText(mDataset[position]);

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}
