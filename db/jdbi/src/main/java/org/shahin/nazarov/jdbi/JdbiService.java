package org.shahin.nazarov.jdbi;

import java.io.Serializable;
import java.util.Collection;

public interface JdbiService<M, K extends Serializable> extends JdbiMapper<M, K> {
    M get(K key);
    Collection<M> list();
}
