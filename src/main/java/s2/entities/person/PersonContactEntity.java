package s2.entities.person;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by russl on 11/14/2016.
 */
@Entity
@Table(name = "person_contact", schema = "enigmabase")
public class PersonContactEntity {
    private String id;
    private String contactDesc;
    private PersonEntity person;
    private PersonEntity contactPerson;
    // person_id
    // contact_person_id
    private LocalDateTime startEventId;
    private LocalDateTime endEventId;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "person_id")
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "contact_person_id")
    public PersonEntity getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(PersonEntity contactPerson) {
        this.contactPerson = contactPerson;
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
    @Column(name = "contact_desc", nullable = true, length = 256)
    public String getContactDesc() {
        return contactDesc;
    }

    public void setContactDesc(String contactDesc) {
        this.contactDesc = contactDesc;
    }


    @Basic
    @Column(name = "start_event_id", nullable = true)
    public LocalDateTime getStartEventId() {
        return startEventId;
    }

    public void setStartEventId(LocalDateTime startEventId) {
        this.startEventId = startEventId;
    }


    @Basic
    @Column(name = "end_event_id", nullable = true)
    public LocalDateTime getEndEventId() {
        return endEventId;
    }

    public void setEndEventId(LocalDateTime endEventId) {
        this.endEventId = endEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonContactEntity that = (PersonContactEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (contactDesc != null ? !contactDesc.equals(that.contactDesc) : that.contactDesc != null) {
            return false;
        }
        if (person != null ? !person.equals(that.person) : that.person != null) {
            return false;
        }
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        if (startEventId != null ? !startEventId.equals(that.startEventId) : that.startEventId != null) {
            return false;
        }
        return endEventId != null ? endEventId.equals(that.endEventId) : that.endEventId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (contactDesc != null ? contactDesc.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (startEventId != null ? startEventId.hashCode() : 0);
        result = 31 * result + (endEventId != null ? endEventId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonContactEntity{" +
                "id='" + id + '\'' +
                ", contactDesc='" + contactDesc + '\'' +
                ", person=" + person +
                ", contactPerson=" + contactPerson +
                ", startEventId=" + startEventId +
                ", endEventId=" + endEventId +
                '}';
    }
}
