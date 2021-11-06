package kg.dor.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Altynbek on 05.11.2021.
 */
@Entity
@Table(name = "DOR_Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long p_id;

    private float all_price;
    private Date inp_date;
    @OneToMany(mappedBy = "v_purchase", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PProduct> pProducts;

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }


    public float getAll_price() {
        return all_price;
    }

    public void setAll_price(float all_price) {
        this.all_price = all_price;
    }

    public Date getInp_date() {
        return inp_date;
    }

    public void setInp_date(Date inp_date) {
        this.inp_date = inp_date;
    }

    public List<PProduct> getpProducts() {
        return pProducts;
    }

    public void setpProducts(List<PProduct> pProducts) {
        this.pProducts = pProducts;
    }
}
