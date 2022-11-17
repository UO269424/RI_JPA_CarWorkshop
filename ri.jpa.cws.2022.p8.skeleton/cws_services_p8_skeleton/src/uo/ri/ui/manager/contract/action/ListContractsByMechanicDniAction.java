package uo.ri.ui.manager.contract.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contract.ContractService.ContractSummaryDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra un contrato dado un id de mecanico
 * 
 * @author Carlos
 *
 */
public class ListContractsByMechanicDniAction implements Action {

    /**
     * Encuentra un contrato dado un id de mecanico pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String idM = Console.readString("Type mechanic dni ");

	// Process
	List<ContractSummaryDto> result = null;
	result = Factory.service.forContractService()
		.findContractsByMechanic(idM);

	// Print result
	if (!result.isEmpty())
	    Printer.displayAllContractsDetailsWithPayrolls(result);
	else
	    Console.println(
		    "There are no contracts for a mechanic with this dni");

    }

}
