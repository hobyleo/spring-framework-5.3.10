package com.hoby;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hoby
 * @since 2024-01-11
 */
public class Main {

	public String str = new String("abc");
	public char[] chars = {'a', 'b', 'c'};

	public static void main(String[] args) {

		// 输出结果及原因
		Integer i1 = -128;
		Integer i2 = -128;
		Integer i3 = 128;
		Integer i4 = 128;
		System.out.println(i1 == i2);
		System.out.println(i3 == i4);

		// 下列哪些写法是对的
		// int[][] a = new int[][3];
		// int[][] b = new int[2][];
		// int[][] c = {{1,2,3}, {2,3}, {2,3,3}};
		// String[][] d = {{"123", "b"}, {"234", ""}};

		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		List<Object> asList = Arrays.asList(list.toArray());
		// 下面哪行代码不会报错
		// String[] array = (String[]) list.toArray();
		// ArrayList arrayList = (ArrayList) list.subList(0, 1);
		// asList.remove(0);

		int i = 1;
		int j = 1;
		System.out.println(i == j);
		System.out.println(i++ == j);
		System.out.println(i == j++);
		System.out.println(i == ++j);
		System.out.println(++i == j);

		Main main = new Main();
		main.change(main.str, main.chars);
		System.out.print(main.str);
		System.out.print(" and ");
		System.out.print(main.chars);

	}

	public void change(String str, char[] chars) {
		str = "test ok";
		chars[0] = 'g';
	}

}
