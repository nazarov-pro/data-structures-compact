package org.shahin.nazarov.jdbi;

public interface DaoHelper {
    default String generateList(String table){
        return String.format("SELECT * FROM %s", table);
    }

    default String generateGetById(String table, String coulmnName, String key){
        return String.format("SELECT * FROM %s WHERE %s = :%s", table, coulmnName, key);
    }

    default String generateRemoveById(String table, String coulmnName, String key){
        return String.format("DELETE FROM %s WHERE %s = :%s", table, coulmnName, key);
    }
}
