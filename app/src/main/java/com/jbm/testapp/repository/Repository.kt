package com.jbm.testapp.repository

import android.util.Log
import com.jbm.testapp.repository.network.APIClient
import com.jbm.testapp.repository.network.APIInterface
import com.jbm.testapp.repository.storage.Fruit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


object Repository {

    private val fruitsService: APIInterface = APIClient.client

    fun getFruitDataList(handleResults: (List<Fruit>) -> Unit, handleError: (Throwable) -> Unit) {
        var disposable: Disposable? = null
        disposable = fruitsService.getFruitData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleResults(it)
                disposable = null
            }, {
                handleError(it)
                disposable = null
            })
    }

}