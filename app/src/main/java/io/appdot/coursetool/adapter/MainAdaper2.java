package io.appdot.coursetool.adapter;

import android.app.Activity;
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
import io.appdot.coursetool.view.FileActivity;
import io.appdot.coursetool.view.Main2Activity;

public class MainAdaper2 extends
        RecyclerView.Adapter<MainAdaper2.Main2ViewHolder> {

    private LayoutInflater mInflater;
    Context mContext;
    private ArrayList<CourseModel> mCourseModels;

    public MainAdaper2(Context context, ArrayList<CourseModel> mCourseModels){

        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mCourseModels = mCourseModels;

    }

    @NonNull
    @Override
    public Main2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.single_list, parent, false);
        return new Main2ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Main2ViewHolder holder, int position) {
        CourseModel currentModel = mCourseModels.get(position);
        holder.bindTo(currentModel);
    }

    @Override
    public int getItemCount() {
        return mCourseModels.size();
    }

    public class Main2ViewHolder extends RecyclerView.ViewHolder
                    implements View.OnClickListener{

        private TextView textView;

        Main2ViewHolder(View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.singList_textView);
            itemView.setOnClickListener(this);
        }

         void bindTo(CourseModel currentModel) {
            textView.setText(currentModel.getCourseTitle());
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, FileActivity.class);
            mContext.startActivity(intent);
        }
    }
}
