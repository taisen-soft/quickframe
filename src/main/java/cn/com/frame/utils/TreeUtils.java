package cn.com.frame.utils;

import cn.com.frame.dto.TreeDto;

import java.util.List;

public class TreeUtils {

	public static List<TreeDto> getAllChildrenList (List<TreeDto> childrenList,
													List<TreeDto> treeList,
													String pId) {
		for(TreeDto tree:treeList){
			if(tree.getpId().equals(pId)){
				childrenList.add(tree);
				getAllChildrenList(childrenList,treeList,Long.toString(tree.getId()));
			}
		}
		return childrenList;
	}
}
