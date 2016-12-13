package s2.project;

import org.springframework.stereotype.Component;
import s2.entities.ProjectEntity;

import java.time.format.DateTimeFormatter;

/**
 * Created by russl on 11/17/2016.
 */

@Component
public class ProjectData {

    private ProjectEntity projectEntity;

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    @Override
    public String toString() {

        return "ProjectData{" + "name='" + getProjectName() + '\'' + ", description='" + getProjectDesc() + '\'' +
                ", type='" + getProjectType() + '\'' +
                ", createDt='" + getCreateDt() + '\'' +
                ", createdBy='" + getCreatedBy() + '\'' +


                '}';
    }


    public Object getValue(int idx) {
        Object ret;
        switch (idx) {
            case 0:
                ret = getProjectName();
                break;
            case 1:
                ret = getProjectDesc();
                break;
            case 2:
                ret = getProjectType();
                break;
            case 3:
                ret = getCreateDt();
                break;
            case 4:
                ret = getCreatedBy();
                break;

            default:
                ret = "";

        }
        return ret;
    }

    public void setValue(int idx, Object val) {

//        switch (idx) {
//            case 0:
//                personNameEntity.setFirst((String) val);
//                break;
//            case 1:
//                personNameEntity.setLast((String) val);
//                 break;
//
//
//        }

    }

    //    private PersonNameEntity personNameEntity;
    public ProjectData() {


    }

    public ProjectData(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
//        Set<NameGroupEntity> nameGroupEntities = projectEntity.getNameGroupEntities();


    }

    public String getProjectName() {
        return (this.projectEntity == null) ? "" : projectEntity.getProjectName();
    }

    public String getProjectDesc() {
        return (this.projectEntity == null) ? "" : projectEntity.getProjectDesc();
    }

    public String getProjectType() {
        // TODO
        if  (this.projectEntity == null) {
            return "";
        }
        return projectEntity.getProjectType().getTypeDesc();
//        String projectCd = projectEntity.getProjectTypeCd();
//        return projectCd;
//        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//
//        ProjectService projectSerive = (ProjectService) context.getBean("projectService");
//        ProjectTypeEntity projectTypeById = projectSerive.findProjectTypeById(projectCd);
//        return projectTypeById.getTypeDesc();
    }

    public String getCreateDt() {
        if (this.projectEntity == null) {
            return "";
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String string = dateTimeFormatter.format(projectEntity.getCreateDt());

        return string;
    }

    public String getCreatedBy() {
        return (this.projectEntity == null) ? "" : projectEntity.getCreatedBy();
    }


}