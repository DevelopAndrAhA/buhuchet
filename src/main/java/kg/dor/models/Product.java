package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 14.09.2021.
 */
@Entity
@Table(name = "DOR_PRODUCT")
public class Product {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long product_id;
    private String name;
    private String prod_desc;
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order v_order;


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

    public Order getV_order() {
        return v_order;
    }

    public void setV_order(Order v_order) {
        this.v_order = v_order;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }
}
