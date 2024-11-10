package com.baina.practice.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8PracticeMain {

	public static void main(String[] args) {
		
		DoubleSummaryStatistics collect = EmployeeDataBase.getAllEmployees().stream().map(Employee::getSalary).collect(Collectors.summarizingDouble(Double::doubleValue));
		System.out.println("min......"+collect.getMin());
		System.out.println("max......"+collect.getMax());
		System.out.println("count......"+collect.getCount());
		System.out.println("avg......"+collect.getAverage());
		System.out.println("sum......"+collect.getSum());
		
		
		Map<String, Double> map = EmployeeDataBase.getAllEmployees().stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary));
		System.out.println(map);
		
		
		Map<Double, List<String>> collect2 = EmployeeDataBase.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getSalary,Collectors.mapping(Employee::getName,Collectors.toList())));
		System.out.println(collect2);
		
		Map<Double, List<Employee>> collect5 = EmployeeDataBase.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getSalary));
		
		Entry<Double, List<String>> maxSalary = collect2.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).collect(Collectors.toList()).get(0);
		System.out.println("maxsalary......."+maxSalary);
		
		
		String str="hi i am naresh baina i am good wt about you ?";
		Map<String, Long> collect3 = Arrays.asList(str.split(" ")).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		Map<String, Long> collect4 = Stream.of(str.split(" ")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(collect3);
		System.out.println(collect4);
		
		Optional<Double> min = EmployeeDataBase.getAllEmployees().stream().map(Employee::getSalary).min(Comparator.comparing(Double::doubleValue));
		System.out.println("min...."+min.get());
		
		
		Long collect6 = EmployeeDataBase.getAllEmployees().stream().map(Employee::getSalary).collect(Collectors.filtering(sal->sal>50000, Collectors.counting()));
		System.out.println("above 50000 salary employeee count : "+collect6);
		
		
		
		
		
	}
	

}
