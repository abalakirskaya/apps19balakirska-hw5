package ua.edu.ucu.stream;
import java.util.ArrayList;
import java.util.Iterator;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterator.*;

public class AsIntStream implements IntStream {
    private Iterator<Integer> iter;

    private AsIntStream(Iterator<Integer> iterator) {
        this.iter = iterator;
    }
    private AsIntStream(int... values){
        ArrayList<Integer> nums = new ArrayList<>();
        for(int n : values){
            nums.add(n);
        }
        this.iter = new StreamIterator(nums);
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        int[] array = this.toArray();
        if(array.length != 0){
            int sum = 0;
            for(int n : array){
                sum += n;
            }
            return (double) sum/array.length;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer max() {
        if(this.iter.hasNext()){
            Integer max = this.iter.next();
            while(this.iter.hasNext()){
                if(this.iter.next() > max){
                    max = this.iter.next();
                }
            }
            return max;
        } else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer min() {
        if(this.iter.hasNext()){
            Integer min = this.iter.next();
            while(this.iter.hasNext()){
                if(this.iter.next() < min){
                    min = this.iter.next();
                }
            }
            return min;
        } else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public long count() {
        long counter = 0;
        while (this.iter.hasNext()) {
            counter += 1;
            this.iter.next();
        }
        return counter;
    }

    @Override
    public Integer sum() {
        if(this.iter.hasNext()){
            Integer sum = 0;
            while (this.iter.hasNext()) {
                sum += this.iter.next();
            }
            return sum;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.iter, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (this.iter.hasNext()) {
            action.accept(this.iter.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.iter, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(this.iter, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        while (this.iter.hasNext()) {
            Integer num = this.iter.next();
            result = op.apply(result, num);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> arr = new ArrayList<>();
        while (this.iter.hasNext()) {
            arr.add(this.iter.next());
        }
        int[] array = new int[arr.size()];
        int i = 0;
        for (Integer element : arr) {
            array[i] = element;
            i++;
        }
        return array;
    }

}
