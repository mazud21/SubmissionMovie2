package com.gmail.hmazud.submissionmovie2.service;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.gmail.hmazud.submissionmovie2.MainActivity;
import com.gmail.hmazud.submissionmovie2.R;

import java.util.Calendar;

public class AlarmApps extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 101;

    public AlarmApps() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = context.getString(R.string.app_name);
        String pesan = context.getString(R.string.remindApps);
        showNotif(context, name, pesan, NOTIFICATION_ID);
    }

    private void showNotif(Context context, String judul, String pesan, int id) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                context.NOTIFICATION_SERVICE
        );
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_image_black_24dp)
                .setContentTitle(judul)
                .setContentText(pesan)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000})
                .setSound(uri);

        notificationManager.notify(id, builder.build());
    }

    public void repeatingAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent(context));
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent(context));
    }

    private PendingIntent pendingIntent(Context context) {
        Intent intent = new Intent(context, AlarmApps.class);
        return PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}