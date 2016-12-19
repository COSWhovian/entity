package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by russl on 12/14/2016.
 */
public class VideoGroupEntity {
    // grouping of one or more episodes
    String id;

/*
television:
   season
   series

movie:
prequel
sequel
https://en.wikipedia.org/wiki/Sequel
reboot
remake
companion piece



cliffhanger
crossover
film series
(based on) video game
spin-off
tetralogy
trilogy


 */




    //
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

    Set<SeriesEntity>             seriesItemEntities;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "series_item_map",
            joinColumns = {@JoinColumn(name = "person_group_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "name_group_id", referencedColumnName = "id", nullable = false, updatable = false)})
    public Set<SeriesEntity> getSeriesItemEntities() {
        return this.seriesItemEntities;
    }




}
