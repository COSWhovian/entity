package s2.entities.simple;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="video_rating")
public class VideoRatingEntity {

    private String ratingId;
    private String ratingName;


    private String ratingDesc;
    private String ratingTip;

    public VideoRatingEntity() {
    }

    public VideoRatingEntity(String ratingName) {
        this.ratingName = ratingName;
    }


    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "rating_id", unique = true, nullable = false, length = 36)
    public String getRatingId() {
        return this.ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    @Column(name="rating_name", nullable=false)
    public String getRatingName() {
        return this.ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }




    @Column(name="rating_desc", nullable=false)
    public String getRatingDesc() {
        return this.ratingDesc;
    }

    public void setRatingDesc(String ratingDesc) {
        this.ratingDesc = ratingDesc;
    }




    @Column(name="rating_tip", nullable=false)
    public String getRatingTip() {
        return this.ratingTip;
    }

    public void setRatingTip(String ratingTip) {
        this.ratingTip = ratingTip;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VCourseEntity{");
        sb.append("ratingId='").append(ratingId).append('\'');
        sb.append(", ratingName='").append(ratingName).append('\'');
        sb.append(", ratingDesc='").append(ratingDesc).append('\'');
        sb.append(", ratingTip='").append(ratingTip).append('\'');
        sb.append('}');
        return sb.toString();
    }
}





