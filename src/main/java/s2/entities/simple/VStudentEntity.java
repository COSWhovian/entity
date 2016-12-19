package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENT")
public class VStudentEntity {

    private String studentId;
    private String studentName;
    private Set<VCourseEntity> courses = new HashSet< >(0);

    public VStudentEntity() {
    }

    public VStudentEntity(String studentName) {
        this.studentName = studentName;
    }

    public VStudentEntity(String studentName, Set<VCourseEntity> courses) {
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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })
    public Set<VCourseEntity> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<VCourseEntity> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VStudentEntity{");
        sb.append("studentId='").append(studentId).append('\'');
        sb.append(", studentName='").append(studentName).append('\'');
        sb.append(", courses=").append(courses);
        sb.append('}');
        return sb.toString();
    }
}
