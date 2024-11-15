package com.lds.authentication.port.user;

import com.lds.authentication.domain.UserModel;
import com.lds.authentication.port.dao.crud.CrudDao;

public interface UserDao extends CrudDao<UserModel>, ReadByEmailDao, UpdatePasswordDao {

}