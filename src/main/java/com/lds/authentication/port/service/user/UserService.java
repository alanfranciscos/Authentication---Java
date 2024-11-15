package com.lds.authentication.port.service.user;

import com.lds.authentication.domain.UserModel;
import com.lds.authentication.port.service.crud.CrudService;

public interface UserService extends CrudService<UserModel>, UpdatePasswordService, ReadByEmailService{

}