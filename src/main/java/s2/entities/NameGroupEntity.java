package s2.entities;

import org.hibernate.annotations.GenericGenerator;
import start.PersonGroupEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 11/18/2016.
 */


@Entity
@Table(name = "name_group", schema = "enigmabase")
public class NameGroupEntity {
    private String id;
    private String groupName;
    private String groupDesc;


    private Set<PersonGroupEntity> personGroupEntities = new HashSet<>();

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
    @Column(name = "group_name", nullable = false, length = 100)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    @Basic
    @Column(name = "group_desc", nullable = false, length = 256)
    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "name_group_map",
            joinColumns = {@JoinColumn(name = "person_group_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "name_group_id", referencedColumnName = "id", nullable = false, updatable = false)})
    public Set<PersonGroupEntity> getPersonGroupEntities() {
        return this.personGroupEntities;
    }

    public void setPersonGroupEntities(Set<PersonGroupEntity> personGroupEntities) {
        this.personGroupEntities = personGroupEntities;
    }

    @Override
    public String toString() {
        return "NameGroupEntity{" +
                "id='" + (id == null ? "" : id) + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
//                ", personGroupEntities=" + personGroupEntities +
                '}';
    }
}
