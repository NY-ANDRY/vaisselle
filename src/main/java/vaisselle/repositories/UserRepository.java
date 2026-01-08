package vaisselle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vaisselle.models.tables.User;

public interface UserRepository extends JpaRepository<User, Long> {}