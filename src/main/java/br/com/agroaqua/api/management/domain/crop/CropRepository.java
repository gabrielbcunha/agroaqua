package br.com.agroaqua.api.management.domain.crop;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface CropRepository {

    Crop save(Crop crop);

    Optional<Crop> findById(Long id);

    Slice<Crop> findAll(Pageable pageable);

}