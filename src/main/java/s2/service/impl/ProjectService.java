package s2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import s2.dao.impl.ProjectDao;
import s2.dao.impl.ProjectTypeDao;
import s2.entities.ProjectEntity;
import s2.entities.ProjectTypeEntity;

import java.util.List;

/**
 * Created by russl on 11/28/2016.
 */
//@Service("projectService")
@Component
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectTypeDao projectTypeDao;


    public ProjectService() {
        projectDao = new ProjectDao();
        projectTypeDao = new ProjectTypeDao();

    }

    public void createProject(ProjectEntity entity) {
        projectDao.openCurrentSessionwithTransaction();
        projectDao.persist(entity);
        projectDao.closeCurrentSessionwithTransaction();
    }

    public void updateProject(ProjectEntity entity) {
        projectDao.openCurrentSessionwithTransaction();
        projectDao.update(entity);
        projectDao.closeCurrentSessionwithTransaction();
    }

    public ProjectEntity findProjectById(String id) {
        projectDao.openCurrentSession();
        ProjectEntity projectEntity = projectDao.findById(id);
        projectDao.closeCurrentSession();
        return projectEntity;
    }

    public void deleteProject(String id) {
        projectDao.openCurrentSessionwithTransaction();
        ProjectEntity projectEntity = projectDao.findById(id);
        projectDao.delete(projectEntity);
        projectDao.closeCurrentSessionwithTransaction();
    }

    public List<ProjectEntity> findAllProjects() {
        projectDao.openCurrentSession();
        List<ProjectEntity> projectEntities = projectDao.findAll();

        projectEntities.forEach(e -> e.getNameGroupEntities().forEach(n -> n.getPersonGroupEntities()));

        projectDao.closeCurrentSession();
        return projectEntities;
    }

    public void deleteAllProjects() {
        projectDao.openCurrentSessionwithTransaction();
        projectDao.deleteAll();
        projectDao.closeCurrentSessionwithTransaction();
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    //
    //
    //
    public void createProjectType(ProjectTypeEntity entity) {
        projectTypeDao.openCurrentSessionwithTransaction();
        projectTypeDao.persist(entity);
        projectTypeDao.closeCurrentSessionwithTransaction();
    }

    public void updateProjectType(ProjectTypeEntity entity) {
        projectTypeDao.openCurrentSessionwithTransaction();
        projectTypeDao.update(entity);
        projectTypeDao.closeCurrentSessionwithTransaction();
    }

    public ProjectTypeEntity findProjectTypeById(String id) {
        projectTypeDao.openCurrentSession();
        ProjectTypeEntity projectTypeEntity = projectTypeDao.findById(id);
        projectTypeDao.closeCurrentSession();
        return projectTypeEntity;
    }

    public void deleteProjectType(String id) {
        projectTypeDao.openCurrentSessionwithTransaction();
        ProjectTypeEntity projectTypeEntity = projectTypeDao.findById(id);
        projectTypeDao.delete(projectTypeEntity);
        projectTypeDao.closeCurrentSessionwithTransaction();
    }

    public List<ProjectTypeEntity> findAllProjectTypes() {
        projectTypeDao.openCurrentSession();
        List<ProjectTypeEntity> projectTypeEntities = projectTypeDao.findAll();
        projectTypeDao.closeCurrentSession();
        return projectTypeEntities;
    }

    public void deleteAllProjectTypes() {
        projectTypeDao.openCurrentSessionwithTransaction();
        projectTypeDao.deleteAll();
        projectTypeDao.closeCurrentSessionwithTransaction();
    }
    //
    //
    //

}