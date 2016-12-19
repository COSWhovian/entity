package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by russl on 12/16/2016.
 */
public class VideoRelationshipEntity {
    /*
    prequel
sequel
https://en.wikipedia.org/wiki/Sequel
reboot
remake
companion piece



cliffhanger
crossover
film series
(based on) video game
spin-off
tetralogy
trilogy
     */



    String id;

    String relationship;
    String relationshipDesc;

    /*
    example:
       sequel

       A sequel is a narrative, documental, or other work of literature, film, theatre, television, music, or video
       game that continues the story of, or expands upon, some earlier work. In the common context of a
       narrative work of fiction, a sequel portrays events set in the same fictional universe as an earlier work,
       usually chronologically following the events of that work.

       sequel

       https://en.wikipedia.org/wiki/Sequel
     */

    //
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
