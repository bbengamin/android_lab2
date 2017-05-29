package com.nure.lab2.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nure.lab2.R;
import com.nure.lab2.adapter.UserAdapter;
import com.nure.lab2.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static Map<Long, User> userMap = new HashMap<>();

    /*static {
        User user = new User();
        user.setId(0L);
        user.setName("test");
        user.setPhone("050");
        userMap.put(0L, user);
    }*/

    public static UserAdapter userAdapter;
    private ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userListView = (ListView) findViewById(R.id.clientsList);

        userAdapter = new UserAdapter(getApplicationContext(), userMap);
        userListView.setAdapter(userAdapter);

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + userMap.get(id).getPhone()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(), "FAIL!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                startActivity(intent);

            }
        });

        registerForContextMenu(userListView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.edit:
                editNote(info.id);
                return true;
            case R.id.delete:
                deleteNote(info.id);
                return true;
            case R.id.view:
                viewMore(info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void viewMore(long id) {
        Intent intent = new Intent("ViewEntityActivity");
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void deleteNote(long id) {
        userMap.remove(id);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void editNote(long id) {
        Intent intent = new Intent("EditEntityActivity");
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openEntityActivity(View view) {
        Intent intent = new Intent("EditEntityActivity");
        startActivity(intent);
    }
}
