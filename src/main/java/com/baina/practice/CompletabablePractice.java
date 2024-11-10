package com.baina.practice;

import java.util.IntSummaryStatistics;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletabablePractice {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		CompletableFuture<IntSummaryStatistics> stats1 = CompletableFuture.supplyAsync(()->IntStream.range(1, 11).summaryStatistics());
		
		CompletableFuture<IntSummaryStatistics> stats2 = CompletableFuture.supplyAsync(()->IntStream.range(11, 21).summaryStatistics());
		
		CompletableFuture<IntSummaryStatistics> stats3 = CompletableFuture.supplyAsync(()->IntStream.range(1, 21).summaryStatistics());
	
		CompletableFuture<Long> thenCombine = stats1.thenCombine(stats2, (a,b)->a.getSum()+b.getSum());
		System.out.println(thenCombine.join());
		
		stats1.thenCompose(a->CompletableFuture.runAsync(()->{
			System.out.println(a.getSum()+stats2.join().getSum());
		}));
	
	}
		
}
