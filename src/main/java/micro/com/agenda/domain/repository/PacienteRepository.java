package micro.com.agenda.domain.repository;

import micro.com.agenda.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long > {

    Optional<Paciente> findByCpf(String cpf);

}
