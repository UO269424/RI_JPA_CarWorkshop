package uo.ri.ui.manager.contractType.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que borra un tipo de contrato
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class DeleteContractTypeAction implements Action {

    /**
     * Borra el tipo de contrato dado su nombre pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String name = Console.readString("Name ");

	// Process
	Factory.service.forContractTypeService().deleteContractType(name);

	// Print result
	Console.println("Contract type succesfully deleted");
    }

}
