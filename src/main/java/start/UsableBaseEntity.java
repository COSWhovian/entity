package start;

import org.hibernate.annotations.GenericGenerator;
 //import org.hibernate.annotations.Table;

import javax.persistence.*;

//import javax.persistence.*;

/**
 * Created by russl on 11/27/2016.
 */
@Entity
@Table(name = "usable_base")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsableBaseEntity {
    String id;

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

    String inUseCd;

    @Basic
    @Column(name = "in_use_cd", nullable = false, length = 3)
    public String getInUseCd() {
        return inUseCd;
    }

    public void setInUseCd(String inUseCd) {
        this.inUseCd = inUseCd;
    }


}
