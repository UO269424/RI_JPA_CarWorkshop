package uo.ri.cws.application.service.contracttype.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contracttype.ContractTypeService;
import uo.ri.cws.application.service.contracttype.crud.command.AddContractTypeTS;
import uo.ri.cws.application.service.contracttype.crud.command.DeleteContractTypeTS;
import uo.ri.cws.application.service.contracttype.crud.command.FindAllContractTypesTS;
import uo.ri.cws.application.service.contracttype.crud.command.FindContractTypeByNameTS;
import uo.ri.cws.application.service.contracttype.crud.command.UpdateContractTypeTS;
import uo.ri.cws.application.util.command.CommandExecutor;

public class ContractTypeServiceImpl implements ContractTypeService {

    private final CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public ContractTypeDto addContractType(ContractTypeDto dto)
	    throws BusinessException {
	return executor.execute(new AddContractTypeTS(dto));
    }

    @Override
    public void deleteContractType(String name) throws BusinessException {
	executor.execute(new DeleteContractTypeTS(name));

    }

    @Override
    public void updateContractType(ContractTypeDto dto)
	    throws BusinessException {
	executor.execute(new UpdateContractTypeTS(dto));

    }

    @Override
    public Optional<ContractTypeDto> findContractTypeByName(String name)
	    throws BusinessException {
	return executor.execute(new FindContractTypeByNameTS(name));
    }

    @Override
    public List<ContractTypeDto> findAllContractTypes()
	    throws BusinessException {
	return executor.execute(new FindAllContractTypesTS());
    }

}
