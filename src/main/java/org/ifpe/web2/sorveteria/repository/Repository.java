package org.ifpe.web2.sorveteria.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

    void create(T t) throws SQLException;

    void update(T t) throws SQLException;

    T read(int codigo) throws SQLException;

    void delete(int codigo) throws SQLException;

    List<T> readAll() throws SQLException;

}
