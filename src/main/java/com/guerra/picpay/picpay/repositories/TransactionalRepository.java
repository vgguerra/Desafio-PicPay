package com.guerra.picpay.picpay.repositories;

import com.guerra.picpay.picpay.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<Transactions, Long> {
}
