package com.mealer.mealer.Repository;

import com.mealer.mealer.Model.TypeUser;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeUsersRepository extends JpaRepository<TypeUser,Integer> {
    TypeUser findByName(String name);
}
