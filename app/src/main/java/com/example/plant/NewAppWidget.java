package com.example.plant;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.google.firebase.auth.FirebaseAuth;

public class NewAppWidget extends AppWidgetProvider {
private FirebaseAuth firebaseAuth;
    int level = 1;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.color, pendingIntent);

            this.refresh(context, views);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private void refresh(Context context, RemoteViews views){
        firebaseAuth = FirebaseAuth.getInstance();
        String uID=firebaseAuth.getUid();
        SharedPreferences sf = context.getSharedPreferences(uID,Context.MODE_PRIVATE);
        level = sf.getInt("level",1);
//        SharedPreferences sharedPreferences = context.getSharedPreferences("levelNumber", Context.MODE_PRIVATE);
//        level = sharedPreferences.getInt("levelNumber", 1);

        if(level == 1){
            views.setImageViewResource(R.id.color, R.drawable.level1);
            Log.d("level1", "----- level1 Called! -----");
        }else if(level == 2){
            Log.d("level2", "----- level2 Called! -----");
            views.setImageViewResource(R.id.color, R.drawable.level2);
        }else if(level == 3){
            views.setImageViewResource(R.id.color, R.drawable.level3);
        }else if(level == 4){
            views.setImageViewResource(R.id.color, R.drawable.level4);
        }
    }

    public void onReceive(Context context, Intent intent) {
        // Protect against rogue update broadcasts (not really a security issue,
        // just filter bad broacasts out so subclasses are less likely to crash).
        String action = intent.getAction();
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                int[] appWidgetIds = extras.getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS);
                if (appWidgetIds != null && appWidgetIds.length > 0) {
                    this.onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds);
                }
            }
        }
        else if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey(AppWidgetManager.EXTRA_APPWIDGET_ID)) {
                final int appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);
                this.onDeleted(context, new int[] { appWidgetId });
            }
        }
        else if (AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)) {
            this.onEnabled(context);
        }
        else if (AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)) {
            this.onDisabled(context);
        }
    }
}

