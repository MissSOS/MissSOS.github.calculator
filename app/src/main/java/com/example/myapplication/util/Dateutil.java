package com.example.myapplication.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateutil {
    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

}
