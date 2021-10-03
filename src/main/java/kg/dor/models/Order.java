package kg.dor.models;

import javax.persistence.*;
import java.util.List;





@Entity
@Table(name = "DOR_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;
    private long cl_id;
    private String inp_date;



    @OneToMany(mappedBy = "v_order", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Product> products;
    private float full_amount;




    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public float getFull_amount() {
        return full_amount;
    }

    public void setFull_amount(float full_amount) {
        this.full_amount = full_amount;
    }

    public String getInp_date() {
        return inp_date;
    }

    public void setInp_date(String inp_date) {
        this.inp_date = inp_date;
    }

    public long getCl_id() {
        return cl_id;
    }

    public void setCl_id(long cl_id) {
        this.cl_id = cl_id;
    }
}
