import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter4Exercise {



    public interface Performance{
        String getName();
        Stream<Artist> getMusicians();
//        default Stream<String> getAllMusicians() {
//            return getMusicians().map(artist -> artist.getName()).collect(Collectors.toList());
//        }
    }

}
