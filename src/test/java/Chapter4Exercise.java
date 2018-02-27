import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter4Exercise {

    // 1. using default
    public interface Performance{
        String getName();
        Stream<Album> getMusicians();
        default Stream<Artist> getAllMusicians() {
            return getMusicians().flatMap(album -> album.getMembers());
        }
    }

}
