package s2.entities.person;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by russl on 11/14/2016.
 */
@Entity
@Table(name = "person_name", schema = "enigmabase")
public class PersonNameEntity {

    private String id;
    private String nameCd;

    private String title;
    private String prefix;
    private String first;
    private String middle;
    private String last;
    private String suffix;
    private String nameDesc;

    private String alternate;

    private PersonEntity personId;

    public PersonNameEntity() {
        // empty constructor
    }

    public PersonNameEntity(PersonEntity person, String nameCd, String title, String prefix, String first, String
            middle, String last, String suffix, String alternate, String nameDesc) {
        this.personId = person;
        this.nameCd = nameCd;
        this.title = title;
        this.prefix = prefix;
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.suffix = suffix;
        this.alternate = alternate;
        this.nameDesc = nameDesc;
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


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    public PersonEntity getPersonId() {
        return personId;
    }

    public void setPersonId(PersonEntity personId) {
        this.personId = personId;
    }


    @Basic
    @Column(name = "name_cd", nullable = true, length = 3)
    public String getNameCd() {
        return nameCd;
    }

    public void setNameCd(String nameCd) {
        this.nameCd = nameCd;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Basic
    @Column(name = "name_desc", nullable = true, length = 256)
    public String getNameDesc() {
        return nameDesc;
    }

    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }


    @Basic
    @Column(name = "prefix", nullable = true, length = 50)
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Basic
    @Column(name = "first", nullable = true, length = 100)
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Basic
    @Column(name = "middle", nullable = true, length = 100)
    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    @Basic
    @Column(name = "last", nullable = true, length = 100)
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Basic
    @Column(name = "suffix", nullable = true, length = 50)
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Basic
    @Column(name = "alternate", nullable = true, length = 100)
    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PersonNameEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nameCd='").append(nameCd).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", prefix='").append(prefix).append('\'');
        sb.append(", first='").append(first).append('\'');
        sb.append(", middle='").append(middle).append('\'');
        sb.append(", last='").append(last).append('\'');
        sb.append(", suffix='").append(suffix).append('\'');
        sb.append(", nameDesc='").append(nameDesc).append('\'');
        sb.append(", alternate='").append(alternate).append('\'');
        sb.append(", personId=").append(personId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonNameEntity that = (PersonNameEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameCd, that.nameCd) &&
                Objects.equals(title, that.title) &&
                Objects.equals(prefix, that.prefix) &&
                Objects.equals(first, that.first) &&
                Objects.equals(middle, that.middle) &&
                Objects.equals(last, that.last) &&
                Objects.equals(suffix, that.suffix) &&
                Objects.equals(nameDesc, that.nameDesc) &&
                Objects.equals(alternate, that.alternate) &&
                Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCd, title, prefix, first, middle, last, suffix, nameDesc, alternate, personId);
    }


}
