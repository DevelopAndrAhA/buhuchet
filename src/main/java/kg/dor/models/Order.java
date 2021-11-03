package kg.dor.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;





@Entity
@Table(name = "DOR_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;
    private long cl_id;
    private String cl_fio;
    private Date inp_date;
    private Date return_cos_date;



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

    public Date getInp_date() {
        return inp_date;
    }

    public void setInp_date(Date inp_date) {
        this.inp_date = inp_date;
    }

    public long getCl_id() {
        return cl_id;
    }

    public void setCl_id(long cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_fio() {
        return cl_fio;
    }

    public void setCl_fio(String cl_fio) {
        this.cl_fio = cl_fio;
    }

    public Date getReturn_cos_date() {
        return return_cos_date;
    }

    public void setReturn_cos_date(Date return_cos_date) {
        this.return_cos_date = return_cos_date;
    }
}
