package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 21.09.2021.
 */
@Entity
@Table(name = "DOR_DOLG")
public class Dolg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dolg_id;
    private long cl_id;
    private long amount;


    public long getDolg_id() {
        return dolg_id;
    }

    public void setDolg_id(long dolg_id) {
        this.dolg_id = dolg_id;
    }

    public long getCl_id() {
        return cl_id;
    }

    public void setCl_id(long cl_id) {
        this.cl_id = cl_id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
