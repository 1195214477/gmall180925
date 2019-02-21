package com.atguigu.exer.ticket;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchDemo {

    public static void main(String[] args)throws Exception
    {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 一统华夏");


    }

    //条件到达0时方可进行下一步骤
    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 同学离开教室");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 班长关门");
    }

}
