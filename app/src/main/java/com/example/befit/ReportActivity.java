package com.example.befit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.befit.dao.RecordDao;
import com.example.befit.database.AppDatabase;
import com.example.befit.entity.Record;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button generateReportButton = findViewById(R.id.generate_report_button);
        generateReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker startDatePicker = findViewById(R.id.start_date_picker);
                DatePicker endDatePicker = findViewById(R.id.end_date_picker);

                // 获取开始日期
                int startYear = startDatePicker.getYear();
                int startMonth = startDatePicker.getMonth();
                int startDay = startDatePicker.getDayOfMonth();
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.set(startYear, startMonth, startDay);
                String startDate = startYear + "-" + (startMonth + 1) + "-" + startDay;

                // 获取结束日期
                int endYear = endDatePicker.getYear();
                int endMonth = endDatePicker.getMonth();
                int endDay = endDatePicker.getDayOfMonth();
                Calendar calendarEnd = Calendar.getInstance();
                calendarEnd.set(endYear, endMonth, endDay);
                String endDate = endYear + "-" + (endMonth + 1) + "-" + endDay;

                // Execute the database query in the background
                new GetWeightDataAsyncTask().execute(startDate, endDate);
            }
        });
    }

    private class GetWeightDataAsyncTask extends AsyncTask<String, Void, List<Record>> {
        @Override
        protected List<Record> doInBackground(String... params) {
            String startDate = params[0];
            String endDate = params[1];

            RecordDao recordDao = AppDatabase.getInstance(ReportActivity.this).recordDao();
            return recordDao.getWeightsBetweenDates(startDate, endDate);
        }

        @Override
        protected void onPostExecute(List<Record> weightList) {
            // Create the bar chart using the weightList data
            createBarChart(weightList);
        }
    }

    private void createBarChart(List<Record> weightList) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < weightList.size(); i++) {
            Record record = weightList.get(i);
            float weight = record.getWeight();
            float xValue = i;

            entries.add(new BarEntry(xValue, weight));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Weight");
        BarData barData = new BarData(dataSet);
        BarChart barChart = findViewById(R.id.bar_chart);
        barChart.setData(barData);

        // 设置自定义的横坐标标签
        ArrayList<String> xLabels = new ArrayList<>();
        for (int i = 0; i < weightList.size(); i++) {
            xLabels.add("Date " + (i + 1)); // 自定义标签，可以根据需求进行修改
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));

        barChart.invalidate();
    }

}