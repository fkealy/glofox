package com.fkealy.glofox.persistence;

import com.fkealy.glofox.model.GymClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GymClassRepository extends CrudRepository<GymClass,String> {
    
}
