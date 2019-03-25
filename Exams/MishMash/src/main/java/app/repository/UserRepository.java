package app.repository;

import app.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);


}
