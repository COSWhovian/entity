package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "video_item")
public class VideoItemEntity {

    private String videoId;
    private String title;
    private Set<VideoRatingEntity> ratings = new HashSet<>(0);

    public VideoItemEntity() {
    }

    public VideoItemEntity(String title) {
        this.title = title;
    }

    public VideoItemEntity(String title, Set<VideoRatingEntity> ratings) {
        this.title = title;
        this.ratings = ratings;
    }


    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "video_id", unique = true, nullable = false, length = 36)
    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String studentId) {
        this.videoId = studentId;
    }

    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String studentName) {
        this.title = studentName;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "video_rating_map",
            joinColumns = {@JoinColumn(name = "video_id")}, inverseJoinColumns = {@JoinColumn(name = "rating_id")})
    public Set<VideoRatingEntity> getRatings() {
        return this.ratings;
    }

    public void setRatings(Set<VideoRatingEntity> courses) {
        this.ratings = courses;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(this);
        return s;

    }
}
