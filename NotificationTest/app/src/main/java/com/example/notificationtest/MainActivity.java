package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice=(Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.send_notice:
                String channelId = "notification_simple";
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    PendingIntent pi =PendingIntent.getActivity(this,0,intent,0);
                    NotificationChannel channel = new NotificationChannel(channelId, "simple", NotificationManager.IMPORTANCE_HIGH);
                    manager.createNotificationChannel(channel);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, channelId);
                    builder .setContentTitle("This is content title");
                    builder.setContentText("This is content text");
                    builder.setWhen(System.currentTimeMillis());
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                    builder.setContentIntent(pi);
                    builder.setAutoCancel(true);//
                    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                    builder.build();
                    Notification notification = builder.build();
                    manager.notify(1, notification);
                }
                else{
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification notification = new NotificationCompat.Builder(MainActivity.this, channelId)
                            .setContentTitle("This is content title")
                            .setContentText("This is content text")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .build();
                    manager.notify(1, notification);
                    Toast.makeText(MainActivity.this,"lest 26",Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }
}