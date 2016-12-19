package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;
import s2.entities.common.DescribedInUseEntity;

import javax.persistence.*;

/**
 * Created by russl on 12/14/2016.
 */
@Entity
@Table(name = "movie", schema = "enigmabase")
public class MovieEntity extends VideoItemEntity {
    /*
        would include "made-for-tv" movie
    */
    /*
        a movie can have one or more ratings
        if the rating changed over time
    */

//    String title;
//
//    @Basic
//    @Column(name = "title", nullable = false)
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
}
