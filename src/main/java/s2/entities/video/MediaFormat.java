package s2.entities.video;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by russl on 12/15/2016.
 */
public class MediaFormat {
/*
this can be a more general type
https://en.wikipedia.org/wiki/Category:Media_formats

book formats
audio book
e-books

comics

discontinued:

film

publications by format

radio

religious media formats

television
video

album
betamax
book
film
internet
musement
newspaper
photo print size
podcast
radio






video
book
audio
...

 */

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
