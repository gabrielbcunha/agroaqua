package br.com.agroaqua.api.management.application.usecase.crop;

import br.com.agroaqua.api.management.application.dto.crop.create.CropCreateRequest;
import br.com.agroaqua.api.management.application.dto.crop.create.CropCreateResponse;
import br.com.agroaqua.api.management.domain.crop.Crop;
import br.com.agroaqua.api.management.domain.crop.CropRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterNewCropUseCase {

    private final CropRepository cropRepository;

    public RegisterNewCropUseCase(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Transactional
    public CropCreateResponse execute(CropCreateRequest request) {
        Crop newCrop = new Crop(request.name(), request.description(), request.normalHumidity(), request.standardTimeToGrowInDays());
        Crop savedCrop = cropRepository.save(newCrop);
        return new CropCreateResponse(savedCrop.getId(), savedCrop.getName(), savedCrop.getDescription(), savedCrop.getNormalHumidity(), savedCrop.getStandardTimeToGrowInDays());
    }
}