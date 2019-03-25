package chushka.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    private Product product;
    private User client;
    private LocalDateTime orderedOn;

    public Order(){}

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public User getClient() {
        return this.client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Column(name = "ordered_on", nullable = false)
    public LocalDateTime getOrderedOn() {
        return this.orderedOn;
    }

    public void setOrderedOn(LocalDateTime orderedOn) {
        this.orderedOn = orderedOn;
    }
}
