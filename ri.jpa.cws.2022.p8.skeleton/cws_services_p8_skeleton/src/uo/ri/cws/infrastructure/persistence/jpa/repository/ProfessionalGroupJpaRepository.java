package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.ProfessionalGroupRepository;
import uo.ri.cws.domain.ProfessionalGroup;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class ProfessionalGroupJpaRepository extends JpaRepositoryFactory
	implements ProfessionalGroupRepository {

    @Override
    public void add(ProfessionalGroup t) {
	// TODO Auto-generated method stub

    }

    @Override
    public void remove(ProfessionalGroup t) {
	// TODO Auto-generated method stub

    }

    @Override
    public Optional<ProfessionalGroup> findById(String id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<ProfessionalGroup> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Optional<ProfessionalGroup> findByName(String name) {
	return Jpa.getManager()
		.createNamedQuery("ProfessionalGroup.findByName",
			ProfessionalGroup.class)
		.setParameter(1, name).getResultStream().findFirst();
    }

}
