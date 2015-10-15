package com.example.treeview.bean;

import com.example.treeview.utils.annotation.TreeNodeId;
import com.example.treeview.utils.annotation.TreeNodeLabel;
import com.example.treeview.utils.annotation.TreeNodePid;

public class FileBean {
	@TreeNodeId
	private int _id;
	@TreeNodePid
	private int p_id;
	@TreeNodeLabel
	private String name;
	
	public FileBean (int id, int pid, String name){
		this._id = id;
		this.p_id = pid;
		this.name = name;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
