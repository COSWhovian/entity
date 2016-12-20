package s2.entities.simple;

/**
 * Created by russl on 12/20/2016.
 */
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Publisher {
    private String id;
    private String name;
    private Set<BookPublisher> bookPublishers;

    public Publisher(){

    }

    public Publisher(String name){
        this.name = name;
    }

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "pub_id", unique = true, nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "pub_name", nullable = false, length = 255)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "publisher")
    public Set<BookPublisher> getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(Set<BookPublisher> bookPublishers) {
        this.bookPublishers = bookPublishers;
    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("Publisher{");
//        sb.append("id='").append(id).append('\'');
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", bookPublishers=").append(bookPublishers);
//        sb.append('}');
//        return sb.toString();
//    }
}
