package com.example.befit.model;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//[Edward] this is created for test purposes only
//public class BeFitClasses {
//    private String className;
//    private String classTime;
//
//    public BeFitClasses(String name, String time) {
//        className = name;
//        classTime = time;
//    }
//    public String getClassName() {
//        return className;
//    }
//    public String getClassTime() {
//        return classTime;
//    }
//    //this is used to populate the list with a few items at the start of the application
//    //it is static so it can be called without instantiating the class
//    public static List<BeFitClasses> createContactsList() {
//        List<BeFitClasses> classes = new ArrayList<BeFitClasses>();
//        classes.add(new BeFitClasses( "Boxing","11:00 AM"));
//        classes.add(new BeFitClasses( "Yoga","01:00 PM"));
//        classes.add(new BeFitClasses( "Body Pump","03:00 PM"));
//        return classes;
//    }
//}

//testing pre-populate database for beFitClasses
public class BeFitClasses extends RoomDatabase.Callback {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        /*Scheduled classes
        MONDAY
        08:00 AM - Yoga
        10:00 AM - Pilates
        01:00 PM - Boxing
        03:00 PM - Body Pump
        05:00 PM - Cycling
        07:00 PM - Hot Yoga

        TUESDAY
        08:00 AM - Pilates
        10:00 AM - Cycling
        02:00 PM - Body Balance
        04:00 PM - Body Step
        06:00 PM - Yin Yoga
        07:00 PM - Boxing
        08:00 PM - Body Pump

        WEDNESDAY
        08:00 AM - Yoga
        10:00 AM - Boxing
        02:00 PM - Cycling
        04:00 PM - Zumba
        06:00 PM - Body Attack
        07:00 PM - Sprint
        09:00 PM - Hot Yoga

        THURSDAY
        08:00 AM - Body Attack
        10:00 AM - Cycling
        02:00 PM - Zumba
        05:00 PM - Pilates
        06:00 PM - Yoga
        08:00 PM - Cycling

        FRIDAY
        08:00 AM - Yoga
        10:00 AM - Pilates
        02:00 PM - Boxing
        05:00 PM - Zumba
        06:00 PM - Sprint
        08:00 PM - Hot Yoga

        SATURDAY
        08:00 AM - Pilates
        09:00 AM - Yoga
        10:00 AM - Cycling
        02:00 PM - Body Pump
        05:00 PM - Zumba
        06:00 PM - Martial Arts
        07:00 PM - Cycling
        09:00 PM - Hot Yoga

        SUNDAY
        10:00 AM - Yoga
        02:00 PM - Cycling
        04:00 PM - Hot Yoga
        05:00 PM - Meditation
        07:00 PM - Yin Yoga

         */

        // Insert data into database
        //MONDAY
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yoga', 'Monday', '08:00 AM', '08:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Pilates', 'Monday', '10:00 AM', '11:00 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Boxing', 'Monday', '01:00 PM', '02:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Body Pump', 'Monday', '03:00 PM', '03:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Monday', '05:00 PM', '05:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Hot Yoga', 'Monday', '07:00 PM', '08:00 PM');");

        //TUESDAY
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Pilates', 'Tuesday', '08:00 AM', '09:00 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Tuesday', '10:00 AM', '10:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Body Balance', 'Tuesday', '02:00 PM', '02:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Body Step', 'Tuesday', '04:00 PM', '05:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yin Yoga', 'Tuesday', '06:00 PM', '07:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Boxing', 'Tuesday', '07:00 PM', '08:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Body Pump', 'Tuesday', '08:00 PM', '08:45 PM');");

        //WEDNESDAY
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yoga', 'Wednesday', '08:00 AM', '08:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Boxing', 'Wednesday', '10:00 AM', '11:00 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Wednesday', '02:00 PM', '02:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Zumba', 'Wednesday', '04:00 PM', '04:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Body Attack', 'Wednesday', '06:00 PM', '07:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Sprint', 'Wednesday', '07:00 PM', '07:30 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Hot Yoga', 'Wednesday', '09:00 PM', '10:00 PM');");

        //THURSDAY
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Body Attack', 'Thursday', '08:00 AM', '09:00 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Thursday', '10:00 AM', '10:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Zumba', 'Thursday', '02:00 PM', '02:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Pilates', 'Thursday', '04:00 PM', '05:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yoga', 'Thursday', '06:00 PM', '06:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Thursday', '08:00 PM', '08:45 PM');");

        //FRIDAY
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yoga', 'Friday', '08:00 AM', '08:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Pilates', 'Friday', '10:00 AM', '11:00 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Boxing', 'Friday', '02:00 PM', '03:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Zumba', 'Friday', '05:00 PM', '05:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Sprint', 'Friday', '06:00 PM', '06:30 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Hot Yoga', 'Friday', '08:00 PM', '09:00 PM');");

        //SATURDAY
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Pilates', 'Saturday', '08:00 AM', '09:00 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yoga', 'Saturday', '09:00 AM', '09:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Saturday', '10:00 AM', '10:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Body Pump', 'Saturday', '02:00 PM', '02:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Zumba', 'Saturday', '05:00 PM', '05:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Martial Arts', 'Saturday', '06:00 PM', '06:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Saturday', '07:00 PM', '07:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Hot Yoga', 'Saturday', '09:00 PM', '10:00 PM');");

        //SUNDAY
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yoga', 'Sunday', '10:00 AM', '10:45 AM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Cycling', 'Sunday', '02:00 PM', '02:45 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Hot Yoga', 'Sunday', '04:00 PM', '05:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Meditation', 'Sunday', '05:00 PM', '06:00 PM');");
        db.execSQL("INSERT INTO classes (className, day, startTime, endTime) VALUES ('Yin Yoga', 'Sunday', '07:00 PM', '08:00 PM');");
    }
}
