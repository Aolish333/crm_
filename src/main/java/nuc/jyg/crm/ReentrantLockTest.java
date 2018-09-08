package nuc.jyg.crm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ji YongGuang.
 * @date 21:26 2018/9/7.
 */
public class ReentrantLockTest {

    private boolean flag = true;
    private int count = 0;

    private static ReentrantLock lock = new ReentrantLock();

    private static final Condition consumeLock = lock.newCondition();
    private static final Condition producerLock = lock.newCondition();

    public void product(String name) {
        lock.lock();
        try {
            while (flag) {
                producerLock.await();
            }
            String modifyFlageThreadName = Thread.currentThread().getName();
            System.out.println(modifyFlageThreadName + ": producer" + ++count);
            flag = true;

            consumeLock.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (!flag) {
                consumeLock.await();
            }
            String modifyThreadName = Thread.currentThread().getName();
            System.out.println(modifyThreadName + ": consumer" + count--);
            flag = false;

            producerLock.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Mutil_Producer implements Runnable {

        ReentrantLockTest reentrantLockTest;

        public Mutil_Producer(ReentrantLockTest reentrantLockTest) {
            this.reentrantLockTest = reentrantLockTest;
        }

        @Override
        public void run() {
            reentrantLockTest.product("烤鸡");
        }
    }

    static class Mutil_Consumer implements Runnable {

        ReentrantLockTest reentrantLockTest;

        public Mutil_Consumer(ReentrantLockTest reentrantLockTest) {
            this.reentrantLockTest = reentrantLockTest;
        }

        @Override
        public void run() {
            reentrantLockTest.consume();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();


    }
}
