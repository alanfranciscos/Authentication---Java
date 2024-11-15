package com.lds.authentication.port.dao.user;

public interface UpdatePasswordDao {
    boolean updatePassword(final int id, final String newPassword);
}
