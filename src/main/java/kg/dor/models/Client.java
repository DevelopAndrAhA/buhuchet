package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 14.09.2021.
 */
@Entity
@Table(name = "DOR_CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cl_id;
    private String fio;
    private String phone;
    private long otdel_id;


    public long getCl_id() {
        return cl_id;
    }

    public void setCl_id(long cl_id) {
        this.cl_id = cl_id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getOtdel_id() {
        return otdel_id;
    }

    public void setOtdel_id(long otdel_id) {
        this.otdel_id = otdel_id;
    }




}
