package com.jbm.testapp.repository

import com.jbm.testapp.repository.network.APIClient
import com.jbm.testapp.repository.network.APIInterface
import com.jbm.testapp.repository.storage.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


object Repository {

    private val countriesService: APIInterface = APIClient.client

    fun getCountryDataList(handleResults: (List<Country>) -> Unit, handleError: (Throwable) -> Unit) {
        var disposable: Disposable? = null
        disposable = countriesService.getCountryData()
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