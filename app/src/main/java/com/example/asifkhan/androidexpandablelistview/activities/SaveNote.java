package com.example.asifkhan.androidexpandablelistview.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asifkhan.androidexpandablelistview.Model.Note;
import com.example.asifkhan.androidexpandablelistview.R;

import java.util.ArrayList;

import static com.example.asifkhan.androidexpandablelistview.DBConfig.AppDatabase.getAppDatabase;

public class SaveNote extends AppCompatActivity {

    Button btnSave,btnSaveNote;
    EditText NoteItem,NoteTitle;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_note);
        NoteItem=findViewById(R.id.NoteItem);
        NoteTitle=findViewById(R.id.NoteTitle);

        if(!(NoteItem.equals(""))||!(NoteTitle.equals(""))) {
            AddNote();
        }

    }


    private void  AddNote(){

        btnSave=findViewById(R.id.btnSave);

        noteList = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.itemList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text = (TextView) view;
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                text.setTextColor(Color.parseColor("#ff0000"));
            }
        });
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, noteList);
        // Here, you set the data in your ListView
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this line adds the data of your EditText and puts in your array
                noteList.add(NoteItem.getText().toString());
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();

                Save();
                NoteItem.setText("");
            }
        });


    }


    protected  void Save(){
        Note aNote=new Note();
        aNote.setTitle(NoteTitle.getText().toString());
        aNote.setNoteItem(NoteItem.getText().toString());
        aNote.setIsDone(0);
        long value=getAppDatabase(this).noteDao().AddNote(aNote);
        Log.d(String.valueOf(value), "added to note db");
    }

    public void GotoList(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}
