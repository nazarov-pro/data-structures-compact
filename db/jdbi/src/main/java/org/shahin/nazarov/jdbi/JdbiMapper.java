package org.shahin.nazarov.jdbi;

import java.util.Collection;

public interface JdbiMapper<M, K> extends MapperHelper {
    void createTable();
    void insert(M model);
    void update(M model, K key);
    void remove(K key);
    M get(K key, Class<M> concreteType);
    Collection<M> list(Class<M> concreteType);
}
