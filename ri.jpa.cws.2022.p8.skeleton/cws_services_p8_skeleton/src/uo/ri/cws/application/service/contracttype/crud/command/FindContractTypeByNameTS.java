package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Implementación de la interfaz Command que devuelve un Optional con el tipo de
 * contrato identificado por su nombre (si existe).
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindContractTypeByNameTS
	implements Command<Optional<ContractTypeDto>> {

    public String name;

    public FindContractTypeByNameTS(String name) {
	ArgumentChecks.isNotNull(name, "The name can not be null");
	ArgumentChecks.isNotBlank(name, "The name can not be empty");
	this.name = name;
    }

    /**
     * @return Optional que contiene el tipo de contrato buscado.
     * 
     * @throws BusinessException
     */
    @Override
    public Optional<ContractTypeDto> execute() throws BusinessException {
	Optional<ContractType> ct = Factory.repository.forContractType()
		.findByName(name);
	return ct.isPresent()
		? Optional.ofNullable(DtoAssembler.toContractTypeDto(ct.get()))
		: Optional.empty();
    }

}
