package com.baina.practice;

import java.util.stream.IntStream;

public class UtilityClass {
	
	public static void test() {
		IntStream.range(1, 11).forEach(num->System.out.println(num));
	}

}
