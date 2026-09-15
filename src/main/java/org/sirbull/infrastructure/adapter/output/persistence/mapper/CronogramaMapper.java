package org.sirbull.infrastructure.adapter.output.persistence.mapper;

import org.sirbull.domain.model.CronogramaPago;
import org.sirbull.infrastructure.adapter.output.persistence.entity.CronogramaEntity;
import org.sirbull.infrastructure.adapter.output.persistence.entity.CuotaEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CronogramaMapper {

    public static CronogramaEntity toEntity(
            BigDecimal monto,
            LocalDate fechaCompra,
            int cuotasTotales,
            int diaCierre,
            int diaPago,
            BigDecimal ted,
            List<CronogramaPago> domainCuotas
    ){
        CronogramaEntity entity = new CronogramaEntity();
        entity.setMonto(monto);
        entity.setFechaCompra(fechaCompra);
        entity.setCuotasTotales(cuotasTotales);
        entity.setDiaCierre(diaCierre);
        entity.setDiaPago(diaPago);
        entity.setTed(ted);

        List<CuotaEntity> cuotaEntities = domainCuotas.stream().map(domain ->{

            CuotaEntity cuotaEntity = new CuotaEntity();
            cuotaEntity.setNumeroCuota(domain.numeroCuota());
            cuotaEntity.setMontoCuota(domain.cuotaFija());
            cuotaEntity.setFechaVencimiento(domain.fechaVencimiento());
            cuotaEntity.setCronograma(entity);
            return cuotaEntity;
        }).toList();

        entity.setCuotas(cuotaEntities);
        return entity;
    }

}
