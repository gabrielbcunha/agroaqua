package br.com.agroaqua.api.management.infrastructure.controller.plot;

import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateRequest;
import br.com.agroaqua.api.management.application.dto.plot.create.PlotCreateResponse;
import br.com.agroaqua.api.management.application.usecase.plot.RegisterNewPlotUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plot")
public class PlotController {

    private final RegisterNewPlotUseCase registerNewPlotUseCase;

    public PlotController(RegisterNewPlotUseCase registerNewPlotUseCase) {
        this.registerNewPlotUseCase = registerNewPlotUseCase;
    }

    @PostMapping()
    public ResponseEntity<PlotCreateResponse> registerPlot(@RequestBody PlotCreateRequest request) {
        PlotCreateResponse response = registerNewPlotUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}