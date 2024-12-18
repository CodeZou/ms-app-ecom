package org.sid.billingsevice.repository;

import org.sid.billingsevice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
