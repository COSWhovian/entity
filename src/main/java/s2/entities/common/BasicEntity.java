package s2.entities.common;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Created by russl on 11/27/2016.
 */
@Entity
@Table(name = "basic")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass

public abstract class BasicEntity {
     String id;
    private LocalDateTime createDt;
    private String createdBy;


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



    @Column(name = "create_dt", nullable = false)
    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    @Basic
    @Column(name = "created_by", nullable = false, length = 40)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
