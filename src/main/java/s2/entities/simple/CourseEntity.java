package s2.entities.simple;





import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COURSE")
public class CourseEntity {

    private String courseId;
    private String courseName;

    public CourseEntity() {
    }

    public CourseEntity(String courseName) {
        this.courseName = courseName;
    }


    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "COURSE_ID", unique = true, nullable = false, length = 36)
    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Column(name="COURSE_NAME", nullable=false)
    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseEntity{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}





