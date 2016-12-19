package s2.entities.project;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import s2.entities.person.NameGroupEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 11/22/2016.
 */
@Entity
@Table(name = "project", schema = "enigmabase")
public class ProjectEntity {
    private String id;


    private String projectName;
    private String projectDesc;
//    private String projectTypeCd;

    private LocalDateTime createDt;
    private String createdBy;

    private Set<NameGroupEntity> nameGroupEntities = new HashSet<>();

    private ProjectTypeEntity projectType;

    public ProjectEntity(String id, String projectName, String projectDesc,
                         String projectType,
                         String createdBy) {
        this.id = id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;

        this.createdBy = createdBy;

        this.createDt = LocalDateTime.now();
    }

    public ProjectEntity(String id, String projectName, String projectDesc,
                         ProjectTypeEntity projectTypeEntity,
                         String createdBy) {
        this.id = id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectType = projectTypeEntity;

        this.createdBy = createdBy;

        this.createDt = LocalDateTime.now();
    }

    public ProjectEntity(String projectName, String projectDesc,
                         ProjectTypeEntity projectTypeEntity,
                         String createdBy) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectType = projectTypeEntity;

        this.createdBy = createdBy;

        this.createDt = LocalDateTime.now();
    }

    public ProjectEntity() {

    }


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

    @Basic
    @Column(name = "project_name", nullable = false, length = 100)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    @Basic
    @Column(name = "project_desc", nullable = false, length = 256)
    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "project_name_group_map",
            joinColumns = {@JoinColumn(name = "project_id",
                    referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "name_group_id",
                    referencedColumnName = "id", nullable = false, updatable = false)})
    public Set<NameGroupEntity> getNameGroupEntities() {
        return this.nameGroupEntities;
    }

    public void setNameGroupEntities(Set<NameGroupEntity> nameGroupEntities) {
        this.nameGroupEntities = nameGroupEntities;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "project_type_id")
    public ProjectTypeEntity getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectTypeEntity projectType) {
        this.projectType = projectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectEntity that = (ProjectEntity) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) {
            return false;
        }
        if (projectDesc != null ? !projectDesc.equals(that.projectDesc) : that.projectDesc != null) {
            return false;
        }
        return nameGroupEntities != null ? nameGroupEntities.equals(that.nameGroupEntities) : that.nameGroupEntities
                == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (projectDesc != null ? projectDesc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id='" + (id == null ? "" : id) + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", createDt=" + createDt +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }

}
