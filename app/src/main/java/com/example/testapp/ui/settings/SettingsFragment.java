package com.example.testapp.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.testapp.R;
import com.example.testapp.databinding.FragmentHomeBinding;
import com.example.testapp.databinding.FragmentSettingsBinding;

import java.util.Calendar;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    private SettingsViewModel viewModel;

        private TimePicker timePicker;
        private CheckBox repeatBox;
        private Button saveBtn;
        private CheckedTextView sunTv, monTv, tueTv, wedTv, thuTv, friTv, satTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  return inflater.inflate(R.layout.fragment_settings, container, false);

     //   viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Initialize views
        timePicker = binding.timePicker;
        repeatBox = binding.repeatBox;
        sunTv =binding.sunTv;
        monTv = binding.monTv;
        tueTv = binding.tueTv;
        wedTv = binding.wedTv;
        thuTv = binding.thuTv;
        friTv = binding.friTv;
        satTv = binding.satTv;
        saveBtn=binding.saveBtn;
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

            // Add any additional setup or listeners here
            setCheckedTextViewClickListener(sunTv);
            setCheckedTextViewClickListener(monTv);
            setCheckedTextViewClickListener(tueTv);
            setCheckedTextViewClickListener(wedTv);
            setCheckedTextViewClickListener(thuTv);
            setCheckedTextViewClickListener(friTv);
            setCheckedTextViewClickListener(satTv);

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int hour = viewModel.getHour();
                    int minute = viewModel.getMinute();

                    if (sunTv.isChecked()) {
                        viewModel.startAlarmBroadcastReceiver(requireContext(), Calendar.SUNDAY, hour, minute);
                    } else if (monTv.isChecked()) {
                        viewModel.startAlarmBroadcastReceiver(requireContext(), Calendar.MONDAY, hour, minute);
                    } else if (tueTv.isChecked()) {
                        viewModel.startAlarmBroadcastReceiver(requireContext(), Calendar.TUESDAY, hour, minute);
                    } else if (wedTv.isChecked()) {
                        viewModel.startAlarmBroadcastReceiver(requireContext(), Calendar.WEDNESDAY, hour, minute);
                    } else if (thuTv.isChecked()) {
                        viewModel.startAlarmBroadcastReceiver(requireContext(), Calendar.THURSDAY, hour, minute);
                    } else if (friTv.isChecked()) {
                        viewModel.startAlarmBroadcastReceiver(requireContext(), Calendar.FRIDAY, hour, minute);
                    } else if (satTv.isChecked()) {
                        viewModel.startAlarmBroadcastReceiver(requireContext(), Calendar.SATURDAY, hour, minute);
                    } else {
                        Toast.makeText(requireContext(), "Please enable the notification", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(requireContext(), "Notification set for " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
                }
            });
        }

    private void setCheckedTextViewClickListener(CheckedTextView checkedTextView) {
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView ctv = (CheckedTextView) v;
                ctv.toggle(); // Toggle the checked state
                Toast.makeText(requireContext(), "Inside SetChecked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        SettingsViewModel dashboardViewModel =
//                new ViewModelProvider(this).get(SettingsViewModel.class);
//
//        binding =  FragmentSettingsBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//
//
//        //final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }

