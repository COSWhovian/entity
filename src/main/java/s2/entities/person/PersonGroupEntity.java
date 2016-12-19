package s2.entities.person;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 11/14/2016.
 */
@Entity
@Table(name = "person_group", schema = "enigmabase")
public class PersonGroupEntity {

    private String id;
    private String groupDesc;
    private String groupName;
    private String groupCd;

    private Set<NameGroupEntity> nameGroupEntities = new HashSet<>();

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
    @Column(name = "group_desc", nullable = true, length = 256)
    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }


    @Basic
    @Column(name = "group_cd", nullable = true, length = 3)
    public String getGroupCd() {
        return groupCd;
    }

    public void setGroupCd(String groupCd) {
        this.groupCd = groupCd;
    }


    @Basic
    @Column(name = "group_name", nullable = true, length = 100)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personGroupEntities")
    public Set<NameGroupEntity> getNameGroupEntities() {
        return this.nameGroupEntities;
    }

    public void setNameGroupEntities(Set<NameGroupEntity> nameGroupEntities) {
        this.nameGroupEntities = nameGroupEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonGroupEntity that = (PersonGroupEntity) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (groupDesc != null ? !groupDesc.equals(that.groupDesc) : that.groupDesc != null) {
            return false;
        }
        return groupName.equals(that.groupName);
    }

    @Override
    public String toString() {
        return "PersonGroupEntity{" +
                "id='" + (id == null ? "" : id) + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                ", groupName='" + groupName + '\'' +
                ", nameGroupEntities=" + nameGroupEntities +
                '}';
    }

    @Override
    public int hashCode() {
        int result = (id == null) ? 0 : id.hashCode();
        result = 31 * result + (groupDesc != null ? groupDesc.hashCode() : 0);
        result = 31 * result + groupName.hashCode();
        return result;
    }


}
