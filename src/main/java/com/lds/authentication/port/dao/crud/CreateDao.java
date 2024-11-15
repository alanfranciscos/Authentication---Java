package com.lds.authentication.port.dao.crud;

public interface CreateDao<T> {
    int add(final T entity);
}
