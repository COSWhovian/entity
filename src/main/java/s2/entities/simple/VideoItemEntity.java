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

    private String studentId;
    private String studentName;
    private Set<VideoRatingEntity> courses = new HashSet<>(0);

    public VideoItemEntity() {
    }

    public VideoItemEntity(String studentName) {
        this.studentName = studentName;
    }

    public VideoItemEntity(String studentName, Set<VideoRatingEntity> courses) {
        this.studentName = studentName;
        this.courses = courses;
    }


    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "item_id", unique = true, nullable = false, length = 36)
    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "title", nullable = false, length = 100)
    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = {@JoinColumn(name = "item_id")}, inverseJoinColumns = {@JoinColumn(name = "rating_id")})
    public Set<VideoRatingEntity> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<VideoRatingEntity> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(this);
        return s;

    }
}
