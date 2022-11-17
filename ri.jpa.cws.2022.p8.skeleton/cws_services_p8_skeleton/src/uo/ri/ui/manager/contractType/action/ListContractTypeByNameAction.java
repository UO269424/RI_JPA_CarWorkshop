package uo.ri.ui.manager.contractType.action;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService.ContractTypeDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra un tipo de contrato dado un nombre
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListContractTypeByNameAction implements Action {

    /**
     * Encuentra un tipo de contrato dado su nombre pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	String name = Console.readString("Contract type name ");

	Optional<ContractTypeDto> ct = null;

	ct = Factory.service.forContractTypeService()
		.findContractTypeByName(name);

	if (ct.isEmpty())
	    Console.println("\nContractType does not exist\n");
	else
	    Printer.printContractType(ct.get());

    }

}
