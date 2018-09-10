package com.example.rifat.readfromapi;

import android.app.ProgressDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rifat.readfromapi.Api.ApiClient;
import com.example.rifat.readfromapi.Api.getData;
import com.example.rifat.readfromapi.Model.dataModel;
import com.example.rifat.readfromapi.Model.dataModelList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        /*dataModel dataModel=new dataModel("1/1/2011 10:20PM", "blocked");
        dataModel dataModel1=new dataModel("1/1/2011 10:20PM",  "blocked");
        dataModel dataModel2=new dataModel("1/1/2011 10:20PM",  "allowed");
        List<dataModel> list=new ArrayList<>();
        list.add(dataModel);
        list.add(dataModel1);
        list.add(dataModel2);
        dataModelList dataModelList=new dataModelList(list,1,"Done");
        */
        getAllData();
    }
    private void getAllData()
    {
        pDialog = new ProgressDialog(
                this);
        pDialog.setMessage("Loading Data ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        getData apiService = ApiClient.getClient().create(getData.class);
        Call<dataModelList> call = apiService.getAlldata("getlog.php");
        call.enqueue(new Callback<dataModelList>() {
            @Override
            public void onResponse(Call<dataModelList> call, Response<dataModelList> response) {
                dataModelList listFoodModel = response.body();



                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_example);
                    for(int i=0;i<listFoodModel.getDataList().size();i++)
                    {
                        dataModel dataModel=listFoodModel.getDataList().get(i);
                        GradientDrawable gd = new GradientDrawable();
                        gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
                        gd.setCornerRadius(5);
                        gd.setStroke(1, 0xFF000000);
                        // Add textview 1
                        TextView textView1 = new TextView(DataActivity.this);
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        textView1.setText("timestamp : "+dataModel.getTimeStamp() +", Status: "+dataModel.getStatus());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            textView1.setBackground(gd);
                        }
                        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
                        linearLayout.addView(textView1);
                    }
                    pDialog.dismiss();



            }

            @Override
            public void onFailure(Call<dataModelList> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(DataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });// visible the progress bar

    }
    /*private  void getAllData(dataModelList dataModelList) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_example);
        for(int i=0;i<dataModelList.getDataList().size();i++)
        {
            dataModel dataModel=dataModelList.getDataList().get(i);
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(5);
            gd.setStroke(1, 0xFF000000);
            // Add textview 1
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setText("Date : "+dataModel.getDate()+", Time: "+dataModel.getTime()+", Status: "+dataModel.getStatus());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView1.setBackground(gd);
            }
            textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
            linearLayout.addView(textView1);
        }
    }*/
}
