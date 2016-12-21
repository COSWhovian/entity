package s2.entities.simple;

import s2.entities.video.VideoGroupEntity;

import javax.persistence.*;

/**
 * Created by russl on 12/21/2016.
 */
@Entity
@Table(name = "wombat")
public class WombatEntity {

    @EmbeddedId
    private VideoGroupKey myKey;

    private String name;

    public VideoGroupKey getMyKey() {
        return myKey;
    }

    public void setMyKey(VideoGroupKey myKey) {
        this.myKey = myKey;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumns( { @JoinColumn(name="GROUP_ID"), @JoinColumn(name="ITEM_ID")})
    @JoinColumn(name="group_id",   insertable = false, updatable = false)
//    @JoinColumn(name="group_id" )
    private VideoGroupEntity groupEntity;



//    public VideoGroupEntity getGroupEntity() {
//        return groupEntity;
//    }
//
//    public void setGroupEntity(VideoGroupEntity groupEntity) {
//        this.groupEntity = groupEntity;
//    }
}
