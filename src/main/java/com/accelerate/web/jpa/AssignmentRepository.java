package com.accelerate.web.jpa;

import org.springframework.data.repository.CrudRepository;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

    // Long findByName(String name);

    //findByCinodeId(int cinodeId);

    // Long addByid(Long id);
}
