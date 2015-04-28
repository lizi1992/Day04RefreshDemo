package com.example.administrator.day04refreshdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.util.ArrayList;
import java.util.List;









//mayang xiugai asdasdasd
public class MainActivity extends ActionBarActivity {

    public PullToRefreshListView listView;
    public ArrayAdapter<String> adapter;
    //定义属性存储页号
    private int pager;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case  0:
                    List<String> list=new ArrayList<>();

                    for (int i = 0; i < 100; i++) {
                        list.add(String.format("new%3d", i));
                    }
//                    Adapter.addAll(list);
                    MainActivity.this.list.addAll(list);
                    adapter.notifyDataSetChanged();
                    //刷新完成
                    listView.onRefreshComplete();
                    break;

                case 1:
                    List<String> list1=new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        list1.add(String.format("第%03d条数据", i+pager*100));
                    }
                    MainActivity.this.list.addAll(list1);
                    adapter.notifyDataSetChanged();
                    //刷新完成
                    listView.onRefreshComplete();
                    break;

            }
        }
    };
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = ((PullToRefreshListView) findViewById(R.id.list));
        list  = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            list.add(String.format("第%3d条数据",i));

        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        //设置拉动模式
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        //一端拉动模式下，用onRefreshListener
//        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//
//            }
//        });
        //两端拉动模式下，用OnRefreshListener2
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                adapter.clear();
                pager=0;


                handler.sendEmptyMessageDelayed(0,1000);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pager++;

                handler.sendEmptyMessageDelayed(1,1000);
                Toast.makeText(MainActivity.this, "onPullUpToRefresh", Toast.LENGTH_SHORT).show();

            }
        });
    }

}



