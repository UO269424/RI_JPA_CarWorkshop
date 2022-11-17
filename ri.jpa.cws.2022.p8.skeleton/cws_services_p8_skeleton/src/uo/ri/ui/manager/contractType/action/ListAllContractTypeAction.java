package uo.ri.ui.manager.contractType.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que lista por pantalla todos los tipos de contratos
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListAllContractTypeAction implements Action {

    /**
     * Lista los tipos de contratos por consola
     */
    @Override
    public void execute() throws BusinessException {
	Console.println("\nList of contract types \n");

	Printer.printContractTypes(Factory.service.forContractTypeService()
		.findAllContractTypes());

    }

}
