package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 14.09.2021.
 */
@Entity
@Table(name = "DOR_PURCHProduct")
public class PProduct {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long product_id;
    private String name;
    private String prod_desc;
    private float price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase v_purchase;


    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    public Purchase getV_purchase() {
        return v_purchase;
    }

    public void setV_purchase(Purchase v_purchase) {
        this.v_purchase = v_purchase;
    }
}
