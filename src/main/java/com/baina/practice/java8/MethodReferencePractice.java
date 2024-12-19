package com.baina.practice.java8;

import java.util.ArrayList;
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

public class MethodReferencePractice {

	public static void main(String[] args) {
		
		List<Employee> allEmployees = EmployeeDataBase.getAllEmployees();
		List<String> employeeNames = getProcessedData( allEmployees, Employee::getName);// e->e.getName()
		System.out.println(employeeNames);
		
		List<String> id_name = getProcessedData(allEmployees, e->{
			final String str=e.getName().toUpperCase();
			return e.getId()+"_"+str;
		});
		
		System.out.println(id_name);
		
		List<String> namesList = Arrays.asList("rajesh","ramesh","sukesh","riyansh","abhinay");
		
		System.out.println(getData("naresh", name->name.length()));
		System.out.println(getData("naresh", MethodReferencePractice::accept));
		
		
	}
	
	public static  <T, R> List<R> getProcessedData(final List<T> items,final Processor<T, R> processor) {
		
		final List<R> list=new ArrayList<>();
		
		for(T item:items) {
			list.add(processor.process(item));
		}
		return list;
		
	}
	
	
	public static <T, R> R getData(T t,Processor<T, R> processor){
		return processor.process(t);
		
	}
	
	public static String accept(String str) {
		
		return str.toUpperCase();
	}
	

}
