import java.util.stream.Stream;

public class Album {
    private Stream<Artist> members = Stream.empty();

    public Stream<Artist> getMembers() {
        return members;
    }

    public void setMembers(Stream<Artist> members) {
        this.members = members;
    }

}