package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;
import s2.entities.person.PersonGroupEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//import s2.entities.person.PersonGroupEntity;

/**
 * Created by russl on 11/18/2016.
 */


@Entity
@Table(name = "video_rating", schema = "enigmabase")
public class VideoNameGroupEntity {
    private String id;
  private String rating;


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
    @Basic
    @Column(name = "rating", nullable = false, length = 36)
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "VideoNameGroupEntity{" +
                "id='" + (id == null ? "" : id) + '\'' +
                ", rating='" + rating + '\'' +

                '}';
    }
}
