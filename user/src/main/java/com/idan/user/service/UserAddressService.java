package com.idan.user.service;

import com.idan.user.Interface.IUserAddressRepository;
import com.idan.user.model.UserAddress;
import com.idan.user.repository.UserAddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAddressService implements IUserAddressRepository {

  final UserAddressRepository userAddressRepository;

  public UserAddressService(UserAddressRepository userAddressRepository){
    this.userAddressRepository = userAddressRepository;
  }


  @Override
  public void save(UserAddress userAddress) {
     userAddressRepository.save(userAddress);
  }
}
