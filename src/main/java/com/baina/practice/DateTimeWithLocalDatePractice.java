package com.baina.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTimeWithLocalDatePractice {

	public static void main(String[] args) throws IOException {
		
		System.out.println(" enter start date in format of : dd-MM-yyyy");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		LocalDate startDate=LocalDate.parse(br.readLine(),DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		System.out.println(" enter end date in format of : dd-MM-yyyy");
		LocalDate EndDate=LocalDate.parse(br.readLine(),DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		Period period=Period.between(startDate, EndDate);
		System.out.println("your age is "+ period.getYears()+" years "+ period.getMonths() +" months "+period.getDays()+" days");
		
		long totalDays = ChronoUnit.DAYS.between(startDate, EndDate);
		ChronoUnit.DAYS.name();		
		double years=totalDays/365.0;
		
		double money=100000,rate=1.5;
		
		double intrest=(money*years*12*rate)/100;
		System.out.println(intrest);
		

		
		List<Employee> sortedByDateOfJoining = Stream.of(new Employee(1,"naresh",LocalDate.parse("01-11-2024",DateTimeFormatter.ofPattern("dd-MM-yyyy"))),
				new Employee(2,"raki",LocalDate.parse("14-11-2024",DateTimeFormatter.ofPattern("dd-MM-yyyy"))),
				new Employee(3,"patel",LocalDate.parse("01-10-2021",DateTimeFormatter.ofPattern("dd-MM-yyyy"))),
				new Employee(4,"sunesh",LocalDate.parse("01-06-2005",DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
		.sorted(Collections.reverseOrder(Comparator.comparing(Employee::getDoj))).collect(Collectors.toList());
		
		System.out.println(sortedByDateOfJoining);
		
	
	}

}
