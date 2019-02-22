package com.atguigu.exer.ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache
{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key,Object value)
    {
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写："+key+"***begin");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t写完成："+key+"***end");
            System.out.println( );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }
    }

    public Object get(String key)
    {
        rwlock.readLock().lock();
        Object result = null;
        try {
            result = null;
            System.out.println(Thread.currentThread().getName()+"\t正在读："+key+"***begin");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读完成："+key+"***end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }
        return result;
    }

}

//读写锁
public class ReadWriteLockDemo {

    public static void main(String[] args){
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"",tempInt+"");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
