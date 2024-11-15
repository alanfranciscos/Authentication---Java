package com.lds.authentication.port.user;

public interface UpdatePasswordDao {
    boolean updatePassword(final int id, final String newPassword);
}
