package s2.dao.impl;

import org.springframework.stereotype.Component;
import s2.dao.AbstractEntityDao;
import s2.dao.EntityDao;
import s2.entities.project.ProjectTypeEntity;

/**
 * Created by russl on 11/27/2016.
 */

@Component
public class ProjectTypeDao extends AbstractEntityDao<ProjectTypeEntity> implements EntityDao<ProjectTypeEntity,
        String> {

    public ProjectTypeDao() {
        // default constructor
    }

    @Override
    public ProjectTypeEntity findById(String projectTypeCd) {
        return getEntityManager().find(ProjectTypeEntity.class, projectTypeCd);
    }

    @Override
    public String getQueryString() {
        return "from ProjectTypeEntity";
    }

}
