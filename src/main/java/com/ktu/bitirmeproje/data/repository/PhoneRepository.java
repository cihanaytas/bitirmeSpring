package com.ktu.bitirmeproje.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ktu.bitirmeproje.data.entity.prod.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone,Long>{

}
