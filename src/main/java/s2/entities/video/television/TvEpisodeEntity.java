package s2.entities.video.television;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by russl on 12/20/2016.
 */
public class TvEpisodeEntity {
    /*
     a tv season id is unique for a given tv show ...
      */
    /*
    season 1, episode 1 of buffy
     */
    @OneToMany
    private TvSeasonEntity tvSeason;

    @OneToOne
    private BroadcastEntity broadcast;

    private String id;

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

}
