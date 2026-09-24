package br.com.agroaqua.api.management.infrastructure.controller.crop;

import br.com.agroaqua.api.management.application.dto.crop.create.CropCreateRequest;
import br.com.agroaqua.api.management.application.dto.crop.create.CropCreateResponse;
import br.com.agroaqua.api.management.application.usecase.crop.RegisterNewCropUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crop")
public class CropController {

    private final RegisterNewCropUseCase registerNewCropUseCase;

    public CropController(RegisterNewCropUseCase registerNewCropUseCase) {
        this.registerNewCropUseCase = registerNewCropUseCase;
    }

    @PostMapping()
    public ResponseEntity<CropCreateResponse> registerCrop(@RequestBody CropCreateRequest request) {
        CropCreateResponse response = registerNewCropUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
