package uo.ri.ui.manager.payroll;

import uo.ri.util.menu.BaseMenu;
import uo.ri.util.menu.NotYetImplementedAction;

public class PayrollManagementMenu extends BaseMenu {

    /**
     * Menu que muestra las distintas operaciones que puedes hacer con las
     * nóminas
     */
    public PayrollManagementMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Payrolls management", null },

		{ "Generate payrolls", NotYetImplementedAction.class },
		{ "Delete last payroll for mechanic",
			NotYetImplementedAction.class },
		{ "Delete last payrolls", NotYetImplementedAction.class },
		{ "Display all payrolls", NotYetImplementedAction.class },
		{ "Display payroll in detail", NotYetImplementedAction.class },
		{ "Display payroll of a mechanic",
			NotYetImplementedAction.class },
		{ "Display payrolls of a professional group",
			NotYetImplementedAction.class } };
    }
}
