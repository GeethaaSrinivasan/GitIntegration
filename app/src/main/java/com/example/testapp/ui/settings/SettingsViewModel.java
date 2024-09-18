package com.example.testapp.ui.settings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.example.testapp.NotificationReceiver;

import java.util.Calendar;

public class SettingsViewModel extends ViewModel {

//    private final MutableLiveData<String> mText;
//
//    public SettingsViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is dashboard fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }


        private int hour;
        private int minute;
        private boolean repeat;
        private boolean[] days;

        public SettingsViewModel(int hour, int minute, boolean repeat, boolean[] days) {
            this.hour = hour;
            this.minute = minute;
            this.repeat = repeat;
            this.days = days;
        }

        // Getters and setters
        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public boolean isRepeat() {
            return repeat;
        }

        public void setRepeat(boolean repeat) {
            this.repeat = repeat;
        }

        public boolean[] getDays() {
            return days;
        }

        public void setDays(boolean[] days) {
            this.days = days;
        }

    public void startAlarmBroadcastReceiver(Context context, int day, int hour, int minute) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
    }

