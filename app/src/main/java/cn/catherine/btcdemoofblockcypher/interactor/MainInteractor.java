package cn.catherine.btcdemoofblockcypher.interactor;

import cn.catherine.btcdemoofblockcypher.bean.BalanceBean;
import cn.catherine.btcdemoofblockcypher.http.HttpApi;
import cn.catherine.btcdemoofblockcypher.http.retrofit.RetrofitFactory;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @projectName: BTCDemoOfBlockCypher
 * @packageName: cn.catherine.btcdemoofblockcypher.interactor
 * @author: catherine
 * @time: 2018/11/19
 */
public class MainInteractor {

    public void getBalance(String address, Callback<BalanceBean> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getInstance().create(HttpApi.class);
        Call<BalanceBean> call = httpApi.getBalance(address);
        call.enqueue(callBackListener);
    }

    public void getTXList(String address, Callback<String> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getInstance().create(HttpApi.class);
        Call<String> call = httpApi.getTransactionList(address);
        call.enqueue(callBackListener);
    }

    public void createTX(String json, Callback<String> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getInstance().create(HttpApi.class);
        RequestBody body =RequestBody.create(MediaType.parse("application/json"), json);
        Call<String> call = httpApi.createTX(body);
        call.enqueue(callBackListener);
    }

    public void pushTX(String rawHash, Callback<String> callBackListener) {
        HttpApi httpApi = RetrofitFactory.getInstance().create(HttpApi.class);
        RequestBody body =RequestBody.create(MediaType.parse("application/json"), rawHash);
        Call<String> call = httpApi.publishTX(body);
        call.enqueue(callBackListener);
    }
}
