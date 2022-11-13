package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

/**
 * Implementación de la interfaz Command que devuelve la lista de todos los
 * tipos de contrato existentes.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class FindAllContractTypesTS implements Command<List<ContractTypeDto>> {

    /**
     * @return Lista con todos los tipos de contratos.
     */
    @Override
    public List<ContractTypeDto> execute() throws BusinessException {
	return DtoAssembler.toContractTypeDtoList(
		Factory.repository.forContractType().findAll());
    }

}
