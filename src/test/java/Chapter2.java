import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class Chapter2 {

    @Test
    public void streamFilter() {
        List<String> colors = Arrays.asList("Blue", "Blue", "Blue", "Yellow", "Red", "Pink", "Green");

        // OLD way (prior to java 8)
        // syntactic sugar that wraps the iterator
        int countUsingForLoop = 0;
        for (String color : colors) {
            if (color.equals("Blue")) {
                countUsingForLoop++;
            }
        }
        assertThat(countUsingForLoop, equalTo(3));

        // external iteration
        // Using Iterator interface
        int countUsingIterator = 0;
        Iterator<String> colorIterator = colors.iterator();
        while (colorIterator.hasNext()) {
            String color = colorIterator.next();
            if (color.equals("Blue")) {
                countUsingIterator++;
            }
        }
        assertThat(countUsingIterator, equalTo(3));

        // internal iteration
        // Using Stream interface (java8)
        long countUsingStream = colors.stream().filter(color -> color.equals("Blue")).count();
        assertThat(countUsingStream, equalTo(3L));
    }

    @Test
    public void streamCollect() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.printf("collected=" + collected);
    }

    @Test
    public void streamMap() {
        List<String> words = Stream.of("Hello", "World", "Pawtucket").map(word -> word.toUpperCase()).collect(Collectors.toList());
        System.out.println(words);
    }

    @Test
    public void streamFilter2() {
        List<String> beginWithNumbers = Stream.of("a", "1abc", "abc1", "2abc", "321").filter(value -> Character.isDigit(value.charAt(0))).collect(Collectors.toList());
        System.out.println("beginWithNumbers=" + beginWithNumbers);

    }

    @Test
    public void streamFlatMap() {
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(3, 4, 2);
        List<Integer> together = Stream.of(list1, list2).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
        System.out.println("together = " + together);
    }

    @Test
    public void streamMinAndMax(){
        List<Integer> numbers = Arrays.asList(32,535,1,345,43,-100);

        int min = numbers.stream().min(Comparator.comparingInt(number->number)).get();
        assertThat(min, is(equalTo(-100)));

        int max = numbers.stream().max(Comparator.comparingInt(number->number)).get();
        assertThat(max, is(equalTo(535)));
    }

    @Test
    public void streamReduce(){
        List<Integer> numbers = Arrays.asList(1,5, 4);

        int total = numbers.stream().reduce(0, (acc, number)->acc+number);

        assertThat(total, is(equalTo(total)));
    }
}