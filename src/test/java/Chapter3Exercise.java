import com.sun.jdi.connect.Connector;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter3Exercise {

    // 1.a
    int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (acc, number) -> acc + number);
    }


    // 1.b
    List<String> firstLetterUppercase(Stream<String> names) {
        return names.map(name ->
                name.replaceFirst("[a-z]", String.valueOf(name.charAt(0)).toUpperCase()))
                .collect(Collectors.toList());
    }


    // 1.c
    List<Person> age28AndOver(Stream<Person> people) {
        return people.filter(person -> (person.getAge() >= 28)).collect(Collectors.toList());
    }

    // 2. convert code that count members external to internal iteration
    @Test
    public void exercise2(){
        List<Artist> artists = Arrays.asList();

        int totalMembers = artists.stream().map(artist->artist.getMemebers().count()).reduce(0L, Long::sum).intValue();

//        return artists.stream()
//                .map(artist -> artist.getMembers().count())
//                .reduce(0L, Long::sum)
//                .intValue();
//
    }

    class Artist{
        private Stream<Artist> memebers = Stream.empty();
        public Stream<Artist> getMemebers() {
            return memebers;
        }
    }

    // 6. count number of lowercase letters in a string
    @Test
    public void exercise6(){
        String text = "Hello World!";
        long charCount = text.chars().filter(c->Character.isLowerCase(c)).count();
        System.out.println(text + ".... charCount = " + charCount);
    }



    ////////////////
    //    TEST   //
    //////////////


    @Test
    public void testAddUp() {
        int sum = addUp(Arrays.asList(1, 2, 3, 6).stream());
        assertThat(sum, is(equalTo(12)));
    }

    @Test
    public void testFirstLetterUppercase() {
        List<String> beatifiedNames = firstLetterUppercase(Stream.of("joaquim", "nancy", "camila", "thallia"));

        assertThat(beatifiedNames, is(equalTo(Arrays.asList("Joaquim", "Nancy", "Camila", "Thallia"))));
    }

    @Test
    public void testAge28AndOver() {
        Stream<Person> people = Stream.of(new Person("Nancy", 28), new Person("Camila", 25), new Person("Thallia", 20), new Person("Joaquim", 29));

        List<Person> peopleOver28 = age28AndOver(people);
        assertThat(peopleOver28, containsInAnyOrder(Matchers.hasProperty("name", is("Nancy")),
                Matchers.hasProperty("name", is("Joaquim"))));
    }
}
