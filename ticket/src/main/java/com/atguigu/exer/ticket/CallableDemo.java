package com.atguigu.exer.ticket;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>
{

    @Override
    public Integer call() throws Exception {
        return 123;
    }
}

/*
*   多线程中，
* */
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException , ExecutionException{

        FutureTask<Integer> futureTask = new FutureTask(() -> {
            System.out.println("********come in call() lambdy express");
            return 200;
        });

        Thread t1 = new Thread(futureTask,"A");
        t1.start();

        System.out.println("*************retValue："+futureTask.get());
    }
}
