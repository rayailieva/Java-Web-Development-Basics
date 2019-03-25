package chushka.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    private String name;
    private BigDecimal price;
    private String description;
    private Type type;

    public Product(){}

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
