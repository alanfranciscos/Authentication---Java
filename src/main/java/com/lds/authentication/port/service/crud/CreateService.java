package com.lds.authentication.port.service.crud;

public interface CreateService<T> {
    int create(final T entity);
}
