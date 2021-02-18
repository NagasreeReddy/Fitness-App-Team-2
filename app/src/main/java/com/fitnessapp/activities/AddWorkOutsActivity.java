package com.fitnessapp.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapp.R;
import com.fitnessapp.api.ApiService;
import com.fitnessapp.models.ResponseData;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddWorkOutsActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = AddWorkOutsActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://getfitt.club/";
    private Uri uri;
    ProgressDialog pd;
    Spinner spinWorkoutType,spinWorkoutLevel,spinWorkoutTime;
    RadioButton radioMale,radioFemale;
    String gender;
    TextInputEditText etWorkoutname,etDescription,etVlink;
    Button btn_upload,btnAddWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_o_uts);

        getSupportActionBar().setTitle("Add Workout");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinWorkoutType=(Spinner)findViewById(R.id.spinWorkoutType);
        spinWorkoutLevel=(Spinner)findViewById(R.id.spinWorkoutLevel);
        spinWorkoutTime=(Spinner)findViewById(R.id.spinWorkoutTime);

        radioMale=(RadioButton)findViewById(R.id.radioMale);
        radioFemale=(RadioButton)findViewById(R.id.radioFemale);

        etWorkoutname=(TextInputEditText)findViewById(R.id.etWorkoutname);
        etDescription=(TextInputEditText)findViewById(R.id.etDescription);
        etVlink=(TextInputEditText)findViewById(R.id.etVlink);

        genderSelect();
        btn_upload=(Button)findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);

            }
        });

        btnAddWorkout=(Button)findViewById(R.id.btnAddWorkout);
        btnAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWorkout();


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, AddWorkOutsActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, AddWorkOutsActivity.this);
                file = new File(filePath);

            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    File file;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, AddWorkOutsActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }

    private void addWorkout() {
        pd = new ProgressDialog(AddWorkOutsActivity.this);
        pd.setTitle("Loading");
        pd.show();
        Map<String, String> map = new HashMap<>();
        map.put("wtype", spinWorkoutType.getSelectedItem().toString());
        map.put("level", spinWorkoutLevel.getSelectedItem().toString());
        map.put("gender", gender);
        map.put("wname", etWorkoutname.getText().toString());
        map.put("des", etDescription.getText().toString());
        map.put("tim", spinWorkoutTime.getSelectedItem().toString());
        map.put("vlink", etVlink.getText().toString());


        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService uploadImage = retrofit.create(ApiService.class);
        Call<ResponseData> fileUpload = uploadImage.userRegistration(fileToUpload, map);
        fileUpload.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                Toast.makeText(AddWorkOutsActivity.this, "Workout Added successfully. ", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddWorkOutsActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void genderSelect(){
        if (radioMale.isChecked()) {
            gender="Male";
        } else {
            gender="Female";
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}