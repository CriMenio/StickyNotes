package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    private EditText noteEdt;
    private Button incresesSizeBtn,decresesSizeBtn,boldBtn,underLineBtn,italicBtn;
    private TextView sizeTV;
    float currentSize;
    StickyNotes note =new StickyNotes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEdt =findViewById(R.id.idEdt);
        incresesSizeBtn =findViewById(R.id.idBtnIncreasesSize);
        decresesSizeBtn =findViewById(R.id.idBtnReduceSize);
        boldBtn =findViewById(R.id.idBtnBOld);
        underLineBtn=findViewById(R.id.idBtnUnderline);
        italicBtn=findViewById(R.id.idBtnItalic);
        sizeTV=findViewById(R.id.idTVSize);
        currentSize = noteEdt.getTextSize();
        sizeTV.setText(""+currentSize);
        incresesSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sizeTV.setText(""+currentSize);
            currentSize++;
            noteEdt.setTextSize(currentSize);
            }
        });
        decresesSizeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sizeTV.setText(""+currentSize);
                currentSize--;
                noteEdt.setTextSize(currentSize);
            }
        });
        boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                italicBtn.setTextColor(getResources().getColor(R.color.white));
                italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdt.getTypeface().isBold()){
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    boldBtn.setTextColor(getResources().getColor(R.color.white));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }else{
                    boldBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }
        });

        underLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteEdt.getPaintFlags()==8){
                    underLineBtn.setTextColor(getResources().getColor(R.color.white));
                    underLineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEdt.setPaintFlags(noteEdt.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));

                }else{
                    underLineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    underLineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
        italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boldBtn.setTextColor(getResources().getColor(R.color.white));
                boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdt.getTypeface().isItalic()){
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    italicBtn.setTextColor(getResources().getColor(R.color.white));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));

                }else {
                    italicBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }
            }
        });
    }
    public void saveButton(View view){
        note.setStick(noteEdt.getText().toString(),this);
        updateWidget();
        Toast.makeText(this,"Note has been updated..",Toast.LENGTH_SHORT).show();
    }
    private void updateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName thisWidget = new ComponentName(this, AppWidget.class);
        remoteViews.setTextViewText(R.id.idTVWidget,noteEdt.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
    }

}