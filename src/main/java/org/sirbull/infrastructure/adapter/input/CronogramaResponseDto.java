package org.sirbull.infrastructure.adapter.input;

import lombok.Data;
import org.sirbull.domain.model.CronogramaPago;

import java.util.List;

@Data
public class CronogramaResponseDto {
    private Long id;
    private List<CronogramaPago> cuotas;
}
