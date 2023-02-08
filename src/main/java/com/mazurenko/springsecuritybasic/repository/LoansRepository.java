package com.mazurenko.springsecuritybasic.repository;

import com.mazurenko.springsecuritybasic.entity.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {

    List<Loans> findByCustomerIdOrderByStartDtDesc(Long customerId);
}
