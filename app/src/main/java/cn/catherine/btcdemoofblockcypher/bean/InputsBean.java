package cn.catherine.btcdemoofblockcypher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: BTCDemoOfBlockCypher
 * @packageName: cn.catherine.btcdemoofblockcypher.bean
 * @author: catherine
 * @time: 2018/11/19
 */
public class InputsBean implements Serializable {

    private List<String> addresses;

    public InputsBean(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "InputsBean{" +
                "addresses=" + addresses +
                '}';
    }
}
