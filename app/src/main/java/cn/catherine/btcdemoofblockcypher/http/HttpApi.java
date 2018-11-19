package cn.catherine.btcdemoofblockcypher.http;

import cn.catherine.btcdemoofblockcypher.bean.BalanceBean;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface HttpApi {

    /*获取当前钱包的余额*/
    @GET("addrs/{address}/balance")
    Call<BalanceBean> getBalance(@Path("address") String address);
//
//    /*获取当前钱包的未交易区块*/
//    @GET("/unspent")
//    Call<String> getUnspentTransactionOutputs(@Query("active") String address);

    /*创建交易*/
    @POST("txs/new")
    Call<String> createTX(@Body RequestBody requestBody);


    /*发送交易*/
    @POST("txs/send")
    Call<String> publishTX(@Body RequestBody requestBody);

    /*获取交易记录*/
    @GET("addrs/{address}/full")
    Call<String> getTransactionList(@Path("address") String address);
}
