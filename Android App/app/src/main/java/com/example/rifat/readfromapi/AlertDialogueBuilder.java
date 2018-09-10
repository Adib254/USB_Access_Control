package com.example.rifat.readfromapi;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by abdullah on 6/29/16.
 */
public class AlertDialogueBuilder {
    String Title = "Message !!!";

    private String m_Text = "";
    Activity x;



    public void DialogueMsg(Activity m, String message) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(m);
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }


}
