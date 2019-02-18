package org.shahin.nazarov.jdbi;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface JdbiService<M extends Serializable, K extends Serializable> extends JdbiDao<M, K> {
    M get(K key);
    Optional<M> getSafe(K key);
    Collection<M> list();
}
