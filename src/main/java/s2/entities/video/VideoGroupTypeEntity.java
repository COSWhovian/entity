package s2.entities.video;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 12/20/2016.
 */
//@Entity
//@Table(name = "video_group")
public class VideoGroupTypeEntity {
    /*
    film series

     */

    private String id;
//    private String title;
//    private String groupDesc;
//
//    private Set<VideoItemEntity> items = new HashSet<>(0);
//
//    public VideoGroupTypeEntity() {
//        // no-arg constructor
//    }
//
//    public VideoGroupTypeEntity(String title, String groupDesc) {
//        this.title = title;
//        this.groupDesc = groupDesc;
//    }
//
//    public VideoGroupTypeEntity(String title, String groupDesc, Set<VideoItemEntity> items) {
//        this.title = title;
//        this.items = items;
//        this.groupDesc = groupDesc;
//    }
//
//
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "group_id", unique = true, nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
//
//    @Column(name = "title", nullable = false, length = 256)
//    public String getTitle() {
//        return this.title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    @Column(name = "group_desc", nullable = false, length = 256)
//    public String getGroupDesc() {
//        return this.groupDesc;
//    }
//
//    public void setGroupDesc(String groupDesc) {
//        this.groupDesc = groupDesc;
//    }
//
//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name = "video_group_map",
//            joinColumns = {@JoinColumn(name = "group_id")}, inverseJoinColumns = {@JoinColumn(name = "video_id")})
//    public Set<VideoItemEntity> getRatings() {
//        return this.items;
//    }
//
//    public void setRatings(Set<VideoItemEntity> items) {
//        this.items = items;
//    }
//
    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);


    }

}
