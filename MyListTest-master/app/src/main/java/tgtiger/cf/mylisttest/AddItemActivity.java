package tgtiger.cf.mylisttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.githang.statusbar.StatusBarCompat;

import java.util.Date;

import tgtiger.cf.mylisttest.model.Item;
import tgtiger.cf.mylisttest.util.SharedPerferenceHelper;

public class AddItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        StatusBarCompat.setStatusBarColor(this, 0xff669900, false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editItemName = (EditText) findViewById(R.id.add_editText1);
        EditText plan_start_time = (EditText) findViewById(R.id.plan_start_time);
        EditText plan_end_time = (EditText) findViewById(R.id.plan_end_time);
        EditText people_number = (EditText) findViewById(R.id.people_number);
        EditText pm_write_time = (EditText) findViewById(R.id.pm_write_time);
        Button buttonSubmit = (Button) findViewById(R.id.add_submit_btn1);

        /*绑定监听器*/
        buttonSubmit.setOnClickListener(new OnSubmitListener(editItemName,plan_start_time,plan_end_time,people_number,pm_write_time));


    }

    private class OnSubmitListener implements View.OnClickListener {
        private EditText editItemName;
        private EditText plan_start_time;
        private EditText plan_end_time;
        private EditText people_number;
        private EditText pm_write_time;

        OnSubmitListener(EditText editItemName,EditText plan_start_time,EditText plan_end_time,EditText people_number,EditText pm_write_time) {
            this.editItemName = editItemName;
            this.plan_start_time = plan_start_time;
            this.plan_end_time = plan_end_time;
            this.people_number = people_number;
            this.pm_write_time = pm_write_time;
        }

        @Override
        public void onClick(View view) {
            /*获取任务事项名字*/
            String itemName = editItemName.getText().toString();
            String planstart_time = plan_start_time.getText().toString();
            String planend_time = plan_end_time.getText().toString();
            String peoplenumber = people_number.getText().toString();
            String pmwrite_time = pm_write_time.getText().toString();

            String task_name ="";
            String task_detail ="";
            String task_plan_start_time ="";
            String task_plan_end_time ="";
            String task_people ="";
            String task_write_time ="";
            String task_real_start_time ="";
            String task_real_end_time ="";
            String real_write_time ="";
            String comment ="";


            //保存
            Item item = new Item();
            item.setName(itemName);
            item.setPlan_start_time(planstart_time);
            item.setPlan_end_time(planend_time);
            item.setPeople_number(peoplenumber);
            item.setPm_write_time(pmwrite_time);

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

            item.setId(Long.valueOf(new Date().getTime()).intValue());

            SharedPerferenceHelper.addNode(AddItemActivity.this, item);
            AddItemActivity.this.finish();
        }
    }

    /**
     * 启动Activity
     */

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddItemActivity.class);
        ((Activity) context).startActivityForResult(intent, 1);
    }

}
