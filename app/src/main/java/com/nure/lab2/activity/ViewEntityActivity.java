package com.nure.lab2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.nure.lab2.R;
import com.nure.lab2.entity.User;

import org.w3c.dom.Text;

public class ViewEntityActivity extends AppCompatActivity {
    
    private TextView viewEntityName;
    private TextView viewEntityPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entity);

        viewEntityName = (TextView) findViewById(R.id.viewEntityName);
        viewEntityPhone = (TextView) findViewById(R.id.viewEntityPhone);

        long id = getIntent().getLongExtra("id", 0);

        User user = MainActivity.userMap.get(id);

        viewEntityName.setText(user.getName());
        viewEntityPhone.setText(user.getPhone());
    }
}
