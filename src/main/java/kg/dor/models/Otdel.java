package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 14.09.2021.
 */
@Entity
@Table(name = "DOR_OTDEL")
public class Otdel {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long otdel_id;
    private String name;


    public long getOtdel_id() {
        return otdel_id;
    }

    public void setOtdel_id(long otdel_id) {
        this.otdel_id = otdel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
