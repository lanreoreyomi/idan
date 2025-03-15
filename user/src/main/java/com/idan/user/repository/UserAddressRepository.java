package com.idan.user.repository;

import com.idan.user.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

}
