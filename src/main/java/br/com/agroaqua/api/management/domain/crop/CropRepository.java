package br.com.agroaqua.api.management.domain.crop;

import java.util.List;
import java.util.Optional;

public interface CropRepository {

    Crop save(Crop crop);

    Optional<Crop> findById(Long id);

    List<Crop> findAll();

}