package registerapp.service;

import org.modelmapper.ModelMapper;
import registerapp.domain.entities.Employee;
import registerapp.domain.models.service.EmployeeServiceModel;
import registerapp.repository.EmployeeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
      try {
          this.employeeRepository.save(
                  this.modelMapper.map(employeeServiceModel, Employee.class));
      }catch (Exception e){
          return false;
      }
      return true;
    }

    @Override
    public List<EmployeeServiceModel> findAllEmployees() {
        return this.employeeRepository.findAll()
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeEmployee(String id) {
        try {
            this.employeeRepository.remove(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
