package com.garby.csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.garby.csv.model.InvoiceRecord;

public interface InvoiceRepository extends JpaRepository<InvoiceRecord, Long> {
}
