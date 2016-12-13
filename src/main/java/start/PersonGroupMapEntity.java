package start;

import javax.persistence.*;

/**
 * Created by russl on 11/14/2016.
 */
@Entity
@Table(name = "person_group_map", schema = "enigmabase" )
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



//
//    @Override
//    public String toString() {
//        return "PersonNameEntity{" +
//                "id='" + id + '\'' +
//                ", nameCd='" + nameCd + '\'' +
//                ", prefix='" + prefix + '\'' +
//                ", first='" + first + '\'' +
//                ", middle='" + middle + '\'' +
//                ", last='" + last + '\'' +
//                ", suffix='" + suffix + '\'' +
//                ", alternate='" + alternate + '\'' +
//                ", personId=" + personId +
//                '}';
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        PersonGroupMapEntity that = (PersonGroupMapEntity) o;
//
//        if (!id.equals(that.id)) return false;
//        if (!nameCd.equals(that.nameCd)) return false;
//        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
//        if (first != null ? !first.equals(that.first) : that.first != null) return false;
//        if (middle != null ? !middle.equals(that.middle) : that.middle != null) return false;
//        if (last != null ? !last.equals(that.last) : that.last != null) return false;
//        if (suffix != null ? !suffix.equals(that.suffix) : that.suffix != null) return false;
//        if (alternate != null ? !alternate.equals(that.alternate) : that.alternate != null) return false;
//        return personId.equals(that.personId);
//    }

//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + nameCd.hashCode();
//        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
//        result = 31 * result + (first != null ? first.hashCode() : 0);
//        result = 31 * result + (middle != null ? middle.hashCode() : 0);
//        result = 31 * result + (last != null ? last.hashCode() : 0);
//        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
//        result = 31 * result + (alternate != null ? alternate.hashCode() : 0);
//        result = 31 * result + personId.hashCode();
//        return result;
//    }
}
