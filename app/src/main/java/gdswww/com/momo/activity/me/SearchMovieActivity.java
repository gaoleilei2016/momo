package gdswww.com.momo.activity.me;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import gdswww.com.momo.R;

public class SearchMovieActivity extends Activity {
    private GridView gridViewType;
    private SimpleAdapter adater1;
    private ArrayList<HashMap<String, Object>> mList1;
    //	private static final int listContent1[] ={};
//	private static final int listContent1[] = { R.string.search_type_all,
//			R.string.search_type_love, R.string.search_type_biography,
//			R.string.search_type_cartoon, R.string.search_type_action,
//			R.string.search_type_ancient };
    private int typeLastItem = 0;
    private static final String listContent1[] = {"300", "580", "1880", "3000", "5800", "10800"};
    private static final String listContent2[] = {"￥:300", "￥:580", "￥:1880", "￥:3000", "￥:5800", "￥:10800"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMainUI();
    }

    private void initMainUI() {
        setContentView(R.layout.activity_gridview);
        initGridViewOne();
    }

    private void initGridViewOne() {
        gridViewType = (GridView) findViewById(R.id.search_type);
        mList1 = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < listContent1.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("radioIcon", R.drawable.border);
            map.put("radioText", listContent1[i]);
            map.put("radioText", listContent2[i]);
            mList1.add(map);
        }
        adater1 = new SimpleAdapter(getApplicationContext(), mList1,
                R.layout.activity_grid_item, new String[]{"radioIcon",
                "radioText", "radioText"}, new int[]{R.id.item_RadioImg,
                R.id.item_RadioText, R.id.item_RadioText1});
        if (gridViewType != null) {
            gridViewType.setAdapter(adater1);
            gridViewType.requestFocus();
            gridViewType.setOnItemClickListener(new MyTypeOnItemClick());
        }
    }

    private void changeItemImg(SimpleAdapter sa, int selectedItem, boolean isOn) {
        HashMap<String, Object> map = (HashMap<String, Object>) sa
                .getItem(selectedItem);
        if (isOn) {
            map.put("radioIcon", R.drawable.selectedborder);
        } else {
            map.put("radioIcon", R.drawable.border);
        }
        sa.notifyDataSetChanged();
    }

    private class MyTypeOnItemClick implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            System.out.println("search_type");
            if (typeLastItem != position) {
                if (typeLastItem >= 0) {
                    changeItemImg(adater1, typeLastItem, false);
                }
            }
            typeLastItem = position;
            changeItemImg(adater1, position, true);
            HashMap<String, Object> map = (HashMap<String, Object>) adater1
                    .getItem(position);
            String typeStr = (String) map.get("radioText");
            Toast.makeText(getApplicationContext(), typeStr, Toast.LENGTH_SHORT).show();
            System.out.println("Type string:" + typeStr);
        }
    }

}