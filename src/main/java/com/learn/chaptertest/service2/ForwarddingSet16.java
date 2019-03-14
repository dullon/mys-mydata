package com.learn.chaptertest.service2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 这个类是转发类 ，也就是装饰器。任何包装类继承他都可以对 他的私有域对象（真实对象）进行包装。他调用的方法时真实对象的方法。
 * 可以在真实方法调用前后进行增强功能，也可在包装类中进行此操作。
 * @param <E>
 */
public class ForwarddingSet16<E> implements Set<E> {

    private final Set<E> set ;

    public ForwarddingSet16(Set<E> set) {
        this.set = set;
    }

    
    public int size() {
        return set.size();
    }

    
    public boolean isEmpty() {
        return set.isEmpty();
    }

    
    public boolean contains(Object o) {
        return set.contains(o);
    }

    
    public Iterator<E> iterator() {
        return set.iterator();
    }

    
    public Object[] toArray() {
        return set.toArray();
    }

    
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    
    public boolean add(E e) {
        return set.add(e);
    }

    
    public boolean remove(Object o) {
        return set.remove(o);
    }

    
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    
    public void clear() {
        set.clear();
    }

    @Override
    public boolean equals(Object o) {
       return set.equals(o);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public String toString() {
        return set.toString();
    }
}
