import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.*;

import java.util.Arrays;
import java.util.List;
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
    int countAllMembersInAlbum(List<Album> albums) {
        return albums.stream().map(album -> album.getMembers().count()).reduce(0L, Long::sum).intValue();
    }

    // 6. count number of lowercase letters in a string
    long howMannyLowerCaseInString(String text) {
        return text.chars().filter(Character::isLowerCase).count();
    }


    ////////////////
    //    TEST   //
    //////////////

    @Test
    public void testCountAllMembersInAlbum() {
        Album a1 = new Album();
        a1.setMembers(Stream.of(new Artist(), new Artist()));
        Album a2 = new Album();
        a2.setMembers(Stream.of(new Artist()));

        int totalMembers = countAllMembersInAlbum(Arrays.asList(a1, a2));
        assertThat(totalMembers, is(equalTo(3)));
    }


    @Test
    public void testLowerCaseCount() {
        long charCount = howMannyLowerCaseInString("Hello World");
        assertThat(charCount, is(equalTo(8L)));
    }

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
