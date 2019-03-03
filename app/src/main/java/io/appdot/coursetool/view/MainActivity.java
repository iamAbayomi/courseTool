package io.appdot.coursetool.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.appdot.coursetool.adapter.MainAdapter;
import io.appdot.coursetool.R;
import io.appdot.coursetool.model.CourseModel;


public class MainActivity extends AppCompatActivity {
    private ArrayList<CourseModel> mModelsData;
    private MainAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mModelsData = new ArrayList<>();
        mRecyclerView = findViewById(R.id.main_recycler_view);

        mAdapter = new MainAdapter(this, mModelsData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initializeData();
    }

    public void initializeData(){
        String[] courseList = getResources().getStringArray(R.array.Courses);

        for(int i=0; i<courseList.length; i++){
            mModelsData.add( new CourseModel(courseList[i]));
        }
    }
}
