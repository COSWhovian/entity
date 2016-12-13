package s2.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by russl on 11/28/2016.
 */
public interface EntityDao<T, I extends Serializable> {

    public void persist(T entity);

    public void update(T entity);

    public T findById(I id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();
}