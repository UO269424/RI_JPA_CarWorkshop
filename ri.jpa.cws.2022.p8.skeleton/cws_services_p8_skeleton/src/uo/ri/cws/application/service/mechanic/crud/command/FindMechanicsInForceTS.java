package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService.MechanicDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.Contract.ContractState;
import uo.ri.cws.domain.Mechanic;

/**
 * Implementación de la interfaz Command lista los mecánicos con contratos en
 * activo.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindMechanicsInForceTS implements Command<List<MechanicDto>> {

    /**
     * @return lista conteniendo los {@link MechanicBLDto} con contratos en
     *         activo.
     * 
     * @throws BusinessException
     */
    @Override
    public List<MechanicDto> execute() throws BusinessException {
	List<Contract> contracts = Factory.repository.forContract().findAll();
	List<Contract> contractsInForce = new ArrayList<Contract>();
	for(Contract c : contracts)	{
	    if(c.getState() == ContractState.IN_FORCE)
		contractsInForce.add(c);
	}
	
	List<Mechanic> mechanics = new ArrayList<Mechanic>();

	if (contractsInForce.isEmpty())
	    return DtoAssembler.toMechanicDtoList(mechanics);

	for (Contract con : contractsInForce) {
	    mechanics.add(con.getMechanic());
	}

	return DtoAssembler.toMechanicDtoList(mechanics);
    }
}