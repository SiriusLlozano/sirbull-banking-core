package org.sirbull.application.usecase;

import org.sirbull.domain.model.CronogramaPago;
import java.util.List;

public record CronogramaResultado(
        long id,
        List<CronogramaPago> cuotas
) {}