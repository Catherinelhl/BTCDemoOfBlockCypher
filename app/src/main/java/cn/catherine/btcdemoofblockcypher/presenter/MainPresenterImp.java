package cn.catherine.btcdemoofblockcypher.presenter;

import android.text.TextUtils;
import cn.catherine.btcdemoofblockcypher.bean.BalanceBean;
import cn.catherine.btcdemoofblockcypher.bean.CreateTxBean;
import cn.catherine.btcdemoofblockcypher.bean.InputsBean;
import cn.catherine.btcdemoofblockcypher.bean.OutputsBean;
import cn.catherine.btcdemoofblockcypher.constants.Constants;
import cn.catherine.btcdemoofblockcypher.contract.MainContract;
import cn.catherine.btcdemoofblockcypher.interactor.MainInteractor;
import cn.catherine.btcdemoofblockcypher.tool.LogTool;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: BTCDemoOfBlockCypher
 * @packageName: cn.catherine.btcdemoofblockcypher.presenter
 * @author: catherine
 * @time: 2018/11/19
 */
public class MainPresenterImp implements MainContract.Presenter {

    private String TAG = MainPresenterImp.class.getSimpleName();
    private MainContract.View view;
    private MainInteractor interactor = new MainInteractor();

    public MainPresenterImp(MainContract.View view) {
        super();
        this.view = view;
    }

    @Override
    public void getBalance() {
        interactor.getBalance(Constants.address, new Callback<BalanceBean>() {
            @Override
            public void onResponse(Call<BalanceBean> call, Response<BalanceBean> response) {
                view.getBalanceSuccess(String.valueOf(response.body().getBalance()));
            }

            @Override
            public void onFailure(Call<BalanceBean> call, Throwable t) {
                view.getBalanceFailure(t.getCause().toString());
            }
        });
    }

    @Override
    public void getTransactionList() {
        interactor.getTXList(Constants.address, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                view.success(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.failure(t.getCause().toString());
            }
        });

    }

    @Override
    public void getUnspent(String amount, String address) {

    }

    @Override
    public void createTX(String feeString, String toAddress, String amountString) {
        long staoshi = 10000000;

        BigDecimal amount = new BigDecimal(amountString).multiply(new BigDecimal(staoshi));
        BigDecimal fee = new BigDecimal(feeString).multiply(new BigDecimal(staoshi));

        CreateTxBean createTxBean = new CreateTxBean();
        createTxBean.setFees(fee.longValue());


        List<String> addresses = new ArrayList<>();
        addresses.add(Constants.address);
        InputsBean inputsBean = new InputsBean(addresses);
        List<InputsBean> inputsBeans = new ArrayList<>();
        inputsBeans.add(inputsBean);
        createTxBean.setInputs(inputsBeans);
        List<String> addressesOutputs = new ArrayList<>();
        addressesOutputs.add(Constants.toAddress);
        OutputsBean outputsBean = new OutputsBean(addressesOutputs, amount.longValue());
        List<OutputsBean> outputsBeans = new ArrayList<>();
        outputsBeans.add(outputsBean);
        createTxBean.setOutputs(outputsBeans);
        LogTool.d(TAG, createTxBean);
        interactor.createTX(new Gson().toJson(createTxBean), new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseBody = response.body();
                if (TextUtils.isEmpty(responseBody)) {
                    view.failure("create TX failure");
                    return;
                }
                view.success(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.has("tosign")) {
                        String tosign = String.valueOf(jsonObject.get("tosign"));
                        pushTX(tosign);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogTool.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.failure(t.getCause().toString());
            }
        });
    }

    @Override
    public void getTXInfoByHash(String rawHash) {

    }

    @Override
    public void pushTX(String rawString) {
        if (TextUtils.isEmpty(rawString)) {
            view.failure("raw hash is empty!!");
            return;
        }
        //start to sign
        LogTool.d(TAG, rawString);
        interactor.pushTX(rawString, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                view.success(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.failure(t.getCause().toString());
            }
        });
    }

}
