package com.example.treeview;

import java.util.ArrayList;
import java.util.List;

import com.example.treeview.adapter.NodeAdapter;
import com.example.treeview.bean.FileBean;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity implements OnItemLongClickListener, OnItemClickListener {
	private ListView lv;
	private NodeAdapter<FileBean> mAdapter;
	private List<FileBean> mDatas = new ArrayList<FileBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) this.findViewById(R.id.lv);
		initDatas();
		try {
			mAdapter = new NodeAdapter<FileBean>(this, mDatas);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		lv.setAdapter(mAdapter);
		initEvents();
	}

	private void initEvents() {
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
	}

	private void initDatas() {
		FileBean bean = new FileBean(1, 0, "��Ŀ¼1");
		mDatas.add(bean);
		bean = new FileBean(2, 0, "��Ŀ¼2");
		mDatas.add(bean);
		bean = new FileBean(3, 0, "��Ŀ¼3");
		mDatas.add(bean);
		bean = new FileBean(4, 1, "��Ŀ¼1-1");
		mDatas.add(bean);
		bean = new FileBean(5, 1, "��Ŀ¼1-2");
		mDatas.add(bean);
		bean = new FileBean(6, 5, "��Ŀ¼1-2-1");
		mDatas.add(bean);
		bean = new FileBean(7, 3, "��Ŀ¼3-1");
		mDatas.add(bean);
		bean = new FileBean(8, 3, "��Ŀ¼3-2");
		mDatas.add(bean);		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		mAdapter.changeExpand(position);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		return false;
	}

}
