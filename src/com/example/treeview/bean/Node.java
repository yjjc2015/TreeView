package com.example.treeview.bean;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	private int pid;
	private int level;
	private String label;
	private int icon;
	private boolean isExpand;
	private Node parent;
	private List<Node> children = new ArrayList<Node>();
	
	public Node(){}
	
	public Node(int id, int pid, String label){
		this.id = id;
		this.pid = pid;
		this.label = label;
	}
	
	public boolean isRoot(){
		return parent == null;
	}
	
	public boolean isLeaf(){
		return children.size() == 0;
	}
	
	public int getLevel() {
//		return parent == null ? 0 : parent.getLevel() + 1;
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public boolean isExpand() {
		return isExpand;
	}
	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
//		if (!isExpand) {
//			for (Node n : children) {
//				n.setExpand(false);
//			}
//		}
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
}
