package ua.edu.ucu.iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {
    private ArrayList<Integer> list;
    private int curr = 0;

    public StreamIterator(ArrayList<Integer> lst){
        this.list = lst;
    }

    public StreamIterator(){
        this.list = new ArrayList<>();
    }

    @Override
    public boolean hasNext() {
        return (this.curr < list.size());
    }

    @Override
    public Integer next() {
        Integer next = this.list.get(this.curr);
        this.curr++;
        return next;
    }
}
