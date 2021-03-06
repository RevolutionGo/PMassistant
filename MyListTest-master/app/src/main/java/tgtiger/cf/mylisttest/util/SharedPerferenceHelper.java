package tgtiger.cf.mylisttest.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tgtiger.cf.mylisttest.model.Item;


public class SharedPerferenceHelper {

    public static void main(String[] args) {

    }
    //SharedPerference存放的路径
    private static String FILE_NAME = "myList";

    private static String KEY = "myList";

    private static void save(Context context, List<Item> itemList) {
//        //数组转化为字符串
//        JSONObject jo;
//        JSONArray ja = new JSONArray();
////        way1
////        ja = JSON.parseArray(itemList.toString());
//
////        way2
//        for(int i=0; i<itemList.size(); i++) {
//            System.out.println();
//            jo = JSON.parseObject(itemList.get(i).toString());
//            ja.add(jo);
//        }
//        String jsonStr = ja.toJSONString();
        String jsonStr = JSON.toJSONString(itemList);
        //保存字符串到SharedPerference中
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(KEY, jsonStr);
        edit.commit();
    }
    /**
     * 获取任务事项数组
     *
     */
    public static List<Item> getItemList(Context context) {
        //获得json字符串
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        //第二个参数意思为如果为获取到,则将第二个参数值赋给前面
        String jsonStr = sp.getString(KEY, "[]");

        //将字符串转化为数组
//        JSONArray ja = JSON.parseArray(jsonStr);
        List<Item> itemList = new ArrayList<>();

//        for(int i=0; i<ja.size(); i++) {
//            JSONObject jo = ja.getJSONObject(i);
//            Item item = JSON.parseObject(jo.toJSONString(), Item.class);
//            itemList.add(item);
//        }
        itemList = JSON.parseArray(jsonStr, Item.class);
        return itemList;
    }

    /**
     * 添加任务事项
     */
    public static void addNode(Context context, Item data) {
        List<Item> list = getItemList(context);
        list.add(data);
        save(context,list);
    }

    /**
     * 删除任务事项
     */

    public static void delNode(Context context, Item data) {
        List<Item> list = getItemList(context);

        //遍历查找数组中与data对象相同的对象并删除
        for(int i=0; i<list.size(); i++) {
            if (list.get(i).getId() == data.getId()) {
                list.remove(i);
                break;
            }
        }
        save(context, list);
    }

    /**
     * 修改任务事项
     *
     */
    public static void modifyNode(Context context, Item data) {
        List<Item> list = getItemList(context);
        //修改原有的名称
        for(int i=0; i<list.size(); i++) {
            if (list.get(i).getId() == data.getId()) {
                list.get(i).setName(data.getName());
                list.get(i).setPlan_start_time(data.getPlan_start_time());
                list.get(i).setPlan_end_time(data.getPlan_end_time());
                list.get(i).setPeople_number(data.getPeople_number());
                list.get(i).setPm_write_time(data.getPm_write_time());
                break;

            }
        }
        save(context, list);
    }

    /**
     * 跟踪任务事项
     *
     */
    public static void taskNode(Context context, Item data) {
        List<Item> list = getItemList(context);
        //修改原有的信息
        for(int i=0; i<list.size(); i++) {
            if (list.get(i).getId() == data.getId()) {
                list.get(i).setTask_name(data.getTask_name());
                list.get(i).setTask_detail(data.getTask_detail());
                list.get(i).setTask_plan_start_time(data.getTask_plan_start_time());
                list.get(i).setTask_plan_end_time(data.getTask_plan_end_time());
                list.get(i).setTask_people(data.getTask_people());
                list.get(i).setTask_write_time(data.getTask_write_time());
                list.get(i).setTask_real_start_time(data.getTask_real_start_time());
                list.get(i).setTask_real_end_time(data.getTask_real_end_time());
                list.get(i).setReal_write_time(data.getReal_write_time());
                list.get(i).setComment(data.getComment());
                break;

            }
        }
        save(context, list);
    }


}
