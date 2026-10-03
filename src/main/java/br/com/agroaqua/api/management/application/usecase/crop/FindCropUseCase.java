package br.com.agroaqua.api.management.application.usecase.crop;

import br.com.agroaqua.api.management.application.dto.crop.get.CropGetResponse;
import br.com.agroaqua.api.management.domain.crop.Crop;
import br.com.agroaqua.api.management.domain.crop.CropRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCropUseCase {

    private final CropRepository cropRepository;

    public FindCropUseCase(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public List<CropGetResponse> findAll(){
        return cropRepository.findAll()
                .stream()
                .map(CropGetResponse::fromDomain)
                .toList();
    }

    public CropGetResponse findById(Long id){
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Crop not found with id " + id));
        return CropGetResponse.fromDomain(crop);
    }

}