package pl.coderslab.charity.service.implementations;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.interfaces.InstitutionService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Institution findById(long id) {
        return institutionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no such Institution"));
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    @Override
    public void updateInstitution(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public void deleteInstitution(long id) {
        institutionRepository.deleteById(id);
    }

    @Override
    public List<Institution> findAllInstitutions() {
        return institutionRepository.findAll();
    }
}
