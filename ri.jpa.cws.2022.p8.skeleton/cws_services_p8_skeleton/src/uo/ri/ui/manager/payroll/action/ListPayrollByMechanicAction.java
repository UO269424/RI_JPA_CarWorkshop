package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra las nóminas de un mecánico dado su dni
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListPayrollByMechanicAction implements Action {

    /**
     * Encuentra las nóminas de un mecánico dado su dni pasado por consola
     */
    @Override
    public void execute() throws BusinessException {
	// Get info
	String id = Console.readString("Mechanic id  ");

	// Process
	List<PayrollSummaryBLDto> result = Factory.service.forPayrollService()
		.getAllPayrollsForMechanic(id);

	// Print result
	Console.println(String.format("Payrolls for mechanic %s", id));
	Printer.printPayrollsSumary(result);

    }

}
