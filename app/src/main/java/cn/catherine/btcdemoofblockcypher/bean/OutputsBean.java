package cn.catherine.btcdemoofblockcypher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: BTCDemoOfBlockCypher
 * @packageName: cn.catherine.btcdemoofblockcypher.bean
 * @author: catherine
 * @time: 2018/11/19
 */
public class OutputsBean implements Serializable {
    private List<String> addresses;

    private long value;

    public OutputsBean(List<String> addresses) {
        this.addresses = addresses;
    }

    public OutputsBean(List<String> addresses, long value) {
        this.addresses = addresses;
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "OutputsBean{" +
                "addresses=" + addresses +
                ", value=" + value +
                '}';
    }
}
