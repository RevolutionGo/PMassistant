package tgtiger.cf.mylisttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

import tgtiger.cf.mylisttest.model.Item;

public class CheckActivity extends AppCompatActivity {
    private Item item = new Item();//任务事项实体类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_item);
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
        String task_name = intent.getStringExtra("task_name");
        String task_detail = intent.getStringExtra("task_detail");
        String task_plan_start_time = intent.getStringExtra("task_plan_start_time");
        String task_plan_end_time = intent.getStringExtra("task_plan_end_time");
        String task_people = intent.getStringExtra("task_people");
        String task_write_time = intent.getStringExtra("task_write_time");
        String task_real_start_time = intent.getStringExtra("task_real_start_time");
        String task_real_end_time = intent.getStringExtra("task_real_end_time");
        String real_write_time = intent.getStringExtra("real_write_time");
        String comment = intent.getStringExtra("comment");


        // 设置值
        item.setId(id);
        item.setName(name);
        item.setPlan_start_time(plan_start_time);
        item.setPlan_end_time(plan_end_time);
        item.setPeople_number(people_number);
        item.setPm_write_time(pm_write_time);
        item.setTask_name(task_name);
        item.setTask_detail(task_detail);
        item.setTask_plan_start_time(task_plan_start_time);
        item.setTask_plan_end_time(task_plan_end_time);
        item.setTask_people(task_people);
        item.setTask_write_time(task_write_time);
        item.setTask_real_start_time(task_real_start_time);
        item.setTask_real_end_time(task_real_end_time);
        item.setReal_write_time(real_write_time);
        item.setComment(comment);

        // 绑定文本框
        TextView editItemName = (TextView) findViewById(R.id.edit_editText1);
        TextView planstart_time = (TextView) findViewById(R.id.plan_start_time);
        TextView planend_time = (TextView) findViewById(R.id.plan_end_time);
        TextView peoplenumber = (TextView) findViewById(R.id.people_number);
        TextView pmwrite_time = (TextView) findViewById(R.id.pm_write_time);
        TextView taskname = (TextView) findViewById(R.id.task_name);
        TextView taskdetail = (TextView) findViewById(R.id.task_detail);
        TextView taskplan_start_time = (TextView) findViewById(R.id.task_plan_start_time);
        TextView taskplan_end_time = (TextView) findViewById(R.id.task_plan_end_time);
        TextView taskpeople = (TextView) findViewById(R.id.task_people);
        TextView taskwrite_time = (TextView) findViewById(R.id.task_write_time);
        TextView taskreal_start_time = (TextView) findViewById(R.id.task_real_start_time);
        TextView taskreal_end_time = (TextView) findViewById(R.id.task_real_end_time);
        TextView realwrite_time = (TextView) findViewById(R.id.real_write_time);
        TextView comments = (TextView) findViewById(R.id.comment);

        //设置初始值
        editItemName.setText(name);
        planstart_time.setText(plan_start_time);
        planend_time.setText(plan_end_time);
        peoplenumber.setText(people_number);
        pmwrite_time.setText(pm_write_time);
        taskname.setText(task_name);
        taskdetail.setText(task_detail);
        taskplan_start_time.setText(task_plan_start_time);
        taskplan_end_time.setText(task_plan_end_time);
        taskpeople.setText(task_people);
        taskwrite_time.setText(task_write_time);
        taskreal_start_time.setText(task_real_start_time);
        taskreal_end_time.setText(task_real_end_time);
        realwrite_time.setText(real_write_time);
        comments.setText(comment);

    }



    /**
     * 启动Activity
     *
     * @param context
     * @param item
     */
    public static void startActivity(Context context, Item item) {

        Intent intent = new Intent(context, CheckActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("name", item.getName());
        intent.putExtra("plan_start_time", item.getPlan_start_time());
        intent.putExtra("plan_end_time", item.getPlan_end_time());
        intent.putExtra("people_number", item.getPeople_number());
        intent.putExtra("pm_write_time", item.getPm_write_time());
        intent.putExtra("task_name", item.getTask_name());
        intent.putExtra("task_detail", item.getTask_detail());
        intent.putExtra("task_plan_start_time", item.getTask_plan_start_time());
        intent.putExtra("task_plan_end_time", item.getTask_plan_end_time());
        intent.putExtra("task_people", item.getTask_people());
        intent.putExtra("task_write_time", item.getTask_write_time());
        intent.putExtra("task_real_start_time", item.getTask_real_start_time());
        intent.putExtra("task_real_end_time", item.getTask_real_end_time());
        intent.putExtra("real_write_time", item.getReal_write_time());
        intent.putExtra("comment", item.getComment());


        ((Activity) context).startActivityForResult(intent, 1);
    }

}
