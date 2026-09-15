package org.sirbull.infrastructure.adapter.input;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class GenerarCronogramaRequestDto {
    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    private BigDecimal monto;

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDate fechaCompra;

    @Min(value = 1, message = "Minimo 1 cuota")
    @Max(value = 36, message = "No se permiten mas de 36 cuotas")
    private int cuotasTotales;

    @Min(value = 1, message = "El día de cierre debe ser válido (1-31)")
    @Max(value = 31, message = "El día de cierre debe ser válido (1-31)")
    private int diaCierre;

    @Min(value = 1, message = "El día de pago debe ser válido (1-31)")
    @Max(value = 31, message = "El día de pago debe ser válido (1-31)")
    private int diaPago;

    @NotNull(message = "La TED no puede ser nula")
    @DecimalMin(value = "0.0001", message = "La tasa (TED) debe ser mayor a cero")
    private BigDecimal ted;
}
