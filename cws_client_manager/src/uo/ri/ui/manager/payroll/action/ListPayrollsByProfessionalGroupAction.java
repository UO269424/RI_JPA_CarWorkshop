package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra las nóminas para todos los mecánicos de un grupo
 * porfesional
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListPayrollsByProfessionalGroupAction implements Action {

    /**
     * Encuentra las nóminas de los mecánicos del grupo porfesional cuyo nombre
     * es pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String name = Console.readString("Professional group  ");

	// Process

	List<PayrollSummaryBLDto> result = Factory.service.forPayrollService()
		.getAllPayrollsForProfessionalGroup(name);

	// Print result
	Console.println(
		String.format("Payrolls for professional group %s", name));
	Printer.printPayrollsSumary(result);

    }

}
