package org.shahin.nazarov.jdbi;

import org.jdbi.v3.core.Jdbi;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public abstract class AbstractJdbiService<M, K extends Serializable> implements JdbiService<M, K> {

    protected Class<? extends JdbiMapper<M, K>> mapperClass;
    protected final Jdbi jdbi;
    protected Class<M> mClass;
    protected Class<K> kClass;

    public AbstractJdbiService(Class<? extends JdbiMapper<M,K>> mapperClass) {
        this.mapperClass = mapperClass;
        this.jdbi = JdbiConfig.getInstance().getJdbi();
        this.mClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.kClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public void createTable() {
        jdbi.useExtension(mapperClass, mapper -> mapper.createTable());
    }

    @Override
    public void insert(M model) {
        jdbi.useExtension(mapperClass, mapper -> mapper.insert(model));
    }

    @Override
    public void update(M model, K key) {
        jdbi.useExtension(mapperClass, mapper -> mapper.update(model, key));
    }

    @Override
    public void remove(K key) {
        jdbi.useExtension(mapperClass, mapper -> mapper.remove(key));
    }

    @Override
    public M get(K key, Class<M> mClass) {
        return jdbi.withExtension(mapperClass, mapper -> mapper.get(key, mClass));
    }

    @Override
    public Collection<M> list(Class<M> mClass) {
        return jdbi.withExtension(mapperClass, mapper -> mapper.list(mClass));
    }

    @Override
    public M get(K key) {
        return get(key, mClass);
    }

    @Override
    public Collection<M> list() {
        return list(mClass);
    }
}
