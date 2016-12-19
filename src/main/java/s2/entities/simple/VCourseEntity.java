package s2.entities.simple;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="COURSE")
public class VCourseEntity {

    private String ratingId;
    private String ratingName;


    private String courseDesc;
    private String courseTip;

    public VCourseEntity() {
    }

    public VCourseEntity(String ratingName) {
        this.ratingName = ratingName;
    }


    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "COURSE_ID", unique = true, nullable = false, length = 36)
    public String getRatingId() {
        return this.ratingId;
    }

    public void setRatingId(String courseId) {
        this.ratingId = courseId;
    }

    @Column(name="COURSE_NAME", nullable=false)
    public String getRatingName() {
        return this.ratingName;
    }

    public void setRatingName(String courseName) {
        this.ratingName = courseName;
    }




    @Column(name="course_desc", nullable=false)
    public String getCourseDesc() {
        return this.courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }




    @Column(name="course_tip", nullable=false)
    public String getCourseTip() {
        return this.courseTip;
    }

    public void setCourseTip(String courseTip) {
        this.courseTip = courseTip;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VCourseEntity{");
        sb.append("ratingId='").append(ratingId).append('\'');
        sb.append(", ratingName='").append(ratingName).append('\'');
        sb.append(", courseDesc='").append(courseDesc).append('\'');
        sb.append(", courseTip='").append(courseTip).append('\'');
        sb.append('}');
        return sb.toString();
    }
}





