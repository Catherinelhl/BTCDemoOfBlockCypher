package cn.catherine.btcdemoofblockcypher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: BTCDemoOfBlockCypher
 * @packageName: cn.catherine.btcdemoofblockcypher.bean
 * @author: catherine
 * @time: 2018/11/19
 */
public class CreateTxBean implements Serializable {

    private long fees;
    private List<InputsBean> inputs;
    private List<OutputsBean> outputs;

    public List<InputsBean> getInputs() {
        return inputs;
    }

    public void setInputs(List<InputsBean> inputs) {
        this.inputs = inputs;
    }

    public List<OutputsBean> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<OutputsBean> outputs) {
        this.outputs = outputs;
    }

    public long getFees() {
        return fees;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "CreateTxBean{" +
                "fees=" + fees +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                '}';
    }
}
