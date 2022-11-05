package proyecto.mingeso.microservicereloj.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "marcas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelojEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_marca", nullable = false)
    private Long id_marca;

    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate fecha;
    @JsonFormat(pattern="HH:mm")
    private LocalTime hora;
    private String rut_empleado;
}