package br.com.agroaqua.api.management.infrastructure.controller.crop;

import br.com.agroaqua.api.management.application.dto.crop.create.CropCreateRequest;
import br.com.agroaqua.api.management.application.dto.crop.create.CropCreateResponse;
import br.com.agroaqua.api.management.application.dto.crop.get.CropGetResponse;
import br.com.agroaqua.api.management.application.usecase.crop.FindCropUseCase;
import br.com.agroaqua.api.management.application.usecase.crop.RegisterNewCropUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crop")
public class CropController {

    private final RegisterNewCropUseCase registerNewCropUseCase;
    private final FindCropUseCase findCropUseCase;

    public CropController(RegisterNewCropUseCase registerNewCropUseCase, FindCropUseCase findCropUseCase) {
        this.registerNewCropUseCase = registerNewCropUseCase;
        this.findCropUseCase = findCropUseCase;
    }

    @PostMapping()
    public ResponseEntity<CropCreateResponse> registerCrop(@RequestBody CropCreateRequest request) {
        CropCreateResponse response = registerNewCropUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<CropGetResponse>> findAllCrops() {
        List<CropGetResponse> response = findCropUseCase.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropGetResponse> findCropById(@PathVariable Long id) {
        CropGetResponse response = findCropUseCase.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
