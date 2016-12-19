package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;
import s2.entities.common.DescribedInUseEntity;
import s2.entities.common.InUseEntity;
import s2.entities.simple.CourseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 12/15/2016.
 */
@Entity
@Table(name = "STUDENT")
public class VideoItemEntity   {

    private String studentId;
    private String studentName;
    private Set<CourseEntity> courses = new HashSet<CourseEntity>(0);

    public VideoItemEntity() {
    }

    public VideoItemEntity(String studentName) {
        this.studentName = studentName;
    }

    public VideoItemEntity(String studentName, Set<CourseEntity> courses) {
        this.studentName = studentName;
        this.courses = courses;
    }




    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "STUDENT_ID", unique = true, nullable = false, length = 36)
    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "STUDENT_NAME", nullable = false, length = 100)
    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })
//    public Set<CourseEntity> getCourses() {
//        return this.courses;
//    }
//
//    public void setCourses(Set<CourseEntity> courses) {
//        this.courses = courses;
//    }

}
