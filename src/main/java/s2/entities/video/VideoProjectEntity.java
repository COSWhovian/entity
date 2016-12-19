package s2.entities.video;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import s2.entities.person.NameGroupEntity;
import s2.entities.project.ProjectTypeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 11/22/2016.
 */
@Entity
@Table(name = "video_item", schema = "enigmabase")
public class VideoProjectEntity {
    private String id;


    private String projectName;
    private String projectDesc;


    private LocalDateTime createDt;
    private String createdBy;

    private Set<VideoNameGroupEntity> nameGroupEntities = new HashSet<>();

    private ProjectTypeEntity projectType;

    public VideoProjectEntity(String id, String projectName, String projectDesc,
                              String projectType,
                              String createdBy) {
        this.id = id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;

        this.createdBy = createdBy;

        this.createDt = LocalDateTime.now();
    }

    public VideoProjectEntity(String id, String projectName, String projectDesc,
                              ProjectTypeEntity projectTypeEntity,
                              String createdBy) {
        this.id = id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectType = projectTypeEntity;

        this.createdBy = createdBy;

        this.createDt = LocalDateTime.now();
    }

    public VideoProjectEntity(String projectName, String projectDesc,
                              ProjectTypeEntity projectTypeEntity,
                              String createdBy) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectType = projectTypeEntity;

        this.createdBy = createdBy;

        this.createDt = LocalDateTime.now();
    }

    public VideoProjectEntity() {

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





    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "project_name_group_map",
            joinColumns = {@JoinColumn(name = "project_id",
                    referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "name_group_id",
                    referencedColumnName = "id", nullable = false, updatable = false)})
    public Set<VideoNameGroupEntity> getNameGroupEntities() {
        return this.nameGroupEntities;
    }

    public void setNameGroupEntities(Set<VideoNameGroupEntity> nameGroupEntities) {
        this.nameGroupEntities = nameGroupEntities;
    }



    public void setProjectType(ProjectTypeEntity projectType) {
        this.projectType = projectType;
    }



}
