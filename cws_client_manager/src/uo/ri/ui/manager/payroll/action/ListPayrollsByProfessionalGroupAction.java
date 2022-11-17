package uo.ri.ui.manager.payroll.action;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService.PayrollSummaryBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra las n�minas para todos los mec�nicos de un grupo
 * porfesional
 * 
 * @author Alonso Gago Su�rez - UO269424
 *
 */
public class ListPayrollsByProfessionalGroupAction implements Action {

    /**
     * Encuentra las n�minas de los mec�nicos del grupo porfesional cuyo nombre
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
