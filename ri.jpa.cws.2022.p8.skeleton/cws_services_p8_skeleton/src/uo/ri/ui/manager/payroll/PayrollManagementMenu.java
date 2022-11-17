package uo.ri.ui.manager.payroll;

import uo.ri.ui.manager.payroll.action.DeleteLastPayrollAction;
import uo.ri.ui.manager.payroll.action.DeletePayrollForMechanicAction;
import uo.ri.ui.manager.payroll.action.GeneratePayrollsAction;
import uo.ri.ui.manager.payroll.action.ListAllPayrollsAction;
import uo.ri.ui.manager.payroll.action.ListPayrollByMechanicAction;
import uo.ri.ui.manager.payroll.action.ListPayrollDetailAction;
import uo.ri.ui.manager.payroll.action.ListPayrollsByProfessionalGroupAction;
import uo.ri.util.menu.BaseMenu;

public class PayrollManagementMenu extends BaseMenu {

    /**
     * Menu que muestra las distintas operaciones que puedes hacer con las
     * nóminas
     */
    public PayrollManagementMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Payrolls management", null },

		{ "Generate payrolls", GeneratePayrollsAction.class },
		{ "Delete last payroll for mechanic",
			DeletePayrollForMechanicAction.class },
		{ "Delete last payrolls", DeleteLastPayrollAction.class },
		{ "Display all payrolls", ListAllPayrollsAction.class },
		{ "Display payroll in detail", ListPayrollDetailAction.class },
		{ "Display payroll of a mechanic",
			ListPayrollByMechanicAction.class },
		{ "Display payrolls of a professional group",
			ListPayrollsByProfessionalGroupAction.class } };
    }
}
