package com.baina.practice.java8;

@FunctionalInterface
public interface Processor<T,R> {

     R process(T t);
}
