package io.appdot.coursetool.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.appdot.coursetool.R;
import io.appdot.coursetool.model.CourseModel;
import io.appdot.coursetool.view.Main2Activity;

public class MainAdapter extends
        RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private final Context context;
    private LayoutInflater mInflater;
        private ArrayList<CourseModel> mModelsData;

   public MainAdapter(Context context, ArrayList<CourseModel> mModelsData){
       mInflater = LayoutInflater.from(context);
       this.context = context;
       this.mModelsData = mModelsData;
   }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.single_list, parent , false);
        return new MainViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        CourseModel currentModel = mModelsData.get(position);
        holder.bindTo(currentModel);
    }

    @Override
    public int getItemCount() {
        return mModelsData.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder
                    implements View.OnClickListener{

             private TextView textView;

         MainViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.singList_textView);
            itemView.setOnClickListener(this);
        }

        void bindTo(CourseModel currentModel){
             textView.setText(currentModel.getCourseTitle());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Main2Activity.class);
            context.startActivity(intent);
        }
    }
}
