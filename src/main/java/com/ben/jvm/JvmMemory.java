package com.ben.jvm;

public class JvmMemory {

    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        long max = run.maxMemory();
        long total = run.totalMemory();
        long free = run.freeMemory();
        long usable = max - total + free;
        System.out.println("最大内存 = " + max);
        System.out.println("已分配内存 = " + total);
        System.out.println("已分配内存中的剩余空间 = " + free);
        System.out.println("最大可用内存 = " + usable);
    }



}
