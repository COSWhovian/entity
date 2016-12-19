package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by russl on 12/15/2016.
 */
//@Entity
//@Table(name = "plot_type", schema = "enigmabase")
public class PlotTypeEntity {


    String id;
    String plotTypeCd;
    String plotTypeTip;
    String plotTypeDesc;


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
    @Column(name = "plot_type_cd", unique = true, nullable = false, length = 3)
    public String getPlotTypeCd() {
        return plotTypeCd;
    }

    public void setPlotTypeCd(String plotTypeCd) {
        this.plotTypeCd = plotTypeCd;
    }

    @Basic
    @Column(name = "plot_type_tip", unique = true, nullable = false, length = 50)
    public String getPlotTypeTip() {
        return plotTypeTip;
    }

    public void setPlotTypeTip(String plotTypeTip) {
        this.plotTypeTip = plotTypeTip;
    }

    @Basic
    @Column(name = "plot_type_desc", nullable = true, length = 256)
    public String getPlotTypeDesc() {
        return plotTypeDesc;
    }

    public void setPlotTypeDesc(String plotTypeDesc) {
        this.plotTypeDesc = plotTypeDesc;
    }


}
