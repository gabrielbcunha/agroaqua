package br.com.agroaqua.api.management.domain.handling;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface HandlingRepository {

    Handling save(Handling handling);

    Optional<Handling> findById(Long id);

    Slice<Handling> findAll(Pageable pageable);

    Slice<Handling> findByEmployeeId(Long employeeId, Pageable pageable);

    Slice<Handling> findByPlotId(Long plotId, Pageable pageable);

}
