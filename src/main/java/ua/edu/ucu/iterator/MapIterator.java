package ua.edu.ucu.iterator;
import java.util.Iterator;
import ua.edu.ucu.function.IntUnaryOperator;
public class MapIterator implements Iterator<Integer> {
    private IntUnaryOperator operator;
    private Iterator<Integer> el;

    public MapIterator(Iterator<Integer> element, IntUnaryOperator operator1){
        this.el = element;
        this.operator = operator1;
    }
    @Override
    public boolean hasNext() {
        return el.hasNext();
    }

    @Override
    public Integer next() {
        return operator.apply(el.next());
    }
}
