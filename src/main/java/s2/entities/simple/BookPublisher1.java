package s2.entities.simple;

/**
 * Created by russl on 12/20/2016.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book_publisher")
public class BookPublisher1 implements Serializable {
    private Book book;
    private Publisher publisher;
    private Date publishedDate;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "pub_id")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Column(name = "pub_date")
    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookPublisher{");
        sb.append("book=").append(book.getId());
        sb.append(", publisher=").append(publisher.getId());
        sb.append(", publishedDate=").append(publishedDate);
        sb.append('}');
        return sb.toString();
    }
//    @Override
//    public String toString() {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        return gson.toJson(this);
//    }
}
