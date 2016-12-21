package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;
import s2.entities.simple.WombatEntity;

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
    String groupDesc;
    Set<VideoItemEntity> videoItemEntities = new HashSet<>();

    @Column(name = "title", nullable = false, length = 256)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "group_desc", nullable = false, length = 256)
    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String group_desc) {
        this.groupDesc = group_desc;
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

    private  Set<WombatEntity> wombats;

    @OneToMany(mappedBy = "groupEntity", fetch = FetchType.EAGER)
    public Set<WombatEntity> getWombats() {
        return wombats;
    }

    public void setWombats(Set<WombatEntity> wombats) {
        this.wombats = wombats;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VideoGroupEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", group_desc='").append(groupDesc).append('\'');
//        sb.append(", videoItemEntities=").append(videoItemEntities);
//        sb.append(", wombats=").append(wombats);
        sb.append('}');
        return sb.toString();
    }
//    @Override
//    public String toString() {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        return gson.toJson(this);
//
//
//    }

}
