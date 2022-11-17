package uo.ri.ui.manager.contract.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que borra un contrato
 * 
 * @author Carlos
 *
 */
public class DeleteContractAction implements Action {

    /**
     * Borra el contrato dado un id pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String idContract = Console.readString("Contract identifier ");

	// Process
	Factory.service.forContractService().deleteContract(idContract);

	// Print result
	Console.println("Contract no longer exists");

    }
}
