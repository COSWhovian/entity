package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 12/14/2016.
 */
@Entity
@Table(name = "video_group")
public class VideoGroupEntity {
    // grouping of one or more video items
    String id;
String title;
String group_desc;
    Set<VideoItemEntity> videoItemEntities = new HashSet<>();

    @Column(name = "title", nullable = false, length = 256)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "group_desc", nullable = false, length = 256)
    public String getGroup_desc() {
        return group_desc;
    }

    public void setGroup_desc(String group_desc) {
        this.group_desc = group_desc;
    }



    //
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "group_id", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "video_group_map",
            joinColumns = {@JoinColumn(name = "group_id", nullable = false,
                    updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "video_id", nullable = false,
                    updatable = false)})
    public Set<VideoItemEntity> getVideoItemEntities() {
        return this.videoItemEntities;
    }

    public void setVideoItemEntities(Set<VideoItemEntity> videoItemEntities) {
        this.videoItemEntities = videoItemEntities;
    }
}
