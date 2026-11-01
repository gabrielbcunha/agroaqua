package br.com.agroaqua.api.management.application.usecase.crop;

import br.com.agroaqua.api.management.application.dto.crop.get.CropGetResponse;
import br.com.agroaqua.api.management.domain.crop.Crop;
import br.com.agroaqua.api.management.domain.crop.CropRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCropUseCase {

    private final CropRepository cropRepository;

    public FindCropUseCase(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public Slice<CropGetResponse> findAll(Pageable pageable) {
        return cropRepository.findAll(pageable)
                .map(CropGetResponse::fromDomain);
    }

    public CropGetResponse findById(Long id){
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Crop not found with id " + id));
        return CropGetResponse.fromDomain(crop);
    }

}