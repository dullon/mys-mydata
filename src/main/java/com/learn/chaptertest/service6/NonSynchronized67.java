package com.learn.chaptertest.service6;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  67 : 避免过度同步
 *
 *  依据情况的不同，过度同步可能会导致性能降低、死锁，甚至不确定的行为。
 * 　　为了避免活性失败和安全性失败，在一个被同步的方法或者代码块中，永远不要放弃对客户端的控制。换句话说，在一个被同步的区域内部，不要调用设计成要被覆盖的方法，或者是由客户端以函数对象的形式提供的方法。从包含该同步区域的角度来看，这样的方法是外来的（alien）。这个类不知道该方法会做什么事情，也无法控制它。根据外来方法的作用，从同步区域中调用它会导致异常、死锁或者数据损坏。通常，你应该在同步区域内做尽可能少的工作。
 * 　　如果一个可变类要并发使用，应该使这个类变成线程安全的，通过内部同步，你还可以获得明显比外部锁定整个对象更高的并发性。否则，就不要在内部同步。让客户在必要的时候从外部同步。(StringBuffer实例几乎总是被用于单个线程中，而它们执行的却是内部同步。因此，这种StringBuffer都应该用StringBuilder代替。)
 * 　　简而言之，为了避免死锁和数据破坏，千万不要从同步区域内部调用外来方法。更为一般地讲，要尽量限制同步区域内部的工作量。当你在设计一个可变类的时候，要考虑一下它们是否应该自己完成同步操作。在现在这个多核的时代，这比永远不要过度同步来得更重要。只有当你有足够的理由一定要在内部同步类的时候，才应该这么做，同时还应该将这个决定清楚地写到文档中。
 */
public class NonSynchronized67 {
    public static void main(String [] args){
        //创建一个被观察的对象
        ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<Integer>());

        //添加一个观察者
        set.addObserver(new SetObserver<Integer>()
        {
            public void added(ObservableSet<Integer> s, Integer e)
            {
                System.out.println(e);
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
        System.out.println("--------------------------------");
        set.addObserver(new SetObserver<Integer>()
        {
            public void added(ObservableSet<Integer> s, Integer e)
            {
                System.out.println(e);
                if (e == 23) //到23，我们取消这个观察者，但是会爆出异常，因为在迭代遍历列表的时候我们自己修改了列表，这是非法的
                    s.removeObserver(this);
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
        System.out.println("--------------------------------");
        /**
         * 那么如何取消观察者者呢？？？？
         * 我们使用另外的一个线程在23的时候删除这个观察者
         */
        set.addObserver(new SetObserver<Integer>()
        {
            @Override
            public void added(final ObservableSet<Integer> set, Integer element)
            {
                System.out.println(element);
                //如果是23
                if(element == 23)
                {
                    //线程池，创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try
                    {
                        executor.submit(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //这里会死锁
                                set.removeObserver(observer);
                            }
                        }).get();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        executor.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++)
            set.add(i);
        System.out.println("--------------------------------");
    }
}

//辅助基础类
class ForwardingSet<E> implements Set<E> {

    /**
     * 这个类作为转发类，内部通过复合的方式把set作为一个组件
     */
    private final Set<E> s;

    public ForwardingSet(Set<E> s){
        this.s = s;
    }

    @Override
    public int size(){
        return s.size();
    }

    @Override
    public boolean isEmpty(){
        return s.isEmpty();
    }

    @Override
    public boolean contains(Object o){
        return s.contains(o);
    }

    @Override
    public Iterator<E> iterator(){
        return s.iterator();
    }

    @Override
    public Object[] toArray(){
        return s.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a){
        return s.toArray(a);
    }

    @Override
    public boolean add(E e){
        return s.add(e);
    }

    @Override
    public boolean remove(Object o){
        return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c){
        return s.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c){
        return s.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c){
        return s.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c){
        return s.removeAll(c);
    }

    @Override
    public void clear(){
        s.clear();
    }

}

//此类可以被观察者注册 依赖于基础类。基础类是观察者抽象类，此类是观察者具体类
class ObservableSet<E> extends ForwardingSet<E> {

    public ObservableSet(Set<E> s){
        super(s);
    }

    private final List<SetObserver<E>> observers = new ArrayList<SetObserver<E>>();//这里用CopyOnWriteArrayList 下面的方法就可以不用同步块来写了。

    public void addObserver(SetObserver<E> observer){
        synchronized (observers){
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer){
        synchronized (observers){
            return observers.remove(observer);
        }
    }

    // This method is the culprit
    private void notifyElementAdded(E element){

        //造成锁死测试 为了避免这种现象我们创建一个快照如下一个方法
        synchronized (observers){
            for (SetObserver<E> observer : observers)
                observer.added(this, element);
        }
    }

    private void notifyElementAdded2(E element){
        List<SetObserver<E>> snapshot = null;
        synchronized (observers){
            //这里拍一个快照,这样我们遍历的时候就不用对原来的集合进行上锁了
            snapshot = new ArrayList<SetObserver<E>>(observers);
        }
        for (SetObserver<E> observer : snapshot)
            observer.added(this, element);
    }

    @Override
    public boolean add(E e){
        //调用父类函数添加到集合中
        boolean added = super.add(e);
        if(added){
            //添加成功,观察者保存注册对象
            notifyElementAdded(e);
        }

        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c){
        boolean result = false;
        for(E element : c){
            //做或运算，只要有一个add添加成功，那么result就是true
            result |= add(element);
        }
        return result;
    }
}
//抽象的观察者。 依赖倒转原则， 细节依赖抽象
interface SetObserver<E> {
    /**
     * 当一个元素添加到ObservableSet对象中的时候，调用
     * @param set
     * @param element
     */
    void added(ObservableSet<E> set, E element);
}


