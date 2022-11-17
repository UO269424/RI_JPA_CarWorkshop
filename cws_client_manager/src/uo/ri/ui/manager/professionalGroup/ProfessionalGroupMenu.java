package uo.ri.ui.manager.professionalGroup;

import uo.ri.ui.manager.professionalGroup.action.AddProfessionalGroupAction;
import uo.ri.ui.manager.professionalGroup.action.DeleteProfessionalGroupAction;
import uo.ri.ui.manager.professionalGroup.action.ListAllProfessionalGroupAction;
import uo.ri.ui.manager.professionalGroup.action.ListProfessionalGroupByNameAction;
import uo.ri.ui.manager.professionalGroup.action.UpdateProfessionalGroupAction;
import uo.ri.util.menu.BaseMenu;

public class ProfessionalGroupMenu extends BaseMenu {

    public ProfessionalGroupMenu() {
	menuOptions = new Object[][] {
		{ "Manager > Professional Group management", null },

		{ "Add Professional Group ", AddProfessionalGroupAction.class },
		{ "Update Professional Group ", UpdateProfessionalGroupAction.class },
		{ "Delete Professional Group ", DeleteProfessionalGroupAction.class },
		{ "List all Professional Group ",
			ListAllProfessionalGroupAction.class },
		{ "List Professional Group By name ",
			ListProfessionalGroupByNameAction.class }, };
    }

}
