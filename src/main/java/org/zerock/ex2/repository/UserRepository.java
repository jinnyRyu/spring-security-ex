package org.zerock.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.ex2.entity.User;

//crud함수를 가지고있음
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
}
