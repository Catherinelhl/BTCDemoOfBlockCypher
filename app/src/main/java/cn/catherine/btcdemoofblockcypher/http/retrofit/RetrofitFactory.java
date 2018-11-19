package cn.catherine.btcdemoofblockcypher.http.retrofit;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/20
 * <p>
 * Http：Retrofit封裝网络请求
 */
public class RetrofitFactory {

    private static Retrofit MainInstance;
    private static Retrofit TestInstance;
    private static OkHttpClient client;


    private static void initClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS)
                    .writeTimeout(3, TimeUnit.SECONDS)
                    .addInterceptor(new OkHttpInterceptor())
                    .build();
        }
    }

    public static Retrofit getInstance() {


        //BlockChain
//        测试：https://testnet.blockchain.info
//        主网：https://blockchain.info


        //BlockCypher
        //test https://api.blockcypher.com/v1/btc/test3
        //main https://api.blockcypher.com/v1/btc/main
        return getMainInstance("https://api.blockcypher.com/v1/btc/main/");
    }

    /**
     * SFN api
     *
     * @param baseUrl
     * @return
     */
    public static Retrofit getMainInstance(String baseUrl) {
        initClient();
        if (MainInstance == null) {
            MainInstance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(new StringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Observable，暂时没用
                    .build();
        }
        return MainInstance;
    }

    /**
     * AN api
     *
     * @param baseUrl
     * @return
     */
    public static Retrofit getTestInstance(String baseUrl) {
        initClient();
        TestInstance = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(new StringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Observable，暂时没用
                .build();
        return TestInstance;
    }


}
