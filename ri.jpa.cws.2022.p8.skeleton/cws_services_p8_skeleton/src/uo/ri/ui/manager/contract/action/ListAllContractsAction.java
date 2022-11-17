package uo.ri.ui.manager.contract.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contract.ContractService.ContractSummaryDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que lista por pantalla los contratos dado un id
 * 
 * @author Carlos
 *
 */
public class ListAllContractsAction implements Action {

    /**
     * Lista los contratos por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	Console.println("\nPrinting contracts summary\n");
	List<ContractSummaryDto> lc = null;

	// Process
	lc = Factory.service.forContractService().findAllContracts();

	// Print the results
	if (lc.isEmpty())
	    Console.print("There is no Contract ");
	else {
	    for (ContractSummaryDto c : lc) {
		Printer.displayThisContractDetailsWithPayrolls(c);

	    }
	}

    }

}
