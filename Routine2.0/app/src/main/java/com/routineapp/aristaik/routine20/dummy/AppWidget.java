package com.routineapp.aristaik.routine20.dummy;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import com.routineapp.aristaik.routine20.FileHelper;
import com.routineapp.aristaik.routine20.ParserClass;
import com.routineapp.aristaik.routine20.Period;
import com.routineapp.aristaik.routine20.R;
import com.routineapp.aristaik.routine20.Setting_frgment;

import org.xml.sax.Parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

       // CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        String widgetText="inti";
        try {
            String text  = FileHelper.readFromFile("data.txt",context);
             String dept = Setting_frgment.FindString(text).toLowerCase();
             String sem =   Setting_frgment.FindNumber(text).toLowerCase();
            Log.d("from wedgit :  arif","dept : " + dept + "  sem :" + sem) ;
            ArrayList[] parserObject  = new ParserClass(FileHelper.readFromFile("data.xml",context)).getData(dept,sem) ;


            if(parserObject == null){
                Log.d("arif","in wedgit :---->> the returned list is null ");
            }
            widgetText = parserObject.toString();
            Log.d("arif",widgetText);
            widgetText = "";


            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == 7) dayOfWeek = 0;

            ArrayList list = parserObject[dayOfWeek];
            for(int i = 0 ; i<list.size();i++){
                Period p = (Period) list.get(i);
                widgetText+= p.getSub_name()+"\n" ;
            }


        }catch (Exception e){
            Log.d("arif","in wedgit   " +e.toString());
            widgetText =  e.toString();

        }RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        String widgetText="inti";
        try {
            String text  = FileHelper.readFromFile("data.txt",context);
            String dept = Setting_frgment.FindString(text).toLowerCase();
            String sem =   Setting_frgment.FindNumber(text).toLowerCase();
            Log.d("from wedgit :  arif","dept : " + dept + "  sem :" + sem) ;
            ArrayList[] parserObject  = new ParserClass(FileHelper.readFromFile("data.xml",context)).getData(dept,sem) ;


            if(parserObject == null){
                Log.d("arif","in wedgit :---->> the returned list is null ");
            }
            widgetText = parserObject.toString();
            Log.d("arif",widgetText);
            widgetText = "";


            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == 7) dayOfWeek = 0;

            ArrayList list = parserObject[dayOfWeek];
            for(int i = 0 ; i<list.size();i++){
                Period p = (Period) list.get(i);
                widgetText+= p.getSub_name()+"\n" ;
            }


        }catch (Exception e){
            Log.d("arif","in wedgit   " +e.toString());
            widgetText =  e.toString();

        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

