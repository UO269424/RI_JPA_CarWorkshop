package uo.ri.ui.manager.contractType.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que actualiza un tipo de contrato dado su nombre
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class UpdateContractTypeAction implements Action {

    /**
     * Actualiza un tipo de contrato cuyo nombre es pasado por consola. El tipo
     * de contrato es actualizado con un nuevo número de días de compensación
     * también pasados por consola.
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String name = Console.readString("Name ");
	Double compensationDays = Console.readDouble("Compensation days ");

	// Process

	ContractTypeDto ct = new ContractTypeDto();
	ct.compensationDays = compensationDays;
	ct.name = name;

	Factory.service.forContractTypeService().updateContractType(ct);

	// Print result
	Console.println("Contract type succesfully updated");

    }

}
