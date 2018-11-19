package cn.catherine.btcdemoofblockcypher.bean;

import java.io.Serializable;

/**
 * @projectName: BTCDemoOfBlockCypher
 * @packageName: cn.catherine.btcdemoofblockcypher.bean
 * @author: catherine
 * @time: 2018/11/19
 */
public class BalanceBean implements Serializable {
    private String address;
    private long total_received;
    private long total_sent;
    private long balance;
    private long unconfirmed_balance;
    private long final_balance;
    private int n_tx;
    private int unconfirmed_n_tx;
    private int final_n_tx;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTotal_received() {
        return total_received;
    }

    public void setTotal_received(long total_received) {
        this.total_received = total_received;
    }

    public long getTotal_sent() {
        return total_sent;
    }

    public void setTotal_sent(long total_sent) {
        this.total_sent = total_sent;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getUnconfirmed_balance() {
        return unconfirmed_balance;
    }

    public void setUnconfirmed_balance(long unconfirmed_balance) {
        this.unconfirmed_balance = unconfirmed_balance;
    }

    public long getFinal_balance() {
        return final_balance;
    }

    public void setFinal_balance(long final_balance) {
        this.final_balance = final_balance;
    }

    public int getN_tx() {
        return n_tx;
    }

    public void setN_tx(int n_tx) {
        this.n_tx = n_tx;
    }

    public int getUnconfirmed_n_tx() {
        return unconfirmed_n_tx;
    }

    public void setUnconfirmed_n_tx(int unconfirmed_n_tx) {
        this.unconfirmed_n_tx = unconfirmed_n_tx;
    }

    public int getFinal_n_tx() {
        return final_n_tx;
    }

    public void setFinal_n_tx(int final_n_tx) {
        this.final_n_tx = final_n_tx;
    }

    @Override
    public String toString() {
        return "BalanceBean{" +
                "address='" + address + '\'' +
                ", total_received=" + total_received +
                ", total_sent=" + total_sent +
                ", balance=" + balance +
                ", unconfirmed_balance=" + unconfirmed_balance +
                ", final_balance=" + final_balance +
                ", n_tx=" + n_tx +
                ", unconfirmed_n_tx=" + unconfirmed_n_tx +
                ", final_n_tx=" + final_n_tx +
                '}';
    }
}
