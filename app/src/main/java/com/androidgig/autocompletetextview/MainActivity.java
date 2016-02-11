package com.androidgig.autocompletetextview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private AutoCompleteTextView actvColor;
    ArrayList<ColorModel> colorList=new ArrayList<>();
    AutoCompleteAdapter adapter;
    String colorArray[]=new String[]{"Black","Red","Green","Blue","White"};
    int colorIds[]=new int[]{Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,Color.WHITE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actvColor=(AutoCompleteTextView)findViewById(R.id.actvColor);
        actvColor.setThreshold(1);

        for(int i=0;i<colorArray.length;i++)
        {
            ColorModel model=new ColorModel();
            model.setId(i+1);
            model.setColorName(colorArray[i]);
            model.setColorId(colorIds[i]);
            colorList.add(model);
        }

        adapter=new AutoCompleteAdapter(this,R.layout.row_item,colorList);
        actvColor.setAdapter(adapter);

        actvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ColorModel model=(ColorModel)view.getTag();
                Toast.makeText(MainActivity.this,"Clicked " + model.getColorName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
