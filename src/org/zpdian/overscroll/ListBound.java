package org.zpdian.overscroll;

import java.util.ArrayList;
import java.util.HashMap;

import com.zpdian.overscroll.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class ListBound extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.anyview);
        setContentView(R.layout.main);
        NewListView list = (NewListView) findViewById(R.id.MyListView);
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = null; 
        for (int i = 0; i < 30; i++) {
            map = new HashMap<String, String>();
            map.put("ItemTitle", "Title.....");
            map.put("ItemText", "text.....");
            mylist.add(map);
        }
        map = new HashMap<String, String>();
        map.put("ItemTitle", "XXXXXXXXXXXXXXXXXX....");
        mylist.add(map);

        SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.my_listitem,

        new String[] {
                "ItemTitle", "ItemText"
        },

        new int[] {
                R.id.ItemTitle, R.id.ItemText
        });

        list.setAdapter(mSchedule);

    }
}
