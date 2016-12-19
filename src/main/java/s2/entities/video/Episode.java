package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by russl on 12/14/2016.
 */
public class Episode {
    //


    // an episode can be filmed in multiple locations
    // an episode can "take place" in multiple locations


    String id;
    int sequence;
    String title;
    FilmingLocationEntity location;





    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    /*
    https://en.wikipedia.org/wiki/Television_program#See_also
https://en.wikipedia.org/wiki/Category:Lists_of_television_series_episodes





     */
// original broadcast audience
    // local, national, international

    // episode type
    //  - special
    // comedy, drama, sci-fi, documentary, news, reality
    // instructional, educational
    // game show
    // historical
    // mockumentary
    // tragedy
    // black comedy
    // melodrama
    //
    //https://en.wikipedia.org/wiki/List_of_television_formats_and_genres
    //



    // an episode can have multiple episode types ... ie, historical comedy, or romantic sci-fi
    String episodeType;
}
