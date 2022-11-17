package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que genera las n�minas de todos los mec�nicos para el mes actual
 * 
 * @author Alonso Gago Su�rez - UO269424
 *
 */
public class GeneratePayrollsAction implements Action {
    private PayrollService ps = Factory.service.forPayrollService();

    /**
     * Genera las n�minas del mes actual para todos los mec�nicos con contrato
     * en activo o cuyo contrato finaliza en dicho mes.
     */
    @Override
    public void execute() throws BusinessException {
	// Process

	ps.generatePayrolls();

	// Print result
	Console.printf("Generated new payrolls");
    }

}
