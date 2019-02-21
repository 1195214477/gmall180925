package com.atguigu.exer.ticket;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//条件全部完成方可执行任务
public class CyclicBarrierDemo {

    public static void main(String[] args)
    {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {System.out.println("********召唤神龙");});
        for (int i = 1; i <= 7; i++) 
        {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t收集第："+tempInt+"龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

}
