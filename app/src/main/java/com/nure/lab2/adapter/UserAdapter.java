package com.nure.lab2.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nure.lab2.R;
import com.nure.lab2.activity.MainActivity;
import com.nure.lab2.entity.User;

import java.util.List;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by igor.bogdanov on 19.05.2017.
 */
public class UserAdapter extends BaseAdapter {

    private Context context;
    private Map<Long, User> userMap;
    private LayoutInflater layoutInflater;

    public UserAdapter(Context context, Map<Long, User> userMap) {
        this.context = context;
        this.userMap = userMap;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userMap.size();
    }

    @Override
    public Object getItem(int i) {
        return userMap.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View content = view;
        if (content == null) {
            content = layoutInflater.inflate(R.layout.item_layout, viewGroup, false);
        }

        User user = userMap.get((long) i);
        if (user != null) {
            TextView entityIdInput = (TextView) content.findViewById(R.id.entityListName);
            entityIdInput.setText(user.getName());
        }

        return content;
    }
}
