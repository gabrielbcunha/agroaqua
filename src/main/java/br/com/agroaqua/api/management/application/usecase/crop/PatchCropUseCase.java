package br.com.agroaqua.api.management.application.usecase.crop;

import br.com.agroaqua.api.management.application.dto.crop.get.CropGetResponse;
import br.com.agroaqua.api.management.application.dto.crop.patch.CropPatchRequest;
import br.com.agroaqua.api.management.domain.crop.Crop;
import br.com.agroaqua.api.management.domain.crop.CropRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PatchCropUseCase {

    private final CropRepository cropRepository;

    public PatchCropUseCase(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Transactional
    public CropGetResponse execute(Long id, CropPatchRequest request) {

        if (id == null) {
            throw new IllegalArgumentException("ID is required");
        }

        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crop not found with id: " + id));

        if (request.name() != null && !request.name().isEmpty()) {
            crop.changeName(request.name());
        }

        if (request.description() != null && !request.description().isEmpty()) {
            crop.changeDescription(request.description());
        }

        if (request.normalHumidity() != null &&
                !(request.normalHumidity().compareTo(BigDecimal.ZERO) == 0) &&
                !(request.normalHumidity().compareTo(BigDecimal.ZERO) < 0)
        ) {
            crop.changeNormalHumidity(request.normalHumidity());
        }

        if(request.standardTimeToGrowInDays() != null && request.standardTimeToGrowInDays() >= 0) {
            crop.changeStandardTimeToGrowInDays(request.standardTimeToGrowInDays());
        }

        Crop patchedCrop = cropRepository.save(crop);
        return CropGetResponse.fromDomain(patchedCrop);
    }

}
