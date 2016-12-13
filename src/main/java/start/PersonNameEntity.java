package start;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

//    @Override
//    public String toString() {
//        return "PersonNameEntity{" + "id='" + id + '\'' + ", nameCd='" + nameCd + '\'' + ", prefix='" + prefix + '\''
//                + ", first='" + first + '\'' + ", middle='" + middle + '\'' + ", last='" + last + '\'' + ", suffix='"
//                + suffix + '\'' + ", alternate='" + alternate + '\'' + ", personId=" + personId + '}';
//    }

    @Override
    public String toString() {
        return "PersonNameEntity{" + "id='" + id + '\'' + ", nameCd='" + nameCd + '\'' + ", title='" + title + '\'' +
                ", prefix='" + prefix + '\'' + ", first='" + first + '\'' + ", middle='" + middle + '\'' + ", last='"
                + last + '\'' + ", suffix='" + suffix + '\'' + ", nameDesc='" + nameDesc + '\'' + ", alternate='" +
                alternate + '\'' + ", personId=" + personId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonNameEntity that = (PersonNameEntity) o;

        if (!id.equals(that.id)) return false;
        if (!nameCd.equals(that.nameCd)) return false;
        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
        if (first != null ? !first.equals(that.first) : that.first != null) return false;
        if (middle != null ? !middle.equals(that.middle) : that.middle != null) return false;
        if (last != null ? !last.equals(that.last) : that.last != null) return false;
        if (suffix != null ? !suffix.equals(that.suffix) : that.suffix != null) return false;
        if (alternate != null ? !alternate.equals(that.alternate) : that.alternate != null) return false;
        return personId.equals(that.personId);
    }

    @Override
    public int hashCode() {
        int result = (id == null) ? 0 : id.hashCode();
        result = 31 * result + nameCd.hashCode();
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (middle != null ? middle.hashCode() : 0);
        result = 31 * result + (last != null ? last.hashCode() : 0);
        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
        result = 31 * result + (alternate != null ? alternate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (nameDesc != null ? nameDesc.hashCode() : 0);


        if (personId != null) {
            result = 31 * result + personId.hashCode();
        }
        return result;
    }
}
