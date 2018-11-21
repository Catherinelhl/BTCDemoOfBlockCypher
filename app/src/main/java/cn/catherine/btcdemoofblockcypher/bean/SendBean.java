package cn.catherine.btcdemoofblockcypher.bean;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * @author catherine.brainwilliam
 * @since 2018/11/20
 */
public class SendBean implements Serializable {
    // 创建交易返回的数据
    private JSONObject tx;
    private List<String> tosign;
    // tosign 参数数据用私钥签名后的字符串
    private List<String> signatures;
    private List<String> pubkeys;

    public JSONObject getTx() {
        return tx;
    }

    public void setTx(JSONObject tx) {
        this.tx = tx;
    }

    @Override
    public String toString() {
        return "SendBean{" +
                "tx=" + tx +
                ", tosign=" + tosign +
                ", signatures=" + signatures +
                ", pubkeys=" + pubkeys +
                '}';
    }

    public List<String> getTosign() {
        return tosign;
    }

    public void setTosign(List<String> tosign) {
        this.tosign = tosign;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<String> signatures) {
        this.signatures = signatures;
    }

    public List<String> getPubkeys() {
        return pubkeys;
    }

    public void setPubkeys(List<String> pubkeys) {
        this.pubkeys = pubkeys;
    }

}
