package uo.ri.ui.manager.payroll.action;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra una nómina dado su id
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListPayrollDetailAction implements Action {

    /**
     * Encuentra una nóminadado su id pasado por consola
     */
    @Override
    public void execute() throws BusinessException {
	// Get info
	String id = Console.readString("Payroll id  ");

	// Process
	Optional<PayrollBLDto> result = Factory.service.forPayrollService()
		.getPayrollDetails(id);

	// Print result
	Console.println(String.format("Details Payroll %s", id));
	Printer.printPayrolls(result);
    }

}
