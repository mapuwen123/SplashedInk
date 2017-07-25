package com.marvin.splashedink.ui.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import butterknife.BindView
import com.marvin.splashedink.R
import com.marvin.splashedink.base.BaseActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : BaseActivity<KotlinView, KotlinPresenter>(), KotlinView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_kotlin
    }

    override fun initPresenter(): KotlinPresenter {
        return KotlinPresenter()
    }

    override fun actionbarInit() {
        Logger.i("actionbarInit")
    }

    override fun dataInit() {
        Logger.i("dataInit")
    }

    override fun eventInit() {
        btn.setOnClickListener(this)
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun error(err: String?) {
        showToast(err)
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btn -> presenter.getMessage()
        }
    }
}
