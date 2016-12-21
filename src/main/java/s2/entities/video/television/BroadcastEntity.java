package s2.entities.video.television;

import javax.persistence.OneToOne;
import java.sql.Timestamp;

/**
 * Created by russl on 12/20/2016.
 */
public class BroadcastEntity {
    /*
    when and 'where' a video was broadcast
    */

    /*
    the id value corresponds to the video item that was broadcast
     */
    private String id;


    private Timestamp airDt;

    @OneToOne
    private OutletEntity outlet;


}
