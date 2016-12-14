package s2.entities.project;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by russl on 11/28/2016.
 */

@Entity
@Table(name = "project_type", schema = "enigmabase")
public class ProjectTypeEntity {
    private String id;
    String projectTypeCd;
    String typeDesc;
    String typeTip;


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
    @Column(name="project_type_cd", unique = true, nullable = false, length = 3)
    public String getProjectTypeCd() {
        return projectTypeCd;
    }

    public void setProjectTypeCd(String projectTypeCd) {
        this.projectTypeCd = projectTypeCd;
    }

    @Basic
    @Column(name = "type_desc", nullable = false, length = 256)
    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Basic
    @Column(name = "type_tip", nullable = false, length = 50)
    public String getTypeTip() {
        return typeTip;
    }

    public void setTypeTip(String typeTip) {
        this.typeTip = typeTip;
    }

    @Override
    public String toString() {
        return "ProjectTypeEntity{" +
                "projectTypeCd='" + projectTypeCd + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", typeTip='" + typeTip + '\'' +
                '}';
    }
}
