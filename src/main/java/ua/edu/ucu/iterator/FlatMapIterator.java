package ua.edu.ucu.iterator;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.ArrayList;
import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {

    private Iterator<Integer> oldIter;
    private IntToIntStreamFunction operator;
    private StreamIterator streamIter;
    private boolean check;

    public FlatMapIterator(Iterator<Integer> previous, IntToIntStreamFunction operator) {
        this.oldIter = previous;
        this.operator = operator;
        this.check = false;
        this.streamIter = new StreamIterator();
    }

    @Override
    public boolean hasNext() {
        return this.streamIter.hasNext() || this.oldIter.hasNext() || !this.check;
    }

    @Override
    public Integer next() {
        if (this.streamIter.hasNext()) {
            return this.streamIter.next();

        } else {
            if (this.oldIter.hasNext()) {
                AsIntStream tempStream = (AsIntStream) this.operator.applyAsIntStream(this.oldIter.next());
                int[] array = tempStream.toArray();
                ArrayList<Integer> list = new ArrayList<>();
                for (int el : array) {
                    list.add(el);
                }
                this.streamIter = new StreamIterator(list);
                Integer next = this.streamIter.next();

                if (!this.oldIter.hasNext()) {
                    this.check = true;
                }
                return next;

            }

        }
        return null;
    }


}
