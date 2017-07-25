package com.marvin.splashedink.ui.kotlin

import io.reactivex.Observable

/**
 * Created by Administrator on 2017/7/25.
 */
class KotlinModel {
    fun getMessage(): Observable<String> {
        return Observable.create {
            source ->
            source.onNext("HELLO KOTLIN!")
            source.onComplete()
        }
    }
}