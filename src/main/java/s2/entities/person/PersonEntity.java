package s2.entities.person;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by russl on 11/14/2016.
 */
@Entity
@Table(name = "person", schema = "enigmabase")
public class PersonEntity {
    private String id;
    private String personDesc;

    private LocalDateTime birthdate;


    private Set<PersonNameEntity> personNameEntities = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Set<PersonNameEntity> getPersonNameEntities() {
        return this.personNameEntities;
    }

    public void setPersonNameEntities(Set<PersonNameEntity> personNameEntities) {
        this.personNameEntities = personNameEntities;
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
    @Column(name = "person_desc", nullable = true, length = 256)
    public String getPersonDesc() {
        return personDesc;
    }

    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc;
    }


    @Basic
    @Column(name = "birthdate", nullable = true)
    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (!id.equals(that.id)) return false;
        if (!personDesc.equals(that.personDesc)) return false;
        return birthdate.equals(that.birthdate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + personDesc.hashCode();
        result = 31 * result + birthdate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id='" + id + '\'' +
                ", personDesc='" + personDesc + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
