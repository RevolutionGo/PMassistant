package tgtiger.cf.mylisttest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tgtiger.cf.mylisttest.model.Item;
import tgtiger.cf.mylisttest.util.SharedPerferenceHelper;

import static android.graphics.Color.GREEN;

public class SelectActivity extends AppCompatActivity {
    private List<Item> itemList;
    private ArrayAdapter adapter;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        StatusBarCompat.setStatusBarColor(this, 0xff669900, false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();//声明一个对象，并获得跳转过来的Intent对象
        key = intent.getStringExtra("selectkey");//从intent对象中获得数据
        //Log.i("selectkey=",key);

        /*绑定ListView*/
        ListView listView = (ListView) findViewById(R.id.main_listview);

        /*设置Adapter*/
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,getData());
        listView.setAdapter(adapter);

        // 添加item的监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //点击时弹出对话框，让用户选择修改或者是完成

                AlertDialog.Builder builder = new AlertDialog.Builder(SelectActivity.this);

                Locale curLocale = getResources().getConfiguration().locale;
                //通过Locale的equals方法，判断出当前语言环境
                String[] opt = new String[0];
                if (curLocale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    //中文
                    opt = new String[]{"查看概况","修改项目", "跟踪任务","删除项目"};
                } else {
                    //英文
                    opt = new String[]{"check","edit", "follow","done"};
                }


                builder.setItems(opt, new OnOptListener(itemList.get(i)));
                builder.show();

            }
        });

    }

    /**
     * item点击事件
     */
    private class OnOptListener implements DialogInterface.OnClickListener {
        private Item item;

        OnOptListener(Item item) {
            this.item = item;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case 0:
                    //点击查看
                    CheckActivity.startActivity(SelectActivity.this, item);
                    break;
                case 1:
                    //点击修改
                    EditItemActivity.startActivity(SelectActivity.this, item);
                    break;
                case 2:
                    //点击跟踪
                    FollowTaskActivity.startActivity(SelectActivity.this, item);
                    break;
                case 3:
                    //点击完成
                    //删除数据
                    SharedPerferenceHelper.delNode(SelectActivity.this, item);
                    break;
            }
            //隐藏弹出框
            dialogInterface.dismiss();

        }
    }

    /**
     * 获取本地数据
     */
    private List<String> getData() {
        itemList = SharedPerferenceHelper.getItemList(this);
        List<String> data = new ArrayList<String>();
        for(int i=0; i<itemList.size(); i++) {
            if (itemList.get(i).getTask_people() == key ||itemList.get(i).getPlan_start_time()== key||itemList.get(i).getPlan_end_time()== key) {
                data.add(itemList.get(i).getName());
                Log.i("selectkey=",data.get(i));
            }
        }
        return data;
    }





}
