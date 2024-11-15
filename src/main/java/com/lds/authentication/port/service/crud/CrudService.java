package com.lds.authentication.port.service.crud;

public interface CrudService<T> extends
        CreateService<T>,
        ReadService<T>,
        UpdateService<T>,
        DeleteService{

}
