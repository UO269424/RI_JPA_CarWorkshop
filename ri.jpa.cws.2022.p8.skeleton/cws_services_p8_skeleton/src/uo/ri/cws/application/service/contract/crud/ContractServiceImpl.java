package uo.ri.cws.application.service.contract.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contract.ContractService;
import uo.ri.cws.application.service.contract.crud.command.FindAllContractsTS;
import uo.ri.cws.application.util.command.CommandExecutor;

public class ContractServiceImpl implements ContractService {

    private final CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public ContractDto addContract(ContractDto c) throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateContract(ContractDto dto) throws BusinessException {
	// TODO Auto-generated method stub

    }

    @Override
    public void deleteContract(String id) throws BusinessException {
	// TODO Auto-generated method stub

    }

    @Override
    public void terminateContract(String contractId) throws BusinessException {
	// TODO Auto-generated method stub

    }

    @Override
    public Optional<ContractDto> findContractById(String id)
	    throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<ContractSummaryDto> findContractsByMechanic(String mechanicDni)
	    throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<ContractSummaryDto> findAllContracts()
	    throws BusinessException {
	return executor.execute(new FindAllContractsTS());
    }

}
