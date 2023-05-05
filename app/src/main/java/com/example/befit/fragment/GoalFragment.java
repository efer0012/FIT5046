package com.example.befit.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.befit.R;
import com.example.befit.databinding.GoalFragmentBinding;
import com.example.befit.entity.Record;
import com.example.befit.viewmodel.RecordViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.time.LocalDate;
import java.time.ZoneId;


public class GoalFragment extends Fragment {
    private GoalFragmentBinding binding;
    private SharedPreferences sharedPreferences;
    private DatePicker datePicker;
    private EditText etHeight;
    private EditText etWeight;

    private Calendar selectedDate;
    private EditText weightEditText;
    private EditText heightEditText;
    private TextView dateTextView;

    private Button mShowDataButton;
    private TextView tvSavedData;

    private RecordViewModel recordViewModel;

    private List<Record> recordList;



    public GoalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        binding = GoalFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // 获取SharedPreferences对象
        sharedPreferences = requireActivity().getSharedPreferences("my_preferences", MODE_PRIVATE);

        // 获取各个控件
        datePicker = binding.datePicker;
        etHeight = binding.heightEdittext;
        etWeight = binding.weightEdittext;



        // 设置DatePicker的初始日期为当前日期
        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);

        // 从SharedPreferences中读取身高和体重数据
        String height = sharedPreferences.getString("height", "");
        String weight = sharedPreferences.getString("weight", "");
        etHeight.setText(height);
        etWeight.setText(weight);



        // 获取保存数据的TextView
        tvSavedData = binding.savedDataTextView;
        recordViewModel = new ViewModelProvider(requireActivity()).get(RecordViewModel.class);
        recordViewModel.getAllRecords().observe(getViewLifecycleOwner(), new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                // 更新UI
                String data = "";
                for (Record record : records) {
                    data += record.getDate_show() + ": " + record.getWeight() + "kg\n";
                }
                tvSavedData.setText(data);
                Log.d("LiveData", "LiveData updated with " + records.size() + " records");
            }
        });


        // 添加保存数据的按钮点击事件
        Button saveButton = binding.saveButton;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户选择的日期
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                LocalDate localDate = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    localDate = LocalDate.of(year, month, day);
                }
                // 将LocalDate转换为毫秒值
                long date = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    date = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
                }
                String date_show = year + "-" + month + "-" + day;
                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();


                // 保存日期、身高和体重数据到SharedPreferences中
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(date + "_height", height);
                editor.putString(date + "_weight", weight);
                editor.apply();

                // 验证用户输入的体重是否是数字
                if (!TextUtils.isDigitsOnly(weight)) {
                    // 如果用户输入的不是数字，则显示一个提示消息
                    binding.weightEdittext.setError("Please enter a valid weight");
                    return;
                }

                // 将数据插入到Room数据库中
                Record record = new Record(date, date_show, Float.parseFloat(height), Float.parseFloat(weight));
                recordViewModel.insert(record);
            }
        });

        // 添加更新数据的按钮点击事件
        Button updateButton = binding.updateButton;
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户选择的日期
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                LocalDate localDate = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    localDate = LocalDate.of(year, month, day);
                }
                // 将LocalDate转换为毫秒值
                long date = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    date = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
                }
                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();

                // 验证用户输入的体重是否是数字
                if (!TextUtils.isDigitsOnly(weight)) {
                    // 如果用户输入的不是数字，则显示一个提示消息
                    binding.weightEdittext.setError("Please enter a valid weight");
                    return;
                }

                // 更新日期、身高和体重数据到SharedPreferences中
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(date + "_height", height);
                editor.putString(date + "_weight", weight);
                editor.apply();

                // 更新数据到Room数据库中
                Record record = new Record(date, year + "-" + month + "-" + day, Float.parseFloat(height), Float.parseFloat(weight));
                recordViewModel.update(record);
            }
        });

        // 清楚输入内容
        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.weightEdittext.setText("");
                binding.heightEdittext.setText("");
            }
        });











        return view;
    }
    private void savedDataTextView() {
        String data = "";
        for (Record record : recordList) {
            data += record.getDate_show() + ": " + record.getWeight() + "kg\n";

        }
        tvSavedData.setText(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

