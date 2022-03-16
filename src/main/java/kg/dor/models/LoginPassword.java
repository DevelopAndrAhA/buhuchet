package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 27.09.2021.
 */
@Entity
@Table(name = "LoginPassword")
public class LoginPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ad_id;
    private String type;
    private String login;
    private String password;

    public long getAd_id() {
        return ad_id;
    }

    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
