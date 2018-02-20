import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Note about lambdas:
 * A lambda expression is a method without a name that is used to pass around behavior as if it were data.
 * A functional interface is an interface with a single abstrat method that is used as the type of a lambda expression.
 */
public class Chapter1 {

    private static  ThreadLocal<SimpleDateFormat> localthreadDateFormatter = ThreadLocal.withInitial(()->new SimpleDateFormat("dd-MMM-yyyy"));

    public static void main(String[] args) {
        Predicate<Integer> greaterThan5 = x -> x > 5;
        System.out.println("3 > 5 = " + greaterThan5.test(3));
        System.out.println("7 > 5 = " + greaterThan5.test(7));

        BinaryOperator<Long> addLongs = (x, y) -> x + y;
        System.out.println("5 + 4 = " + addLongs.apply(5L, 4L).toString());

        System.out.println("Date = " + localthreadDateFormatter.get().format(new Date()));
    }
}
