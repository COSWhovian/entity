package s2.entities.simple;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by russl on 12/21/2016.
 */
@Embeddable
public class VideoGroupKey implements Serializable {
    public VideoGroupKey() {

    }

    public VideoGroupKey(String groupId, String itemId) {
        this.groupId = groupId;
        this.itemId = itemId;
    }

    @Column(name = "group_id", nullable = false)
    private String groupId;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    /**
     * getters and setters
     **/
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String id) {
        this.groupId = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String id) {
        this.itemId = id;
    }
}
