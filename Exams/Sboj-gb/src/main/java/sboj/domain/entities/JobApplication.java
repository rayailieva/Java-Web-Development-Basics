package sboj.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {

    private String sector;
    private String profession;
    private BigDecimal salary;
    private String description;

    public JobApplication() {
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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
}
