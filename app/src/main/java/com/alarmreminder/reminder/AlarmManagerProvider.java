package com.alarmreminder.reminder;

import android.app.AlarmManager;
import android.content.Context;

/*
Provider zarządzający ustanawianiem nowych powiadomień
injectAlarmManager  jest wymagana do działania providera
 */
public class AlarmManagerProvider {
    private static final String TAG = AlarmManagerProvider.class.getSimpleName();
    private static AlarmManager sAlarmManager;
    public static synchronized void injectAlarmManager(AlarmManager alarmManager) {
        if (sAlarmManager != null) {
            throw new IllegalStateException("Alarm Manager Already Set");
        }
        sAlarmManager = alarmManager;
}
    static synchronized AlarmManager getAlarmManager(Context context) {
        if (sAlarmManager == null) {
            sAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        return sAlarmManager;
    }
}
