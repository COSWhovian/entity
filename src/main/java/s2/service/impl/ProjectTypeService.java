package s2.service.impl;

import s2.dao.impl.ProjectTypeDao;
import s2.entities.ProjectTypeEntity;

import java.util.List;

/**
 * Created by russl on 12/2/2016.
 */
public   class ProjectTypeService {

    private static ProjectTypeDao projectTypeDao;

    public ProjectTypeService() {
        projectTypeDao = new ProjectTypeDao();
    }

//    public void createProjectType(ProjectTypeEntity entity) {
//        projectTypeDao.openCurrentSessionwithTransaction();
//        projectTypeDao.persist(entity);
//        projectTypeDao.closeCurrentSessionwithTransaction();
//    }
//
//    public void updateProjectType(ProjectTypeEntity entity) {
//        projectTypeDao.openCurrentSessionwithTransaction();
//        projectTypeDao.update(entity);
//        projectTypeDao.closeCurrentSessionwithTransaction();
//    }
//
//    public ProjectTypeEntity findProjectTypeById(String id) {
//        projectTypeDao.openCurrentSession();
//        ProjectTypeEntity projectTypeEntity = projectTypeDao.findById(id);
//        projectTypeDao.closeCurrentSession();
//        return projectTypeEntity;
//    }
//
//    public void deleteProjectType(String id) {
//        projectTypeDao.openCurrentSessionwithTransaction();
//        ProjectTypeEntity projectTypeEntity = projectTypeDao.findById(id);
//        projectTypeDao.delete(projectTypeEntity);
//        projectTypeDao.closeCurrentSessionwithTransaction();
//    }
//
//    public List<ProjectTypeEntity> findAllProjectTypes() {
//        projectTypeDao.openCurrentSession();
//        List<ProjectTypeEntity> projectTypeEntities = projectTypeDao.findAll();
//        projectTypeDao.closeCurrentSession();
//        return projectTypeEntities;
//    }
//
//    public void deleteAllProjectTypes() {
//        projectTypeDao.openCurrentSessionwithTransaction();
//        projectTypeDao.deleteAll();
//        projectTypeDao.closeCurrentSessionwithTransaction();
//    }

    public ProjectTypeDao getProjectTypeDao() {
        return projectTypeDao;
    }
}