package com.lds.authentication.port.dao.user;

import com.lds.authentication.domain.UserModel;

public interface ReadByEmailDao {
    UserModel readByEmail(final String email);
}
