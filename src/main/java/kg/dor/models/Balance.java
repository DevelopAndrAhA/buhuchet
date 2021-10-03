package kg.dor.models;

import javax.persistence.*;

/**
 * Created by altynbek.kochkonbaev on 21.09.2021.
 */
@Entity
@Table(name = "DOR_BALANCE")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long balance_id;
    private long balance_sum;


    public long getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(long balance_id) {
        this.balance_id = balance_id;
    }

    public long getBalance_sum() {
        return balance_sum;
    }

    public void setBalance_sum(long balance_sum) {
        this.balance_sum = balance_sum;
    }
}
