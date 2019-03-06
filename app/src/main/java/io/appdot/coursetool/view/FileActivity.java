package io.appdot.coursetool.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import io.appdot.coursetool.R;

public class FileActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private static final String TAG = "FileActivity";
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        //setting the Toolbar
        setToolbar();

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference().child("past_questions");

    }

    public void setToolbar(){

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Course Tool");
        toolbar.setTitleTextColor(getResources().getColor(R.color.cardview_light_background));
        toolbar.inflateMenu(R.menu.upload_tool);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.upload){
                    performFileSearch();
                }

                return false;
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void performFileSearch(){


        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if(requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if(resultData != null){
                uri =resultData.getData();
                Log.i(TAG, "Uri" +uri.toString());
            }
            final int takeFlags = resultData.getFlags()
                    & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
// Check for the freshest data.
            getContentResolver().takePersistableUriPermission(uri, takeFlags);

            assert uri != null;
            StorageReference pqRef= mStorageReference.child(Objects.requireNonNull(uri.getLastPathSegment()));

            pqRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }
    }

    }



