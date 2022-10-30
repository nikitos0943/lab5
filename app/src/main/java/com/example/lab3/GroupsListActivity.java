package com.example.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Arrays;

public class GroupsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_list);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String group = ((StudentsGroup) adapterView.getItemAtPosition(i)).toString();
                Intent intent = new Intent(GroupsListActivity.this,
                        StudentsGroupActivity.class);
                intent.putExtra(StudentsGroupActivity.GROUP_NUMBER, group);
                startActivity(intent);
            }
        };
        ListView listView = (ListView) findViewById(R.id.groups_list);
        listView.setOnItemClickListener(listener);

    }

    protected void onStart(){
        super.onStart();
        ListView listView = (ListView) findViewById(R.id.groups_list);
        ArrayAdapter<StudentsGroup> adapter = new ArrayAdapter<StudentsGroup>(
                this,
                android.R.layout.simple_list_item_1,
                StudentsGroup.getGroups()
        );
        listView.setAdapter(adapter);
    }

    private final static ArrayList<StudentsGroup> groups = new ArrayList<>(
            Arrays.asList(
                    new StudentsGroup("К20-1", "Компьютерные науки",
                            0, true, false),
                    new StudentsGroup("К21-1", "Компьютерные науки",
                            0, false, true),
                    new StudentsGroup("К21-1м", "Компьютерные науки",
                            1, true, false)
            )
    );

    public void onGrpAddClick (View view){
        startActivity(
                new Intent(this,AddStudentsGroupActivity.class)
        );
    }

    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_group:
                startActivity(
                        new Intent(this, AddStudentsGroupActivity.class)
                );
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.groups_menu, menu);

        String text = "";
        for (StudentsGroup group: StudentsGroup.getGroups()){
            text += group.getNumber() + "\n";

        }

        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }

}