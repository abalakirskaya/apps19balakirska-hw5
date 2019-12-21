package ua.edu.ucu;
import ua.edu.ucu.stream.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;


public class AsIntStreamTest {
    private IntStream intStr;
    private IntStream emptyIntStr;

    @Before
    public void init(){
        int[] array = {-3, -2, -1, 0, 1, 2, 3};
        int[]array2 = {};
        this.intStr = AsIntStream.of(array);
        emptyIntStr = AsIntStream.of(array2);
    }

    @Test
    public void testAverage(){
        double expected = 0;
        double result = this.intStr.average();
        assertEquals(expected, result, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAverage(){
        double result = this.emptyIntStr.average();
    }

    @Test
    public void testMax(){
        int expected = 3;
        int result = this.intStr.max();
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMax() {
        double result = this.emptyIntStr.max();
    }

    @Test
    public void testMin() {
        int expected = -3;
        int result = this.intStr.min();
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMin() {
        int result = this.emptyIntStr.min();
    }

    @Test
    public void testCount() {
        long expected = 7;
        long result = this.intStr.count();
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyCount() {
        long expected = 0;
        long result = this.emptyIntStr.count();
        assertEquals(expected, result);
    }

    @Test
    public void testSum() {
        int expected = 0;
        int result = this.intStr.sum();
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySum() {
        int result = this.emptyIntStr.sum();
    }

    @Test
    public void testFilter() {
        int[] expected = new int[]{-3, -2, -1, 0};
        int[] result = this.intStr
                .filter(x -> x <= 0).toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMap() {
        int[] expected = new int[]{9, 4, 1, 0, 1, 4, 9};
        int[] result = this.intStr
                .map(x -> x * x).toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFlatMap() {
        int[] expected = new int[]{-4, -3, -2, -3, -2, -1,-2, -1, 0, -1, 0, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4};
        int[] result = this.intStr
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testReduce() {
        int expected = 0;
        int result = this.intStr
                .reduce(0, (sum, x) -> sum += x);
        assertEquals(expected, result);
    }

    @Test
    public void testForEach() {
        String expected = "-3-2-10123";
        String result = StreamApp.streamForEach(this.intStr);
        assertEquals(expected, result);
    }
}
