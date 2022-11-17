package uo.ri.ui.manager;

import uo.ri.ui.manager.contract.ContractsMenu;
import uo.ri.ui.manager.contractType.ContractTypeMenu;
import uo.ri.ui.manager.mechanic.MechanicsMenu;
import uo.ri.ui.manager.payroll.PayrollManagementMenu;
import uo.ri.ui.manager.professionalGroup.ProfessionalGroupMenu;
import uo.ri.ui.manager.sparepart.SparepartsMenu;
import uo.ri.ui.manager.vehicletype.VehicleTypesMenu;
import uo.ri.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {
    {
	menuOptions = new Object[][] { { "Manager", null },
		{ "Contracts management", ContractsMenu.class },
		{ "Contract Types management", ContractTypeMenu.class },
		{ "Mechanics management", MechanicsMenu.class },
		{ "Payrolls management", PayrollManagementMenu.class },
		{ "Professional group management",
			ProfessionalGroupMenu.class },
		{ "Spareparts management", SparepartsMenu.class },
		{ "Vehicle types management", VehicleTypesMenu.class }, };
    }
}