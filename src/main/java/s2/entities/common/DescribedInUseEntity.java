package s2.entities.common;

import javax.persistence.*;

/**
 * Created by russl on 12/17/2016.
 */
@Entity
@Table(name = "described")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DescribedInUseEntity extends InUseEntity {


    String description;
    String tip;
    String referenceUrl;

    @Basic
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "tip", nullable = false)
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Basic
    @Column(name = "ref_url", nullable = false)
    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }


}
