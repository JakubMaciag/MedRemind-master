package com.alarmreminder.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;


/*
Klasa do określenia sposobu wymiany informacji z systemem - "Baza Danych" notyfikacji/serwisów w przestrzeni systemowej URI
https://developer.android.com/guide/index.html

 */
public class AlarmReminderContract {

    private AlarmReminderContract() {}
    //określenie odniesienia do aplikacji
    public static final String CONTENT_AUTHORITY = "com.alarmreminder";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //określenie ścieżki do "silnika" operującego notyfikacjami
    public static final String PATH_VEHICLE = "reminder-path";

    //klasa okreslająca dane wprowadzane do systemej bazy danych
    public static final class AlarmReminderEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_VEHICLE);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_VEHICLE;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_VEHICLE;

        public final static String TABLE_NAME = "vehicles";

        public final static String _ID = BaseColumns._ID;
        public static final String KEY_TITLE = "title";
        public static final String KEY_DATE = "date";
        public static final String KEY_TIME = "time";
        public static final String KEY_REPEAT = "repeat";
        public static final String KEY_REPEAT_NO = "repeat_no";
        public static final String KEY_REPEAT_TYPE = "repeat_type";
        public static final String KEY_ACTIVE = "active";

    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
}
