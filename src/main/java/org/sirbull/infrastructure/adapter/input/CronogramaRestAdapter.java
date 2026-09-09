package org.sirbull.infrastructure.adapter.input;


import org.sirbull.application.port.input.GenerarCronogramaUseCase;
import org.sirbull.domain.model.CronogramaPago;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cronograma")
public class CronogramaRestAdapter {
    private final GenerarCronogramaUseCase generarCronogramaUseCase;
    public CronogramaRestAdapter(GenerarCronogramaUseCase generarCronogramaUseCase){
        this.generarCronogramaUseCase = generarCronogramaUseCase;
    }

    @PostMapping

    public List<CronogramaPago> generar(@RequestBody GenerarCronogramaRequestDto request){
        return generarCronogramaUseCase.generarCronograma(
                request.getMonto(),
                request.getFechaCompra(),
                request.getCuotas(),
                request.getDiaCierre(),
                request.getDiaPago(),
                request.getTed()
                );

    }








}
