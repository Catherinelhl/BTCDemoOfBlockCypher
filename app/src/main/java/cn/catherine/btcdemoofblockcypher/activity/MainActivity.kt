package cn.catherine.btcdemoofblockcypher.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import cn.catherine.btcdemoofblockcypher.R
import cn.catherine.btcdemoofblockcypher.constants.Constants
import cn.catherine.btcdemoofblockcypher.contract.MainContract
import cn.catherine.btcdemoofblockcypher.presenter.MainPresenterImp
import cn.catherine.btcdemoofblockcypher.tool.LogTool
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    companion object {
        private val TAG: String = MainActivity::class.simpleName!!
    }

    private val presenter: MainContract.Presenter = MainPresenterImp(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getBalance()
        tv_address.text = Constants.address
        et_amount.setText("0.00002")
        initView()
        initListener()
    }

    private fun initView() {
        btn_get_balance.setOnClickListener {
            presenter.getBalance()
        }
        btn_get_tx_list.setOnClickListener { presenter.getTransactionList() }
        btn_push.setOnClickListener {
            var amount = et_amount.text.toString()
            presenter.createTX(
                Constants.feeString,
                Constants.toAddress,
                amount
            )
        }
        btn_push_xiaodong.setOnClickListener { presenter.pushTX(null) }
        btn_get_tx_status.setOnClickListener { presenter.getTXInfoByHash("") }
    }

    private fun initListener() {}


    override fun getBalanceSuccess(balance: String?) {
        LogTool.d(Companion.TAG, balance)
        btn_get_balance.text = "Current balance:$balance"
    }

    override fun getBalanceFailure(info: String?) {
        LogTool.e(Companion.TAG, info)
        btn_get_balance.text = "Current balance:$info"

    }

    override fun success(info: String?) {
        LogTool.d(Companion.TAG, info)
        et_content.setText(info)

    }

    override fun failure(info: String?) {
        LogTool.e(Companion.TAG, info)
        et_content.setText(info)

    }

    override fun hashStatus(info: String?) {
        LogTool.d(Companion.TAG, info)

    }


}
