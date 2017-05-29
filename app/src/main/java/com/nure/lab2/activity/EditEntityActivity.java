package com.nure.lab2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nure.lab2.R;
import com.nure.lab2.entity.User;

import java.util.Map;

public class EditEntityActivity extends AppCompatActivity {

    private EditText entityIdInput;
    private EditText entityNameInput;
    private EditText entityPhoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entity);

        entityIdInput = (EditText) findViewById(R.id.entityInputId);
        entityNameInput = (EditText) findViewById(R.id.entityName);
        entityPhoneInput = (EditText) findViewById(R.id.entityPhone);

        long id = getIntent().getLongExtra("id", -1);

        if (id != -1) {
            User user = MainActivity.userMap.get(id);
            entityIdInput.setText(String.valueOf(user.getId()));
            entityNameInput.setText(user.getName());
            entityPhoneInput.setText(user.getPhone());
        }

    }

    public void saveEntity(View view) {
        Map<Long, User> userMap = MainActivity.userMap;

        String idText = entityIdInput.getText().toString();
        Long id = idText.equals("") ? MainActivity.userMap.size() : Long.parseLong(idText);
        String name = entityNameInput.getText().toString();
        String phone = entityPhoneInput.getText().toString();

        User user;
        if (userMap.containsKey(id)) {
            user = userMap.get(id);
        } else {
            user = new User();
        }

        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        userMap.put(id, user);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
