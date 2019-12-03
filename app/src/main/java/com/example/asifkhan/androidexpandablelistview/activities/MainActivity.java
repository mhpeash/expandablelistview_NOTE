package com.example.asifkhan.androidexpandablelistview.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifkhan.androidexpandablelistview.Model.Note;
import com.example.asifkhan.androidexpandablelistview.R;
import com.example.asifkhan.androidexpandablelistview.adapters.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.asifkhan.androidexpandablelistview.DBConfig.AppDatabase.getAppDatabase;

public class MainActivity extends AppCompatActivity {


    Button btnSave;
    EditText NoteItem;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;


    private List<String> parent_title;

    private HashMap<String, List<String>> child_title;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

//    private static String PARENT_TITLE_ONE = "Parent One";
//    private static String PARENT_TITLE_TWO = "Parent Two";
//    private static String PARENT_TITLE_THREE = "Parent Three";
//    private static String PARENT_TITLE_FOUR = "Parent Four";
//
//    private static String CHILD_TITLE_ONE = "Child One";
//    private static String CHILD_TITLE_TWO = "Child Two";
//    private static String CHILD_TITLE_THREE = "Child Three";
//    private static String CHILD_TITLE_FOUR = "Child Four";
    List<Note> ItemByTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        parent_title = new ArrayList<>();
        child_title = new HashMap<>();

        expandableListAdapter = new ExpandableListAdapter(parent_title, child_title, this);

        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);


        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
               // Toast.makeText(MainActivity.this, parent_title.get(i) + " expanded", Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(MainActivity.this, parent_title.get(i) + " -> " + child_title.get(parent_title.get(i)).get(i1), Toast.LENGTH_SHORT).show();

                TextView text = (TextView) view;
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                text.setTextColor(Color.parseColor("#ff0000"));

                final String selected = (String) expandableListAdapter.getChild(i, i1);
                int isUpdated= getAppDatabase(MainActivity.this).noteDao().UpdateDoneStatus(selected);
                Log.d("Statused Changed", String.valueOf(isUpdated));
                return true;
            }
        });

        getExpandableList();
    }




    public void GotoSave(View v){
        startActivity(new Intent(this,SaveNote.class));
    }

    // getting expandable list view
    private void getExpandableList() {

        List<String> allNote = getAppDatabase(this).noteDao().GetAllNoteTitles();

        for (int i = 0; i < allNote.size(); i++) {
            /**
             * Get Note Title
            */
            parent_title.add(allNote.get(i));
            /**
            * GetAll By Note Title
            */
            ItemByTitle = getAppDatabase(this).noteDao().GetAllByTitle(allNote.get(i));
            /**
             * Item collection by Title
             */
            List<String> parent = new ArrayList<>();
            for (int j = 0; j < ItemByTitle.size(); j++) {

             //   if((ItemByTitle.get(j).getIsDone())==0)
             //   {
                    parent.add(ItemByTitle.get(j).getNoteItem());
             //   }
             //   else
             //       {
                       // backGroundToChange(ItemByTitle.indexOf(j));
               //         parent.add("Completed----->"+ItemByTitle.get(j).getNoteItem());
               //     }

            }
            /**
             * Setting Title and its collection to hashmap
             */
            child_title.put(allNote.get(i), parent);
        }


//        parent_title.add(PARENT_TITLE_ONE);
//        parent_title.add(PARENT_TITLE_TWO);
//        parent_title.add(PARENT_TITLE_THREE);
//        parent_title.add(PARENT_TITLE_FOUR);

        // parent one
//        List<String> parent_one = new ArrayList<>();
//        parent_one.add(CHILD_TITLE_ONE);
//        // parent_one.add(CHILD_TITLE_TWO);
//        // parent_one.add(CHILD_TITLE_THREE);
//        // parent_one.add(CHILD_TITLE_FOUR);
//
//        // parent two
//        List<String> parent_two = new ArrayList<>();
//        parent_two.add(CHILD_TITLE_ONE);
//        parent_two.add(CHILD_TITLE_TWO);
//        // parent_two.add(CHILD_TITLE_THREE);
//        //  parent_two.add(CHILD_TITLE_FOUR);

//        // parent three
//        List<String> parent_three = new ArrayList<>();
//        parent_three.add(CHILD_TITLE_ONE);
//        parent_three.add(CHILD_TITLE_TWO);
//        parent_three.add(CHILD_TITLE_THREE);
//        // parent_three.add(CHILD_TITLE_FOUR);
//
//        // parent four
//
//        List<String> parent_four = new ArrayList<>();
//
//        parent_four.add(CHILD_TITLE_ONE);
//        parent_four.add(CHILD_TITLE_TWO);
//        parent_four.add(CHILD_TITLE_THREE);
//        parent_four.add(CHILD_TITLE_FOUR);
//
//        // parent_four.addAll(arrayList);
//
//        // adding all the child with the respective parent
//        child_title.put(PARENT_TITLE_ONE, parent_one);
//        child_title.put(PARENT_TITLE_TWO, parent_two);
//        child_title.put(PARENT_TITLE_THREE, parent_three);
//        child_title.put(PARENT_TITLE_FOUR, parent_four);
    }





}
