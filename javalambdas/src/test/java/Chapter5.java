import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter5 {

    private static final List<Artist> ARTISTS = Arrays.asList(new Artist("Drake"), new Artist("The Weeknd"), new Artist("Rhianna"), new Artist("Jay Z"));

    @Test
    public void methodReference(){
        List<String> artistNames = ARTISTS.stream().map(Artist::getName).collect(Collectors.toList());
        assertThat(artistNames, is(equalTo(Arrays.asList("Drake", "The Weeknd", "Rhianna", "Jay Z"))));
    }

    @Test
    public void elementOrdering(){
        List<Integer> numbers = Stream.of(3,5,2,0).sorted().collect(Collectors.toList());
        assertThat(numbers, is(equalTo(Arrays.asList(0,2,3,5))));
    }

    @Test
    public void partitionData(){
        Map<Boolean, List<Artist>> drakeOrNot = ARTISTS.stream().collect(Collectors.partitioningBy(artist->"Drake".equals(artist.getName())));

        drakeOrNot.forEach((k,v)->{
            System.out.printf("%s -> %s\n", k, v.stream().map(Artist::getName).collect(Collectors.joining(", ", "[", "]")));
        });
    }
}
