package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 14.09.2021.
 */
@Entity
@Table(name = "DOR_COURIER")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cr_id;
    private String login;
    private String password;
    private String fio;
    private String phone;
    private String blocked_status;

    public long getCr_id() {
        return cr_id;
    }

    public void setCr_id(long cr_id) {
        this.cr_id = cr_id;
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

    public String getBlocked_status() {
        return blocked_status;
    }

    public void setBlocked_status(String blocked_status) {
        this.blocked_status = blocked_status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
