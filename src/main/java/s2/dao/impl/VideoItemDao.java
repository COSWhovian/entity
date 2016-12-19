package s2.dao.impl;

import org.springframework.stereotype.Component;
import s2.dao.AbstractEntityDao;
import s2.dao.EntityDao;
import s2.entities.video.VideoItemEntity;

/**
 * Created by russl on 11/27/2016.
 */

@Component
public class VideoItemDao extends AbstractEntityDao<VideoItemEntity> implements EntityDao<VideoItemEntity, String> {

    public VideoItemDao() {
        // default constructor
    }

    @Override
    public VideoItemEntity findById(String id) {
        return getEntityManager().find(VideoItemEntity.class, id);
    }

    @Override
    public String getQueryString() {
        return "from VideoItemEntity";
    }


}
