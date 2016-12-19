package s2.entities.common;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by russl on 12/16/2016.
 */
@Entity
@Table(name = "usable_base")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InUseEntity extends BasicEntity {


    /*
    see InUseCd
     */
    String inUseCd;
    private LocalDateTime inUseStartDt;
    private LocalDateTime inUseEndDt;


    @Basic
    @Column(name = "in_use_start_dt", nullable = false)

    public LocalDateTime getInUseStartDt() {
        return inUseStartDt;
    }

    public void setInUseStartDt(LocalDateTime inUseStartDt) {
        this.inUseStartDt = inUseStartDt;
    }

    @Basic
    @Column(name = "in_use_end_dt", nullable = false)
    public LocalDateTime getInUseEndDt() {
        return inUseEndDt;
    }

    public void setInUseEndDt(LocalDateTime inUseEndDt) {
        this.inUseEndDt = inUseEndDt;
    }


    @Basic
    @Column(name = "in_use_cd", nullable = false, length = 3)
    public String getInUseCd() {
        return inUseCd;
    }

    public void setInUseCd(String inUseCd) {
        this.inUseCd = inUseCd;
    }
}
