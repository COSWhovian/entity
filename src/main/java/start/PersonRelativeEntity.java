package start;

import javax.persistence.*;

/**
 * Created by russl on 11/14/2016.
 */
@Entity
@Table(name = "person_relative", schema = "enigmabase", catalog = "")
public class PersonRelativeEntity {
    private String id;
    private String personId;
    private String relativeCd;

    @Id
    @Column(name = "id", nullable = true, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "person_id", nullable = true, length = 36)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "relative_cd", nullable = true, length = 3)
    public String getRelativeCd() {
        return relativeCd;
    }

    public void setRelativeCd(String relativeCd) {
        this.relativeCd = relativeCd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonRelativeEntity that = (PersonRelativeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (personId != null ? !personId.equals(that.personId) : that.personId != null) return false;
        if (relativeCd != null ? !relativeCd.equals(that.relativeCd) : that.relativeCd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (personId != null ? personId.hashCode() : 0);
        result = 31 * result + (relativeCd != null ? relativeCd.hashCode() : 0);
        return result;
    }
}
