package io.github.cloudadc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.cloudadc.iControl.wapper.Category;
import io.github.cloudadc.iControl.wapper.iType;
import io.github.cloudadc.iControl.wapper.iWrapper;

public class Helper {

	static void printHelperAdoc(String link) {

		Map<Category, List<String>> map = new HashMap<>();
		
		Arrays.asList(iWrapper.class.getDeclaredMethods()).forEach( m -> {
			add(map, m.getAnnotation(iType.class).category(), m.getName());
		});
		
		add(map, Category.DNS, "TODO");
		
		add(map, Category.BIQ, "TODO");
		
		map.keySet().forEach(c -> {
			System.out.println("* " + c);
			map.get(c).forEach(n -> {
				System.out.println("** link:" + link + "[" + n + "]");
			});
			System.out.println();
		});
	}

	private static void add(Map<Category, List<String>> map, Category key, String name) {
		if(map.get(key) == null) {
			map.put(key, new ArrayList<String>());
		}
		map.get(key).add(name);
	}
}
