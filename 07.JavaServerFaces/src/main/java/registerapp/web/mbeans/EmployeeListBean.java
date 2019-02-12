package registerapp.web.mbeans;

import org.modelmapper.ModelMapper;
import registerapp.domain.models.service.EmployeeServiceModel;
import registerapp.domain.models.view.EmployeeListViewModel;
import registerapp.domain.models.view.EmployeeViewModel;
import registerapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {

    private List<EmployeeListViewModel> employees;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListBean() {

    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeListViewModel> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }

    public BigDecimal getTotalMoney() {
        return this.employees
                .stream()
                .map(EmployeeListViewModel::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAverageSalary() {
        return this.employees.size() == 0 ? BigDecimal.ZERO
                : this.getTotalMoney().divide(new BigDecimal(this.employees.size()), RoundingMode.HALF_UP);
    }

}
