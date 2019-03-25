package chushka.repository;

import chushka.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);

}
