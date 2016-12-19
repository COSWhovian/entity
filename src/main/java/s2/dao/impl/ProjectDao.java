package s2.dao.impl;

import org.springframework.stereotype.Component;
import s2.dao.AbstractEntityDao;
import s2.dao.EntityDao;
import s2.entities.project.ProjectEntity;

/**
 * Created by russl on 11/27/2016.
 */

@Component
public class ProjectDao extends AbstractEntityDao<ProjectEntity> implements EntityDao<ProjectEntity, String> {

    public ProjectDao() {
        // default constructor
    }

    @Override
    public ProjectEntity findById(String projectCd) {
        return getEntityManager().find(ProjectEntity.class, projectCd);
    }

    @Override
    public String getQueryString() {
        return "from ProjectEntity";
    }


}
