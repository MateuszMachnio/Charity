package pl.coderslab.charity.service.interfaces;

import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import java.util.List;

public interface InstitutionService {

    Institution findById(long id);

    Institution saveInstitution(Institution institution);

    void updateInstitution(Institution institution);

    void deleteInstitution(long id);

    List<Institution> findAllInstitutions();
}
