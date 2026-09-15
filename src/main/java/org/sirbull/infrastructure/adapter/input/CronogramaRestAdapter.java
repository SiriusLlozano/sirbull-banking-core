package org.sirbull.infrastructure.adapter.input;


import jakarta.validation.Valid;
import org.sirbull.application.port.input.GenerarCronogramaUseCase;
import org.sirbull.application.usecase.CronogramaResultado;
import org.sirbull.domain.model.CronogramaPago;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public CronogramaResponseDto generar(
            @Valid @RequestBody GenerarCronogramaRequestDto request
    ){
        CronogramaResultado resultado = generarCronogramaUseCase.generarCronograma(
                request.getMonto(),
                request.getFechaCompra(),
                request.getCuotasTotales(),
                request.getDiaCierre(),
                request.getDiaPago(),
                request.getTed()
        );

        CronogramaResponseDto response = new CronogramaResponseDto();
        response.setId(resultado.id());
        response.setCuotas(resultado.cuotas());

        return response;
    }


}
