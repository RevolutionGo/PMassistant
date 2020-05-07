package tgtiger.cf.mylisttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.githang.statusbar.StatusBarCompat;

import tgtiger.cf.mylisttest.model.Item;
import tgtiger.cf.mylisttest.util.SharedPerferenceHelper;

public class EditItemActivity extends AppCompatActivity {
    private Item item = new Item();//任务事项实体类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        StatusBarCompat.setStatusBarColor(this, 0xff669900, false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 获取 item 的值
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String plan_start_time = intent.getStringExtra("plan_start_time");
        String plan_end_time = intent.getStringExtra("plan_end_time");
        String people_number = intent.getStringExtra("people_number");
        String pm_write_time = intent.getStringExtra("pm_write_time");

        // 设置值
        item.setId(id);
        item.setName(name);
        item.setPlan_start_time(plan_start_time);
        item.setPlan_end_time(plan_end_time);
        item.setPeople_number(people_number);
        item.setPm_write_time(pm_write_time);

        // 绑定输入框和按钮
        EditText editItemName = (EditText) findViewById(R.id.edit_editText1);
        EditText planstart_time = (EditText) findViewById(R.id.plan_start_time);
        EditText planend_time = (EditText) findViewById(R.id.plan_end_time);
        EditText peoplenumber = (EditText) findViewById(R.id.people_number);
        EditText pmwrite_time = (EditText) findViewById(R.id.pm_write_time);
        Button buttonSubmit = (Button) findViewById(R.id.edit_submit_btn1);

        //设置初始值
        editItemName.setText(name);
        planstart_time.setText(plan_start_time);
        planend_time.setText(plan_end_time);
        peoplenumber.setText(people_number);
        pmwrite_time.setText(pm_write_time);

        // 设置点击事件
        buttonSubmit.setOnClickListener(new EditItemActivity.OnSubmitListener(editItemName,planstart_time,planend_time,peoplenumber,pmwrite_time,item));

    }


    private class OnSubmitListener implements View.OnClickListener {

        private EditText editItemName;
        private EditText planstart_time;
        private EditText planend_time;
        private EditText peoplenumber;
        private EditText pmwrite_time;

        private Item item;

        OnSubmitListener(EditText editItemName, EditText planstart_time,EditText planend_time,EditText peoplenumber,EditText pmwrite_time,Item item) {
            this.editItemName = editItemName;
            this.planstart_time = planstart_time;
            this.planend_time = planend_time;
            this.peoplenumber = peoplenumber;
            this.pmwrite_time = pmwrite_time;
            this.item = item;
        }

        @Override
        public void onClick(View view) {


            // 获取任务事项名字
            String itemName = editItemName.getText().toString();
            String planstarttime = planstart_time.getText().toString();
            String planendtime = planend_time.getText().toString();
            String peoplenumbers = peoplenumber.getText().toString();
            String pmwritetime = pmwrite_time.getText().toString();

            //保存
            item.setName(itemName);
            item.setPlan_start_time(planstarttime);
            item.setPlan_end_time(planendtime);
            item.setPeople_number(peoplenumbers);
            item.setPm_write_time(pmwritetime);

            //修改数据
            SharedPerferenceHelper.modifyNode(EditItemActivity.this, item);

//            关闭页面
            EditItemActivity.this.finish();
        }
    }


    /**
     * 启动Activity
     *
     * @param context
     * @param item
     */
    public static void startActivity(Context context, Item item) {

        Intent intent = new Intent(context, EditItemActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("name", item.getName());
        intent.putExtra("plan_start_time", item.getPlan_start_time());
        intent.putExtra("plan_end_time", item.getPlan_end_time());
        intent.putExtra("people_number", item.getPeople_number());
        intent.putExtra("pm_write_time", item.getPm_write_time());

        ((Activity) context).startActivityForResult(intent, 1);
    }

}
