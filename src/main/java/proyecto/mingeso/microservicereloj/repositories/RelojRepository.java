package proyecto.mingeso.microservicereloj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.mingeso.microservicereloj.entities.RelojEntity;

import java.util.ArrayList;

@Repository
public interface RelojRepository extends JpaRepository<RelojEntity, Long> {
    @Query(value="select * from marcas as h where h.rut_empleado = :rut_dado", nativeQuery = true)
    ArrayList<RelojEntity> findByRut(@Param("rut_dado") String rut_dado);
}