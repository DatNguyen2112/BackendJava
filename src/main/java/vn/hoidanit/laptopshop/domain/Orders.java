package vn.hoidanit.laptopshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double totalPrice;

    // Nhiều đơn hàng sẽ thuộc 1 người dùng - many orders to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    // Nhiều dòng order_detail sẽ thuộc 1 order
    @OneToMany(mappedBy = "orders")
    List<OrderDetail> orderDetail;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", totalPrice=" + totalPrice + "]";
    }

}
