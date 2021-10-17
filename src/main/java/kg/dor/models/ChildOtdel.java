package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 14.09.2021.
 */
@Entity
@Table(name = "DOR_OTDEL_CHILD")
public class ChildOtdel {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long child_otdel_id;
    private long parent_otdel;
    private String name;

    public long getParent_otdel() {
        return parent_otdel;
    }

    public void setParent_otdel(long parent_otdel) {
        this.parent_otdel = parent_otdel;
    }

    public long getChild_otdel_id() {
        return child_otdel_id;
    }

    public void setChild_otdel_id(long child_otdel_id) {
        this.child_otdel_id = child_otdel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
