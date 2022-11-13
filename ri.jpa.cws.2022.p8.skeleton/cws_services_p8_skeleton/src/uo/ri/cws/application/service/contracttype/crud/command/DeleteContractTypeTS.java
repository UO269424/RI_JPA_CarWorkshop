package uo.ri.cws.application.service.contracttype.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;
import uo.ri.cws.domain.ContractType;
import uo.ri.util.assertion.ArgumentChecks;

/**
 * Implementación de la interfaz Command que elimina un tipo de contrato dado su
 * identificador.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class DeleteContractTypeTS implements Command<Void> {

    private String contractTypeName;

    public DeleteContractTypeTS(String contractTypeName) {
	ArgumentChecks.isNotNull(contractTypeName,
		"El nombre del tipo de contrato no puede ser null");
	ArgumentChecks.isNotBlank(contractTypeName.trim(),
		"El nombre del tipo de contrato no puede estar vacío");
	this.contractTypeName = contractTypeName;
    }

    /**
     * Elimina un tipo de contrato dado su id siempre y cuando no tenga
     * contratos asociados.
     * 
     * @throws BusinessException
     */
    @Override
    public Void execute() throws BusinessException {
	Optional<ContractType> op = Factory.repository.forContractType()
		.findByName(contractTypeName);
	BusinessChecks.exists(op,
		"El tipo de contrato que se quiere eliminar no existe");

	ContractType ct = op.get();

	if (hasAssociatedContracts())
	    throw new BusinessException(
		    "EL tipo de contrato que se desea eliminar tiene contratos asociados");

	Factory.repository.forContractType().remove(ct);

	return null;
    }

    private boolean hasAssociatedContracts() {
	List<Contract> contracts = Factory.repository.forContract().findAll();
	boolean present= false;
	for(Contract c : contracts)	{
	    if(c.getContractType().getName().equalsIgnoreCase(contractTypeName))
		present=true;
	}	
	return present;
    }

}
