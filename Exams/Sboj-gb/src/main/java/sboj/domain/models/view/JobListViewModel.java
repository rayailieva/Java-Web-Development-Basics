package sboj.domain.models.view;

import java.math.BigDecimal;

public class JobListViewModel {

    private String id;
    private String sector;
    private String profession;
    private BigDecimal salary;
    private String description;

    public JobListViewModel() {
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
