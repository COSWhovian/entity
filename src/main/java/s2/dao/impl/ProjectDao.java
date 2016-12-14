package s2.dao.impl;

import org.springframework.stereotype.Component;
import s2.dao.AbstractEntityDao;
import s2.dao.EntityDao;
import s2.entities.project.ProjectEntity;
import s2.entities.project.ProjectTypeEntity;

/**
 * Created by russl on 11/27/2016.
 */

@Component
public class ProjectDao extends AbstractEntityDao<ProjectEntity> implements EntityDao<ProjectEntity, String> {


    public ProjectDao() {

    }


    public ProjectEntity findById(String projectCd) {
        ProjectEntity entity = (ProjectEntity) getCurrentSession().get(ProjectEntity.class,
                projectCd);
        return entity;
    }

    @Override
    public String getQueryString() {
        return "from ProjectEntity";
    }

    public void delete(ProjectTypeEntity projectTypeEntity) {
        getCurrentSession().delete(projectTypeEntity);
    }

//    public List<ProjectTypeEntity> findAllProjects() {
//        List<ProjectTypeEntity> entities = (List<ProjectTypeEntity>) getCurrentSession().createQuery("from " +
//                "ProjectTypeEntity").list();
//        return entities;
//    }

//    public void deleteAllProjects() {
//        List<ProjectTypeEntity> entities = findAllProjects();
//        entities.forEach(e -> deleteProject(e));
//    }
}
