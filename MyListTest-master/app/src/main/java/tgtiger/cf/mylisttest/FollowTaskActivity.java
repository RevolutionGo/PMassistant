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

public class FollowTaskActivity extends AppCompatActivity {
    private Item item = new Item();//任务事项实体类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_task_item);
        StatusBarCompat.setStatusBarColor(this, 0xff669900, false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 获取 item 的值
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
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

        // 绑定输入框和按钮
        EditText taskname = (EditText) findViewById(R.id.task_name);
        EditText taskdetail = (EditText) findViewById(R.id.task_detail);
        EditText taskplan_start_time = (EditText) findViewById(R.id.task_plan_start_time);
        EditText taskplan_end_time = (EditText) findViewById(R.id.task_plan_end_time);
        EditText taskpeople = (EditText) findViewById(R.id.task_people);
        EditText taskwrite_time = (EditText) findViewById(R.id.task_write_time);
        EditText taskreal_start_time = (EditText) findViewById(R.id.task_real_start_time);
        EditText taskreal_end_time = (EditText) findViewById(R.id.task_real_end_time);
        EditText realwrite_time = (EditText) findViewById(R.id.real_write_time);
        EditText taskcomment = (EditText) findViewById(R.id.comment);
        Button buttonSubmit = (Button) findViewById(R.id.add_submit_btn1);

        //设置初始值
        taskname.setText(task_name);
        taskdetail.setText(task_detail);
        taskplan_start_time.setText(task_plan_start_time);
        taskplan_end_time.setText(task_plan_end_time);
        taskpeople.setText(task_people);
        taskwrite_time.setText(task_write_time);
        taskreal_start_time.setText(task_real_start_time);
        taskreal_end_time.setText(task_real_end_time);
        realwrite_time.setText(real_write_time);
        taskcomment.setText(comment);


        // 设置点击事件

        buttonSubmit.setOnClickListener(new FollowTaskActivity.OnSubmitListener(taskname, taskdetail,taskplan_start_time,taskplan_end_time,taskpeople,
                taskwrite_time,taskreal_start_time,taskreal_end_time,realwrite_time,taskcomment,item));

    }

    private class OnSubmitListener implements View.OnClickListener {

        private EditText taskname;
        private EditText taskdetail;
        private EditText taskplan_start_time;
        private EditText taskplan_end_time;
        private EditText taskpeople;
        private EditText taskwrite_time;
        private EditText taskreal_start_time;
        private EditText taskreal_end_time;
        private EditText realwrite_time;
        private EditText taskcomment;

        private Item item;

        OnSubmitListener(EditText taskname, EditText taskdetail,EditText taskplan_start_time,EditText taskplan_end_time,EditText taskpeople,
                         EditText taskwrite_time,EditText taskreal_start_time,EditText taskreal_end_time,EditText realwrite_time,EditText taskcomment,Item item) {
            this.taskname = taskname;
            this.taskdetail = taskdetail;
            this.taskplan_start_time = taskplan_start_time;
            this.taskplan_end_time = taskplan_end_time;
            this.taskpeople = taskpeople;
            this.taskwrite_time = taskwrite_time;
            this.taskreal_start_time = taskreal_start_time;
            this.taskreal_end_time = taskreal_end_time;
            this.realwrite_time = realwrite_time;
            this.taskcomment = taskcomment;
            this.item = item;
        }

        @Override
        public void onClick(View view) {


            // 获取任务事项名字
            String tname = taskname.getText().toString();
            String tdetail = taskdetail.getText().toString();
            String tplan_start_time = taskplan_start_time.getText().toString();
            String tplan_end_time = taskplan_end_time.getText().toString();
            String tpeople = taskpeople.getText().toString();
            String twrite_time = taskwrite_time.getText().toString();
            String treal_start_time = taskreal_start_time.getText().toString();
            String treal_end_time = taskreal_end_time.getText().toString();
            String trealwrite_time = realwrite_time.getText().toString();
            String tcomment = taskcomment.getText().toString();

            //保存
            item.setTask_name(tname);
            item.setTask_detail(tdetail);
            item.setTask_plan_start_time(tplan_start_time);
            item.setTask_plan_end_time(tplan_end_time);
            item.setTask_people(tpeople);
            item.setTask_write_time(twrite_time);
            item.setTask_real_start_time(treal_start_time);
            item.setTask_real_end_time(treal_end_time);
            item.setReal_write_time(trealwrite_time);
            item.setComment(tcomment);

            //添加数据
            SharedPerferenceHelper.taskNode(FollowTaskActivity.this, item);

//            关闭页面
            FollowTaskActivity.this.finish();
        }
    }


    /**
     * 启动Activity
     *
     * @param context
     * @param item
     */
    public static void startActivity(Context context, Item item) {

        Intent intent = new Intent(context, FollowTaskActivity.class);
        intent.putExtra("id", item.getId());
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



