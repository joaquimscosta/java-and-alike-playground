import java.util.List;
import java.util.Optional;

// Artists domain class using Optional
public class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        Optional<Artist> artist = Optional.empty();
        if(index>=0 && index<artists.size()){
            artist = Optional.of(artists.get(index));
        }
        return artist;
    }

    public String getArtistName(int index){
        return getArtist(index).map(Artist::getName).orElse("unknown");
    }
}
