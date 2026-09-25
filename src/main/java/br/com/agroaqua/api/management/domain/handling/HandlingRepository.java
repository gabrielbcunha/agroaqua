package br.com.agroaqua.api.management.domain.handling;

import java.util.List;
import java.util.Optional;

public interface HandlingRepository {

    Handling save(Handling handling);

    Optional<Handling> findById(Long id);

    List<Handling> findAll();

}
