package com.lds.authentication.port.dao.crud;

public interface CrudDao<T> extends
        CreateDao<T>,
        ReadDao<T>,
        DeleteDao,
        UpdateDao<T> {
}
