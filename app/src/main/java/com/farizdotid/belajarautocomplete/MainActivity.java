package com.farizdotid.belajarautocomplete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    String[] namaProvinsi = {"Jawa Barat", "Jawa Timur", "Jawa Tengah", "Kalimantan", "Sulawesi", "Bali"};
    private AutoCompleteTextView actext_namaprov;
    private AutoCompleteTextView actext_namaprovdb;
    private DBHelperNamaProvinsi dbHelperNamaProvinsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelperNamaProvinsi = new DBHelperNamaProvinsi(this);
        dbHelperNamaProvinsi.loadContent();

        String HD;

        initAutoCompleteNamaProv();
        initAutoCompleteNamaProvDB();
    }

    private void initAutoCompleteNamaProv() {
        actext_namaprov = (AutoCompleteTextView) findViewById(R.id.actext_namaprov);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, namaProvinsi);
        actext_namaprov.setAdapter(adapter);
        actext_namaprov.setThreshold(1);
        String ubahsesuatu;

        actext_namaprov.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, (CharSequence) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAutoCompleteNamaProvDB() {
        actext_namaprovdb = (AutoCompleteTextView) findViewById(R.id.actext_namaprovdb);


        final String[] namaProvDB = dbHelperNamaProvinsi.SelectAllDataNamaProv();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, namaProvDB);
        actext_namaprovdb.setAdapter(adapter);
        actext_namaprovdb.setThreshold(1);
        actext_namaprovdb.dismissDropDown();

        actext_namaprovdb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, (CharSequence) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
