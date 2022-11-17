package uo.ri.ui.manager.contract;

import uo.ri.util.menu.BaseMenu;
import uo.ri.util.menu.NotYetImplementedAction;

/**
 * Clase que muestra por pantalla las distintas operaciones que puedes hacer con
 * los contratos
 * 
 * @author Carlos
 *
 */
public class ContractsMenu extends BaseMenu {

    /**
     * Menu que muestra las distintas operaciones que puedes hacer con los
     * contratos
     */
    public ContractsMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Contracts management", null },

		{ "Create contract", NotYetImplementedAction.class },
		{ "Update contract", NotYetImplementedAction.class },
		{ "Delete contract", NotYetImplementedAction.class },
		{ "Display all contracts", NotYetImplementedAction.class },
		{ "Display contract by mechanic",
			NotYetImplementedAction.class },
		{ "Display contract by id", NotYetImplementedAction.class } };
    }
}
