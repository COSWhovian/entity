package s2.entities.process;

import s2.entities.common.BasicEntity;

import javax.persistence.*;

/**
 * Created by russl on 11/27/2016.
 */
@Entity
@Table(name = "process_control")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class ProcessControlEntity extends BasicEntity {
    String id;


    String processName;

    @Basic
    @Column(name = "process_name", nullable = false, length = 100)
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }


}
