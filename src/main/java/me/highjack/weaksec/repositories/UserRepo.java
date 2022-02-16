package me.highjack.weaksec.repositories;

import me.highjack.weaksec.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
