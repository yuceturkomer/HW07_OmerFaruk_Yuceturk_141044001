package com.yuceturkomer.hw07;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by ömer on 27.04.2016.
 */
public class PriorityQueue<E extends Comparable<E>> extends AbstractQueue<E> {
    private ArrayList<E> customerArrayList;
    Comparator<E> comparator = null;

    public void sortArray(){
        if(comparator!=null){
            customerArrayList.sort(comparator);
        }
        else{
            for(int i=0;i<customerArrayList.size();++i){
                for(int j=i;j<customerArrayList.size();++j){
                    if(customerArrayList.get(i).compareTo(customerArrayList.get(j))>0){
                        swap(i,j);
                    }
                }
            }
        }
    }

    public PriorityQueue() {
        customerArrayList = new ArrayList<E>();
    }

    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Iterator<E> iterator() {
        return customerArrayList.iterator();
    }

    @Override
    public int size() {
        return customerArrayList.size();
    }
    public boolean offer(E e) {
        customerArrayList.add(e);
        sortArray();
        return true;
    }

    public E poll() {
        if (customerArrayList.size() == 0) {
            return null;
        }
        return customerArrayList.remove(customerArrayList.size() - 1);
    }

    public E peek() {
        if (customerArrayList.size() == 0) {
            return null;
        }
        return customerArrayList.get(size() - 1);
    }

    public void swap(int firstIndex, int secondIndex) {
        if (firstIndex<0||firstIndex>=customerArrayList.size() || secondIndex<0||secondIndex>=customerArrayList.size())
            throw new ArrayIndexOutOfBoundsException();

        E temp = customerArrayList.get(firstIndex);
        customerArrayList.set(firstIndex,customerArrayList.get(secondIndex));
        customerArrayList.set(secondIndex,temp);
    }


}
