/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author MOHAMMAD
 */
public class TaskScheduler implements ServletContextListener {

    //For Prevent More than one Call
    static Boolean IsTaskRunning = false;
    ServletContext context;
    static Timer timer = new Timer();
    //Run in Start Up

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        ServerLog.Print("TaskScheduler.contextInitialized();");
        if (!Server.userNameSMS.equals("") && !Server.passwordSMS.equals("")) {
            Start();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        ServerLog.Print("TaskScheduler.contextDestroyed();");
        timer.cancel();
        if (IsTaskRunning) {
        }
    }

    static void Sms() {
        ServerLog.Print("Run: TaskScheduler.Sms();");
        //Set For 10Am
        long HourOfStartTaskEveryDay = 7;
        Calendar now = Calendar.getInstance();
        int CurrentMin = now.get(Calendar.MINUTE);
        int CurrentHour = now.get(Calendar.HOUR_OF_DAY);
        long MinDiffre = 0;
        //Find Difference Between Server Start And 10Am And Wait Correspond
        long HourDiff = 0;
        if ((HourOfStartTaskEveryDay - CurrentHour) >= 0) {
            HourDiff = (HourOfStartTaskEveryDay - CurrentHour) * 60 * 60 * 1000;
        } else {
            HourDiff = (24 - (HourOfStartTaskEveryDay - CurrentHour)) * 60 * 60 * 1000;
        }
        if (HourDiff != 0) {
            MinDiffre = CurrentMin * 60 * 1000;
        }
        long Wait = HourDiff - MinDiffre;

        long Period = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
//        long Period = 86400000;//5*60 * 1000;//Hour_OF_Send_Sms*60*60*1000
//        long Period = 10000;//5*60 * 1000;//Hour_OF_Send_Sms*60*60*1000

        TaskHMIS task = new TaskHMIS();
        timer.schedule(task, 5000, 4320000); // 60*60*24*100 = 8640000ms
//        timer.schedule(new TaskHMIS(), Wait, Period); // 60*60*24*100 = 8640000ms
    }

    
    
    public static void Start() {
        ServerLog.Print("...........::::::::|||||Run: TaskScheduler.Start();|||||::::::::............");
        if (!IsTaskRunning) {
            IsTaskRunning = true;
            Sms();
        }
    }

    public static void main(String[] args) {
        ServerLog.Print("Run: TaskScheduler.Main();");
        Start();

    }
}
