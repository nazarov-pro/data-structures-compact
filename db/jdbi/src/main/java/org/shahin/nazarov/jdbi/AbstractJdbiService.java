package org.shahin.nazarov.jdbi;

import lombok.Getter;
import org.jdbi.v3.core.Jdbi;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Optional;

@Getter
public abstract class AbstractJdbiService<M extends Serializable, K extends Serializable> implements JdbiService<M, K> {
    private final Class<? extends JdbiDao<M, K>> mapperClass;
    private final Jdbi jdbi;
    private final Class<M> modelClass;
    private final Class<K> keyClass;

    public AbstractJdbiService(final Class<? extends JdbiDao<M, K>> daoClass) {
        this.mapperClass = daoClass;
        this.jdbi = JdbiConfig.getInstance().getJdbi();
        this.modelClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.keyClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * Creating table DDL statement
     * PS: Not recommended, use liquibase or other db migration tool
     */
    public void createTable() {
        jdbi.useExtension(mapperClass, mapper -> mapper.createTable());
    }

    /**
     * Insert statement
     * @param model object which will be insert
     */
    @Override
    public void insert(final M model) {
        jdbi.useExtension(mapperClass, mapper -> mapper.insert(model));
    }

    /**
     * Update statement
     * @param model object which will be update
     * @param key object identifier
     */
    @Override
    public void update(final M model, final K key) {
        jdbi.useExtension(mapperClass, mapper -> mapper.update(model, key));
    }

    /**
     * Remove (Delete) statement
     * @param key object identifier
     */
    @Override
    public void remove(final K key) {
        jdbi.useExtension(mapperClass, mapper -> mapper.remove(key));
    }

    /**
     * Batch insert (multiple objects)
     * @param models Collection of objects
     */
    @Override
    public void insert(final Collection<M> models) {
        jdbi.useExtension(mapperClass, mapper -> mapper.insert(models));
    }

    /**
     * Bach update (multiple objects)
     * @param models Collection of objects which will change
     * @param keys Collection of identifiers of objects
     */
    @Override
    public void update(final Collection<M> models, final Collection<K> keys) {
        jdbi.useExtension(mapperClass, mapper -> mapper.update(models, keys));
    }

    /**
     * Batch remove
     * @param keys Collection of identifiers of objects
     */
    @Override
    public void remove(final Collection<K> keys) {
        jdbi.useExtension(mapperClass, mapper -> mapper.remove(keys));
    }

    /**
     * Get one object by a provided key
     * @param key Identifier of object
     * @param mClass Required type of class
     * @return Object of mClass
     */
    @Override
    public M get(final K key, final Class<M> mClass) {
        return jdbi.withExtension(mapperClass, mapper -> mapper.get(key, mClass));
    }

    /**
     * Collection of all objects
     * @param mClass Required type of object
     * @return Object of mClass
     */
    @Override
    public Collection<M> list(final Class<M> mClass) {
        return jdbi.withExtension(mapperClass, mapper -> mapper.list(mClass));
    }

    /**
     * Get object by key (without mentioning class type)
     * @param key Identifier of object
     * @return Returns object
     */
    @Override
    public M get(final K key) {
        return get(key, modelClass);
    }

    /**
     * Collection of all objects (without mentioning class type)
     * @return Returns collection of objects
     */
    @Override
    public Collection<M> list() {
        return list(modelClass);
    }

    /**
     * Get count of elements
     * @return Size int64
     */
    @Override
    public long size() {
        return jdbi.withExtension(mapperClass, mapper -> mapper.size());
    }

    /**
     * Get object as Optional format
     * @param key Object identifier
     * @return Optional object
     */
    @Override
    public Optional<M> getSafe(final K key) {
        return Optional.ofNullable(get(key));
    }
}
