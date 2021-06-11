package com.hibernatedemo.pagination;

import java.util.*;

public class CollectionsDemo {

	public static void main(String[] args) {
		List<String> myList = new ArrayList();
		myList.add("Murali");
		myList.add("Mohan");

		for (String item : myList) {
			System.out.println(item);
		}

		Map<String, String> myMap = new HashMap();
		myMap.put("Name", "Murali");
		myMap.put("city", "Hyd");
		for (String key:myMap.keySet()) {
			System.out.println(myMap.get(key));
			
		}
	}

}
