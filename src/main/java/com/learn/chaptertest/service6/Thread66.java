package com.learn.chaptertest.service6;


import java.util.concurrent.TimeUnit;

/**
 * 66 : 同步访问共享的可变数据
 *
 * 1、当一个对象被一个线程修改的时候，可以阻止另一个线程观察到对象内部不一致的状态；
 * 2、同步不仅可以组织一个线程看到对象处于不一致的状态，还可以保证进入同步方法或者同步代码块的每个线程，都看到由同一个锁保护的之前所有的修改效果。
 *
 * 为了提高性能，在读写原子数据的时候，应该避免使用同步。这个建议是非常危险而且错误的。因为，虽然读写原子数据都是原子操作，但是不保证一个线程的写入的值对于另一个线程是完全可见的（值得一提的是，究竟什么样的原子变量必须进行同步，是需要看情况的）。因此，为了在线程之间进行可靠的通信，也为了互斥访问，同步是必要的。
 *
 *
 * 总之，解决这一问题的最好办法其实是尽量避免在线程间共享可变数据，将可变数据限制在单线程中。让线程短时间修改一个对象，并与其他线程共享，这是可以接受的，只需要同步修改的动作即可。不需要同步，其他线程也可以读取该对象，只要它以后没有再改变。总的来说，如果想要多个线程共享可变数据，那么读写都需要进行同步。
 */
public class Thread66 {
    private static boolean stopRequest;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0 ;
               /* while (!stopRequest){
                    i++;
                    //System.out.println(i); 灵异现在不懂，如果注掉 循环不会关闭 线程一直运行。 如果打开注释则 会关闭。考虑system源码是否有同步功能 源码中确实有同步功能 使得线程可以正常关闭。源码中得到验证system确实有同步锁。
                }*/
                while (!stopRequest){
                    i++;
                }
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequest = true;
        System.out.println("Thread over.");
    }

    //所以需要把stopRequest同步化。
    private static synchronized void requestStop() {
        stopRequest = true;
    }

    private static synchronized boolean stopRequest() {
        return stopRequest;

    }

    //由于++运算符不是原子的，因此在多线程的时候会出错。++运算符执行两项操作：1、读取值；2、写回新值（相当于原值+1）。
    private static volatile int nextSerialNumber = 0;

    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }
}
    /**
     * Prints a boolean and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(boolean)}</code> and then
     * <code>{@link #println()}</code>.
     *
     * @param x  The <code>boolean</code> to be printed

    public void println(boolean x) {
        synchronized (this) {
            print(x);
            newLine();
        }
    } */