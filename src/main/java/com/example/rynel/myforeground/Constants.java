package com.example.rynel.myforeground;

/**
 * Created by rynel on 10/9/2017.
 */

public class Constants {


    //action for music player with dummy strings
    public interface ACTION {
        public static String MAIN_ACTION = "com.rynel.foregroundservice.action.main";
        public static String INIT_ACTION = "com.rynel.foregroundservice.action.init";
        public static String PREV_ACTION = "com.rynel.foregroundservice.action.prev";
        public static String PLAY_ACTION = "com.rynel.foregroundservice.action.play";
        public static String NEXT_ACTION = "com.rynel.foregroundservice.action.next";
        public static String STARTFOREGROUND_ACTION = "com.rynel.foregroundservice.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.rynel.foregroundservice.action.stopforeground";
    }

    //notification of app running on foreground
    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}