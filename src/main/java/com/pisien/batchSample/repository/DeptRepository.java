package com.pisien.batchSample.repository;

import com.pisien.batchSample.domain.Dept;
import org.springframework.data.repository.CrudRepository;

public interface DeptRepository extends CrudRepository<Dept, Long> {

}
