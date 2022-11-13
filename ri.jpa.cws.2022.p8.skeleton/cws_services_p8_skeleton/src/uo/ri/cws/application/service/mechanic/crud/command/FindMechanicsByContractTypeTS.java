package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Contract.ContractState;
import uo.ri.cws.domain.ContractType;
import uo.ri.cws.domain.Mechanic;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Implementación de la interfaz Command que lista a los mecánicos con un tipo
 * de contrato dado.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindMechanicsByContractTypeTS
	implements Command<List<MechanicDto>> {

    public String contractTypeId;

    public FindMechanicsByContractTypeTS(String contractTypeId) {
	ArgumentChecks.isNotNull(contractTypeId,
		"El id del tipo de contrato no puede ser nulo");
	ArgumentChecks.isNotBlank(contractTypeId.trim(),
		"El id del tipo de contrato no puede estar vacío");
	this.contractTypeId = contractTypeId;
    }

    /**
     * @return lista con los {@link MechanicBLDto} con el tipo de contrato dado.
     * 
     * @throws BusinessException
     */
    @Override
    public List<MechanicDto> execute() throws BusinessException {

	Optional<ContractType> ct = Factory.repository.forContractType()
		.findByName(contractTypeId);
	if (ct.isEmpty())
	    return null;
	List<Contract> contracts = Factory.repository.forContract()
		.findByContractTypeId(ct.get().getId());
	List<Contract> inForce = new ArrayList<>();
	for (Contract c : contracts) {
	    if (c.getState() == ContractState.IN_FORCE)
		inForce.add(c);
	}

	List<Mechanic> mechanics = new ArrayList<Mechanic>();

	for (Contract contract : inForce) {
	    mechanics.add(contract.getMechanic());
	}

	return DtoAssembler.toMechanicDtoList(mechanics);
    }

}
