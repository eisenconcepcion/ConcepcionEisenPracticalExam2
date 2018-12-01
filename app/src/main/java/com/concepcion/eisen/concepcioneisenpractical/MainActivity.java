package com.concepcion.eisen.concepcioneisenpractical;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etFn, etLn, etExam1, etExam2;
    TextView tvAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFn = findViewById(R.id.etFirstName);
        etLn = findViewById(R.id.etLastName);
        etExam1 = findViewById(R.id.etExam1);
        etExam2 = findViewById(R.id.etExam2);
        tvAverage = findViewById(R.id.tvAverage);


    }


    public void saveAndCompute(View v){

        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "records.txt");

        String fname = etFn.getText().toString();
        String lname = etLn.getText().toString();
        String exam1 = etExam1.getText().toString();
        String exam2 = etExam2.getText().toString();
        String temp1 = "Persist: ";
        String comma = ", ";

        double average = Integer.parseInt(exam1) + Integer.parseInt(exam2);
        average = average / 2;
        String average_String = Double.toString(average);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(temp1.getBytes());
            fos.write(fname.getBytes()); //First name
            fos.write(comma.getBytes());
            fos.write(lname.getBytes()); //Last name
            fos.write(comma.getBytes());
            fos.write(average_String.getBytes()); //Average
            Toast.makeText(this, "Data saved SUCCESSFUL in SD card..", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "ERROR saving in SD card", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG).show();

        }

        tvAverage.setText(average_String);
        

    }
}
