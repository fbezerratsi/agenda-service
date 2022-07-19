package micro.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import micro.com.agenda.domain.entity.Paciente;
import micro.com.agenda.domain.repository.PacienteRepository;
import micro.com.agenda.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        if (Objects.isNull(paciente.getId())) {
            this.validarInsercaoCpf(paciente);
        }

        return repository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void validarInsercaoCpf(Paciente paciente) {
        Optional< Paciente > optPaciente = repository.findByCpf(paciente.getCpf());

        if (optPaciente.isPresent()) {
            throw new BusinessException("Cpf já existente na base de dados");
        }
    }
}
