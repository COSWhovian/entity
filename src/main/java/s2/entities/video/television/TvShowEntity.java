package s2.entities.video.television;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by russl on 12/20/2016.
 */
public class TvShowEntity {

    /*
    example:  Buffy, Supernatural, ...
     */

    private String title;
    private String showDesc;
    private String showTip;

    private String id;

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
