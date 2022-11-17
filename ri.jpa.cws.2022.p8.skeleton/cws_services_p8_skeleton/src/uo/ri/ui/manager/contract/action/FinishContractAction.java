package uo.ri.ui.manager.contract.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contract.ContractService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Class to terminate a contract with an id
 * 
 * @author
 *
 */
public class FinishContractAction implements Action {

    private ContractService service = Factory.service.forContractService();

    /**
     * Finaliza el contrato cuyo id es pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String id = Console.readString("Type contract identifier");

	// Process
	service.terminateContract(id);

	// Print the result
	Console.println("Contract is terminated");

    }

}
