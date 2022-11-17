package uo.ri.ui.manager.contractType.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que crea un tipo de contrato
 * 
 * @author Alonso Gago Su�rez - UO269424
 *
 */
public class AddContractTypeAction implements Action {

    /**
     * Crea un nuevo tipo de contrato el cual el nombre y el numero de d�as de
     * compensaci�n son pasados por consola
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

	ContractTypeDto result = Factory.service.forContractTypeService()
		.addContractType(ct);

	// Print result
	Console.println("New contract type succesfully added");
	Printer.printContractType(result);
    }
}
