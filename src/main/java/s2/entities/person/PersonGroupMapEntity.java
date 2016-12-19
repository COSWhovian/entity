package s2.entities.person;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by russl on 11/14/2016.
 */
@Entity
@Table(name = "person_group_map", schema = "enigmabase")
public class PersonGroupMapEntity {

    private String id;
    //
    PersonGroupEntity personGroupEntity;
    PersonEntity personEntity;


    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonGroupMapEntity that = (PersonGroupMapEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personGroupEntity, that.personGroupEntity) &&
                Objects.equals(personEntity, that.personEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personGroupEntity, personEntity);
    }
}
