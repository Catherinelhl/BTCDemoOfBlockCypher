package cn.catherine.btcdemoofblockcypher.contract;

import cn.catherine.btcdemoofblockcypher.bean.SendBean;

import java.util.List;

/**
 * @projectName: BTCDemoOfBlockCypher
 * @packageName: cn.catherine.btcdemoofblockcypher.contract
 * @author: catherine
 * @time: 2018/11/19
 */
public interface MainContract {

    interface View {
        void getBalanceSuccess(String balance);

        void getBalanceFailure(String info);

        void success(String info);

        void failure(String info);

        void hashStatus(String info);

    }

    interface Presenter {

        void getBalance();

        void getTransactionList();

        void getUnspent(String amount,String address);

        void createTX(String feeString, String toAddress, String amountString);
        void getTXInfoByHash(String rawHash);

        void pushTX( SendBean sendBean);
    }
}
