package com.yuceturkomer.hw07;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * The generic PriorityQueue Class. The instance E must have been implemented the interface "Comparable"
 * and comparator is optional.
 */
public class PriorityQueue<E extends Comparable<E>> extends AbstractQueue<E> {
    private ArrayList<E> customerArrayList;
    Comparator<E> comparator = null;

    /**
     * If there is a comparator, uses it to sort.
     * else sorts the customerArrayList using insertion sort.
     */
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

    /**
     * The no parameter constructor
     */
    public PriorityQueue() {
        customerArrayList = new ArrayList<E>();
    }

    /**
     * The constructor if comparator wants to be given
     * @param comparator comparator object
     */
    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Overriden iterator method. Delegates ArrayList's iterator.
     * @return customerArrayList's iterator
     */
    @Override
    public Iterator<E> iterator() {
        return customerArrayList.iterator();
    }

    /**
     * Delegates ArrayList's size method
     * @return customerArrayList's size
     */
    @Override
    public int size() {
        return customerArrayList.size();
    }

    /**
     * Adds the value at the end of the array and then sorts it. It provides us to use the array as priority queue
     * @param e item to be added
     * @return true
     */
    public boolean offer(E e) {
        customerArrayList.add(e);
        sortArray();
        return true;
    }

    /**
     * Removes the last element(which has the most priority)
     * @return removed element's reference
     */
    public E poll() {
        if (customerArrayList.size() == 0) {
            return null;
        }
        return customerArrayList.remove(customerArrayList.size() - 1);
    }

    /**
     * Returns the last element's reference without removing it (which has the most priority)
     * @return Last element's reference
     */
    public E peek() {
        if (customerArrayList.size() == 0) {
            return null;
        }
        return customerArrayList.get(size() - 1);
    }

    /**
     * Swaps the values of elements according to given indices
     * @param firstIndex index of first element to be swapped
     * @param secondIndex index of second element to be swapped
     */
    public void swap(int firstIndex, int secondIndex) {
        if (firstIndex<0||firstIndex>=customerArrayList.size() || secondIndex<0||secondIndex>=customerArrayList.size())
            throw new ArrayIndexOutOfBoundsException();

        E temp = customerArrayList.get(firstIndex);
        customerArrayList.set(firstIndex,customerArrayList.get(secondIndex));
        customerArrayList.set(secondIndex,temp);
    }


}
