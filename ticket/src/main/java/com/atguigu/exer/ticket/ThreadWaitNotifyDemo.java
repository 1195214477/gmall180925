package com.atguigu.exer.ticket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment()throws Exception
    {
        lock.lock();
        try
        {
            while (number != 0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }
    public void decrement()throws Exception
    {
        lock.lock();
        try
        {
            while (number == 0){
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

/*    public synchronized void increment()throws Exception
    {
        //判断
        while (number != 0){
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }
    public synchronized void decrement()throws Exception
    {
        //判断
        while (number == 0){
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }*/


}

public class ThreadWaitNotifyDemo {

    public static void main(String[] args)
    {
        ShareResource sr = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    sr.increment();
                    try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    sr.decrement();
                    try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    sr.increment();
                    try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    sr.decrement();
                    try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();


    }
}
