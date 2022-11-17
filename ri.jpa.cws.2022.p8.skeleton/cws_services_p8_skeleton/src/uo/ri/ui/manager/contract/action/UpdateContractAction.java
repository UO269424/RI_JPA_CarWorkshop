package uo.ri.ui.manager.contract.action;

import java.time.LocalDate;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contract.ContractService.ContractDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que actualiza un contrato dado su id
 * 
 * @author Carlos
 *
 */
public class UpdateContractAction implements Action {

    /**
     * Actualiza un contrato cuyo id es pasado por consola, el contrato es
     * actualizado con una fecha de finalizacion y un salario pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	Printer.displayAllContracts();
	String id = Console.readString("Type contract id ");
	LocalDate endD = Console.readDate("Type contract start date ");
	double salary = Console.readDouble("Type annual base wage ");

	// Process
	ContractDto cdto = new ContractDto();
	cdto.id = id;
	cdto.endDate = endD;
	cdto.annualBaseWage = salary;

	Factory.service.forContractService().updateContract(cdto);

	// Print result
	Console.println("Contract updated");

    }

}
