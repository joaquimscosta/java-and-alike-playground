import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

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

    private static final ThreadLocal<SimpleDateFormat> threadLocalDateFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MMM-yyyy"));
    private static final Predicate<Integer> greaterThan5 = x -> x > 5;
    private static final BinaryOperator<Long> addLongs = (x, y) -> x + y;


    @Test
    public void greaterTha5ReturnFalse() {
        boolean shouldBeFalse = greaterThan5.test(3);
        assertFalse("3 is less than 5", shouldBeFalse);
    }

    @Test
    public void greaterThan5ReturnTrue() {
        boolean shouldBeTrue = greaterThan5.test(7);
        assertTrue("7 is greater than 5", shouldBeTrue);
    }


    @Test
    public void addingTwoLongs() {
        long sum = addLongs.apply(5L, 4L);
        assertThat(sum, is(equalTo(9L)));
    }


    @Test
    public void usingThreadLocal() {
        SimpleDateFormat myFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date rightNow = new Date();
        assertThat(threadLocalDateFormatter.get().format(rightNow), is(equalTo(myFormatter.format(rightNow))));
    }
}
