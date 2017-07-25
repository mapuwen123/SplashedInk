package com.marvin.splashedink.ui.kotlin

import com.marvin.splashedink.base.BasePresenter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Administrator on 2017/7/25.
 */
class KotlinPresenter: BasePresenter<KotlinView>(), Observer<String> {
    val model: KotlinModel = KotlinModel()

    fun getMessage() {
        model.getMessage()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    lateinit var disposable: Disposable

    override fun onNext(t: String) {
        mView.showMessage(t)
    }

    override fun onComplete() {
        disposable.dispose()
    }

    override fun onError(e: Throwable) {
        mView.error(e.message)
        disposable.dispose()
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }
}