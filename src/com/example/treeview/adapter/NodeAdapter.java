package com.example.treeview.adapter;

import java.util.List;

import com.example.treeview.R;
import com.example.treeview.R.id;
import com.example.treeview.R.layout;
import com.example.treeview.bean.Node;
import com.example.treeview.utils.TreeHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NodeAdapter<T> extends BaseAdapter {
	private Context mContext; 
	private LayoutInflater mInflater;
	private List<Node> mVisibleNodes;
	private List<Node> mNodes;
	
	public NodeAdapter (Context context, List<T> datas)
			throws IllegalAccessException, IllegalArgumentException {
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		mNodes = TreeHelper.getSortedNodes(datas);
//		mVisibleNodes = TreeHelper.getVisibleNodes(mNodes);
		mVisibleNodes = TreeHelper.filterVisibleNodes(mNodes);
	}
	
	public void changeExpand (int position) {
		Node node = mVisibleNodes.get(position);
		if (node.isLeaf()) 
			return;
		node.setExpand(!node.isExpand());
		refresh();
	}
	
	public void refresh() {
//		mVisibleNodes = TreeHelper.getVisibleNodes(mNodes);
		mVisibleNodes = TreeHelper.filterVisibleNodes(mNodes);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mVisibleNodes.size();
	}

	@Override
	public Node getItem(int position) {
		return mVisibleNodes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mVisibleNodes.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_layout, parent, false);
			holder = new ViewHolder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
			holder.tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Node node = mVisibleNodes.get(position);
		convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
		holder.tvLabel.setText(node.getLabel());
		if (node.getIcon() != -1) {
			holder.ivIcon.setVisibility(View.VISIBLE);
			holder.ivIcon.setImageResource(node.getIcon());
		} else {
			holder.ivIcon.setVisibility(View.INVISIBLE);			
		}
		return convertView;
	}
	
	static class ViewHolder {
		public ImageView ivIcon;
		public TextView tvLabel;
	}

}
