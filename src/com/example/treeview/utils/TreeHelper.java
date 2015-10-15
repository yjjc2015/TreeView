package com.example.treeview.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.treeview.R;
import com.example.treeview.bean.Node;
import com.example.treeview.utils.annotation.TreeNodeId;
import com.example.treeview.utils.annotation.TreeNodeLabel;
import com.example.treeview.utils.annotation.TreeNodePid;

public class TreeHelper {
	public static <T> List<Node> getSortedNodes (List<T> datas) 
			throws IllegalAccessException, IllegalArgumentException {
		List<Node> result = new ArrayList<Node>();
		List<Node> nodes = convertDatas2Nodes(datas);
		List<Node> roots = getRootNodes(nodes);
		for (Node node : roots) {
			addNode(node, result, 1, false);
		}
//		for (Node node : result) {
//			selectNodeIcon(node);
//		}
		return result;
	}
	
	public static List<Node> getVisibleNodes (List<Node> nodes) {
		List<Node> result = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node.isRoot() || node.getParent().isExpand()) {
				result.add(node);
			}
		}
		return result;
	}
	
	public static List<Node> filterVisibleNodes (List<Node> nodes) {
		List<Node> result = new ArrayList<Node>();
		List<Node> roots = getRootNodes(nodes);
		for (Node node : roots) {
			addNode(node, result, 1, true);
		}
		return result;
	}
	
	private static void selectNodeIcon(Node node) {
		if (node.isLeaf()) {
			node.setIcon(-1);
		} else if (node.isExpand()) {
			node.setIcon(R.drawable.tree_ex);
		} else {
			node.setIcon(R.drawable.tree_ec);
		}
	}
	
	/**
	 * 将节点按顺序添加到结果集合中
	 * @param node
	 * @param result
	 * @param i
	 */
	private static void addNode(Node node, List<Node> result, int currentLevel, boolean isHidden) {
		node.setLevel(currentLevel - 1);
		if (isHidden && (node.getParent()!=null && !node.getParent().isExpand())) {
			return;
		}
		if (isHidden) {
			selectNodeIcon(node);
		}
		result.add(node);
		if (node.isLeaf()) 
			return;
		for (Node n : node.getChildren())
			addNode(n, result, currentLevel + 1, true);
	}

	/**
	 * 将用户数据转换为节点
	 * @param datas
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private static <T> List<Node> convertDatas2Nodes(List<T> datas)
			throws IllegalAccessException, IllegalArgumentException {
		List<Node> nodes = new ArrayList<Node>();
		Node node = null;
		for (T t : datas) {
			Class clazz = t.getClass();
			Field[] fields = clazz.getDeclaredFields();
			int id = -1;
			int pid = -1;
			String label = null;
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getAnnotation(TreeNodeId.class) != null) {
					id = field.getInt(t);
				} else if (field.getAnnotation(TreeNodePid.class) != null) {
					pid = field.getInt(t);
				} else if (field.getAnnotation(TreeNodeLabel.class) != null) {
					label = (String) field.get(t);
				}
			}
			node = new Node(id, pid, label);
			nodes.add(node);
		}
		
		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			for (int j = i+1; j < nodes.size(); j++) {
				Node temp = nodes.get(j);
				if (node.getId() == temp.getPid()) {
					node.getChildren().add(temp);
					temp.setParent(node);
				} else if (node.getPid() == temp.getId()) {
					node.setParent(temp);
					temp.getChildren().add(node);
				}
			}
		}
		
		return nodes;
	}
	
	/**
	 * 得到根节点集合
	 * @param nodes
	 * @return
	 */
	private static List<Node> getRootNodes(List<Node> nodes) {
		List<Node> result = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node.isRoot()) {
				result.add(node);
			}
		}
		return result;
	}
}
