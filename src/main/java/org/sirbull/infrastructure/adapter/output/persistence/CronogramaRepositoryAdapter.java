package org.sirbull.infrastructure.adapter.output.persistence;

import org.sirbull.application.port.output.CronogramaRepositoryPort;
import org.sirbull.domain.model.CronogramaPago;
import org.sirbull.infrastructure.adapter.output.persistence.entity.CronogramaEntity;
import org.sirbull.infrastructure.adapter.output.persistence.mapper.CronogramaMapper;
import org.sirbull.infrastructure.adapter.output.persistence.repository.CronogramaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CronogramaRepositoryAdapter implements CronogramaRepositoryPort {

    private final CronogramaRepository cronogramaRepository;

    public CronogramaRepositoryAdapter(CronogramaRepository cronogramaRepository){
        this.cronogramaRepository = cronogramaRepository;

    }

    @Override
    @Transactional
    public long guardarCronograma(
            BigDecimal monto,
            LocalDate fechaCompra,
            int cuotasTotales,
            int diaCierre,
            int diaPago,
            BigDecimal ted,
            List<CronogramaPago> cuotas
    ){

        CronogramaEntity entity = CronogramaMapper.toEntity(
                monto,fechaCompra, cuotasTotales,diaCierre,diaPago,ted,cuotas);
        CronogramaEntity savedEntity = cronogramaRepository.save(entity);
        return savedEntity.getId();

    }
}
