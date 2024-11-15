package com.lds.authentication.port.service.user;

import com.lds.authentication.domain.UserModel;

public interface ReadByEmailService {
    UserModel findByEmail(final String email);
}
