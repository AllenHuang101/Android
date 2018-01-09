package com.asus.a03_greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.asus.a03_greendao.entities.Student;
import com.asus.a03_greendao.gen.DaoSession;
import com.asus.a03_greendao.gen.StudentDao;
import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private DaoSession mDaoSession;
    private StudentDao mStudentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDaoSession = ((GreenDaoApp) getApplication()).getDaoSession();
        mStudentDao = mDaoSession.getStudentDao();


        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSearch = findViewById(R.id.btnSearch);
        final EditText etId = findViewById(R.id.etId);
        final EditText etName = findViewById(R.id.etName);
        final TextView tvResult = findViewById(R.id.tvResult);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student stu = new Student();


                String id = etId.getText().toString();
                String name = etName.getText().toString();

                stu.setId(new Long(id));
                stu.setName(name);
                mStudentDao.insert(stu);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Student> students = mStudentDao.queryBuilder()
                        .build().list();
                tvResult.setText(new Gson().toJson(students));

            }
        });



        Log.d("GreenDaoLab", DebugDB.getAddressLog());
    }
}
