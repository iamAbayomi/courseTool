package io.appdot.coursetool.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.appdot.coursetool.adapter.MainAdaper2;
import io.appdot.coursetool.adapter.MainAdapter;
import io.appdot.coursetool.R;
import io.appdot.coursetool.model.CourseModel;

public class Main2Activity extends AppCompatActivity {
    private ArrayList<CourseModel> mModelsData;
    private MainAdaper2 mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mModelsData = new ArrayList<>();
        mRecyclerView= findViewById(R.id.second_recycler_view);

        mAdapter = new MainAdaper2(this, mModelsData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initializeData();


    }

    private void initializeData() {
        String[] courseList = getResources().getStringArray(R.array.materials);

        for(int i=0; i<courseList.length; i++){
            mModelsData.add(new CourseModel(courseList[i]));
        }

    }
}
