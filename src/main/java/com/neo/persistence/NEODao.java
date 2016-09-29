package com.neo.persistence;

import com.neo.model.Neo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface NEODao extends CrudRepository<Neo, Integer> {

    <S extends Neo> S save(S neo);

    Neo findById(int id);

    //Kudos to: http://stackoverflow.com/questions/22573428/case-insensitive-query-with-spring-crudrepository
    Neo findByNameContainingIgnoreCase(String name);

    Page<Neo> findAll(Pageable pageable);
}
