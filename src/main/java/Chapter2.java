import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter2 {

    public static void main(String[] args) {
        example1();
        example2();
    }

    public static void example1() {
        List<String> colors = Arrays.asList("Blue", "Blue", "Blue", "Yellow", "Red", "Pink", "Green");

        // OLD way (prior to java 8)
        // syntactic sugar that wraps the iterator
        int colorBlueCount = 0;
        for (String color : colors) {
            if (color.equals("Blue")) {
                colorBlueCount++;
            }
        }
        System.out.println("using for loop colorBlueCount=" + colorBlueCount);

        // external iteration
        // Using Iterator interface
        colorBlueCount = 0;
        Iterator<String> colorIterator = colors.iterator();
        while (colorIterator.hasNext()) {
            String color = colorIterator.next();
            if (color.equals("Blue")) {
                colorBlueCount++;
            }
        }
        System.out.println("using iterator colorBlueCount=" + colorBlueCount);

        // internal iteration
        // Using Stream interface (java8)
        colorBlueCount = (int) colors.stream().filter(color -> color.equals("Blue")).count();
        System.out.println("using stream colorBlueCount=" + colorBlueCount);
    }

    public static void example2() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.printf("collected=" + collected);
        List<String> words = Stream.of("Hello", "World", "Pawtucket").map(word -> word.toUpperCase()).collect(Collectors.toList());
        System.out.println(words);


        List<String> beginWithNumbers = Stream.of("a", "1abc", "abc1", "2abc", "321").filter(value -> Character.isDigit(value.charAt(0))).collect(Collectors.toList());
        System.out.println("beginWithNumbers=" + beginWithNumbers);

        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<Integer> together = Stream.of(list1, list2).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
        System.out.println("together = " + together);
    }
}
