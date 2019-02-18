package org.shahin.nazarov.jdbi;

import java.io.Serializable;
import java.util.Collection;

public interface JdbiDao<M extends Serializable, K extends Serializable> extends DaoHelper {
    void createTable();
    void insert(M model);
    void insert(Collection<M> models);
    void update(M model, K key);
    void update(Collection<M> models, Collection<K> keys);
    void remove(K key);
    void remove(Collection<K> keys);
    M get(K key, Class<M> concreteType);
    Collection<M> list(Class<M> concreteType);
    long size();
}
