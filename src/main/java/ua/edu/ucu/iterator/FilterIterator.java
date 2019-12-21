package ua.edu.ucu.iterator;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {

    private Iterator<Integer> prevEl;
    private IntPredicate predicate;
    private Integer nextEl;

    public FilterIterator(Iterator<Integer> previous, IntPredicate predicate) {
        this.prevEl = previous;
        this.predicate = predicate;
        this.nextEl = null;
    }

    @Override
    public boolean hasNext() {
        if (this.nextEl != null) {
            return true;
        } else {
            Integer num;
            while (this.prevEl.hasNext()) {
                num = this.prevEl.next();
                if (this.predicate.test(num)) {
                    this.nextEl = num;
                    return true;
                }

            }
            this.nextEl = null;
            return false;
        }
    }


    @Override
    public Integer next() {
        Integer num = this.nextEl;
        this.nextEl = null;
        return num;
    }


}
