package com.mingle.ZiYou.content;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.mingle.ZiYou.adapter.MyAdapter;
import com.mingle.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ListView listView=(ListView)findViewById(R.id.comment_list);

        List<Map<String, Object>> list=getData();
        listView.setAdapter(new MyAdapter(this, list));

    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", R.drawable.demo_list);
            map.put("title", "title"+i);
            map.put("info", "information"+i);
            list.add(map);
        }
        return list;
    }
}
