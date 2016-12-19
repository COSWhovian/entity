package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by russl on 12/14/2016.
 */
//@Entity
//@Table(name = "trailer", schema = "enigmabase")
public class Trailer {
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
}
