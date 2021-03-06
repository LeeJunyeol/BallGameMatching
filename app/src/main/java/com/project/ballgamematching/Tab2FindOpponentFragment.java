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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TJ on 2017-04-27.
 */

public class Tab2FindOpponentFragment extends Fragment implements View.OnClickListener {
    static final String[] LIST_MENU = {"List1", "List2", "List3"};
    int sYear, sMonth, sDay;
    TextView sTxtdate;
    View view;
    ArrayAdapter<CharSequence> scspin;

    boolean sInitSpinner;
    //찾기 버튼
    Button btn_out;
    //축구 농구 라디오그룹 (버튼)
    RadioGroup ssts;
    //팀, 용병, 원해요 라디오 그룹 (버튼)
    RadioGroup ssvs;


//선언----------------------------------------------

    //타켓API로 빌드(GregorianCalendar()원래minSDK 24로 맞춰야함)
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab2_find_opponent, container, false);
//라디오버튼-----------------------------------------------------------------------------------------
        ssts = (RadioGroup) view.findViewById(R.id.searchsports);
        ssvs = (RadioGroup) view.findViewById(R.id.searchversus);
        btn_out = (Button) view.findViewById(R.id.searchmat);
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //축구 농구 라디오그룹 (버튼)
                RadioButton sports = (RadioButton) view.findViewById(ssts.getCheckedRadioButtonId());
                //팀, 용병, 원해요 라디오 그룹 (버튼)
                RadioButton versus = (RadioButton) view.findViewById(ssvs.getCheckedRadioButtonId());
                //축구 농구 라디오그룹 (버튼)
                String str_sptype = sports.getText().toString();
                //팀, 용병, 원해요 라디오 그룹 (버튼)
                String str_vstype = versus.getText().toString();

//                ListView matchinglv = (ListView) view.findViewById(R.id.sclistview);
//                matchinglv.setRemoteViewsAdapter(Intent.parseIntent());
                //축구 농구 라디오그룹 (버튼)
                Toast.makeText(view.getContext(), str_sptype + "선택", Toast.LENGTH_SHORT).show();
                //팀, 용병, 원해요 라디오 그룹 (버튼)
                Toast.makeText(view.getContext(), str_vstype + "선택", Toast.LENGTH_SHORT).show();
            }
        });
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
// 리스트뷰 -----------------------------------------------------------------------------------------------
        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);
        ListView listView = (ListView) view.findViewById(R.id.sclistview);
        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get TextView's text
                String strText = (String) parent.getItemAtPosition(position);

            }
        });


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