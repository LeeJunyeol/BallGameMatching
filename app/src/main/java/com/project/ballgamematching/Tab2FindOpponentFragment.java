package com.project.ballgamematching;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TJ on 2017-04-27.
 */

public class Tab2FindOpponentFragment extends Fragment implements View.OnClickListener {
    int sYear, sMonth, sDay;
    TextView sTxtdate;
    View view;
    ArrayAdapter<CharSequence> scspin;
    boolean sInitSpinner;


//선언----------------------------------------------


    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab2_find_opponent, container, false);
//캘린더----------------------------------------------------------------------
        sTxtdate = (TextView) view.findViewById(R.id.txtdate);

        Calendar cal = new GregorianCalendar();
        sYear = cal.get(Calendar.YEAR);
        sMonth = cal.get(Calendar.MONTH);
        sDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();

        Button btnSearchDate = (Button) view.findViewById(R.id.searchdate);
        btnSearchDate.setOnClickListener(this);
//캘린더 끝-------------------------------------------------------------
//스피너 시작---------------------------------------------------------------------------------------------------------------------------

        Spinner spin = (Spinner) view.findViewById(R.id.timespinner);
        spin.setPrompt("[시간선택]");

        scspin = ArrayAdapter.createFromResource(view.getContext(), R.array.searchtime, android.R.layout.simple_spinner_item);
        scspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(scspin);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                           @Override
                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                               if (sInitSpinner == false) {
                                                   sInitSpinner = true;
                                                   return;

                                               }
                                               Toast.makeText(view.getContext(), parent.getItemAtPosition(position) + "시간", Toast.LENGTH_SHORT).show();
                                           }

                                           @Override
                                           public void onNothingSelected(AdapterView<?> parent) {

                                           }
                                       }
        );
//스피너 끝---------------------------------------------------------------------------------------------------------------

        return view;
    }
//캘린더 시작---------------------------------------------------------------------------------------------------------------
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchdate:
                new DatePickerDialog(view.getContext(), sDateSetListener, sYear, sMonth, sDay).show();

                break;
        }
    }

    DatePickerDialog.OnDateSetListener sDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sYear = year;
            sMonth = monthOfYear;
            sDay = dayOfMonth;
            UpdateNow();
        }
    };

    void UpdateNow() {
        sTxtdate.setText(String.format("%d년 %d월 %d일", sYear, sMonth + 1, sDay));
    }
//캘린더 끝---------------------------------------------------------------------------------------------------------------
}