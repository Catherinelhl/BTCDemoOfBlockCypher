package cn.catherine.btcdemoofblockcypher.presenter;

import android.text.TextUtils;
import cn.catherine.btcdemoofblockcypher.bean.*;
import cn.catherine.btcdemoofblockcypher.constants.Constants;
import cn.catherine.btcdemoofblockcypher.contract.MainContract;
import cn.catherine.btcdemoofblockcypher.interactor.MainInteractor;
import cn.catherine.btcdemoofblockcypher.tool.LogTool;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.json.JSONArray;
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
                view.getBalanceSuccess(String.valueOf(response.body().getFinal_balance()));
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
        long staoshi = 100000000;

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
                    JSONObject jsonObject = new JSONObject(responseBody);
                    JSONObject jsonObjectTX = (JSONObject) jsonObject.get("tx");
                    if (jsonObject.has("tosign")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("tosign");
                        List<String> list = new ArrayList<String>();
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                list.add(jsonArray.getString(i));
                            }
                        }
                        SendBean sendBean = new SendBean();
                        sendBean.setTx(jsonObjectTX);
                        sendBean.setTosign(list);
                        pushTX(sendBean);

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
    public void pushTX(SendBean sendBean) {
        if (sendBean == null) {
            view.failure("raw hash is empty!!");
            return;
        }
        List<String> allSign = sendBean.getTosign();
        List<String> publicKey = new ArrayList<>();
        List<String> signature = new ArrayList<>();
        for (String s : allSign) {
            LogTool.d(TAG, s);
            Sha256Hash sha256Hash = new Sha256Hash(Utils.parseAsHexOrBase58(s));
            TransactionOutPoint outPoint = new TransactionOutPoint(MainNetParams.get(),
                   1, sha256Hash);
            Script script = new Script(Utils.parseAsHexOrBase58("pay-to-script-hash"));
            LogTool.d(TAG, "addSignedInput getTxid:" +s);
//                DeterministicKey deterministicKey = KeyStorage.getInstance().getBtcDeterministicKeyBySeedAndAddress(mSeed);
            transaction.addSignedInput(outPoint, script, Constants.privateWIFKey, Transaction.SigHash.ALL, true);
            Sha256Hash hash = Transaction.hashForSignature(0, script,  Transaction.SigHash.ALL, true);
            ECKey.ECDSASignature ecSig = sigKey.sign(hash);
            publicKey.add(Constants.publicKey);
            signature.add(s);
        }
        sendBean.setPubkeys(publicKey);
        sendBean.setSignatures(signature);


        //start to sign
        interactor.pushTX(new Gson().toJson(sendBean), new Callback<String>() {
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
