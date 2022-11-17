package uo.ri.ui.manager.contract.action;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contract.ContractService;
import uo.ri.cws.application.service.contract.ContractService.ContractDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra un contrato dado un id
 * 
 * @author Carlos
 *
 */
public class ListContractByIdAction implements Action {

    private ContractService service = Factory.service.forContractService();

    /**
     * Encuentra un contrato dado un id de contrato pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String id = Console.readString("Type id");

	// Process
	Optional<ContractDto> opContract = service.findContractById(id);

	if (opContract.isPresent())
	    Printer.displayThisContractDetails(opContract.get());
	else
	    Console.println("There is no contract with this id");

    }

}
