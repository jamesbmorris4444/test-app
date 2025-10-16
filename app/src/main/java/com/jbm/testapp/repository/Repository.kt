package com.jbm.testapp.repository


object Repository {



//    fun getCountryDataList(handleResults: (List<Country>) -> Unit, handleError: (Throwable) -> Unit) {
//        var disposable: Disposable? = null
//        disposable = countriesService.getCountryData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                handleResults(it)
//                disposable = null
//            }, {
//                handleError(it)
//                disposable = null
//            })
//    }

//    suspend fun getCountryDataList(handleResults: (List<Country>) -> Unit, handleError: (Throwable) -> Unit) {
//        try {
//            handleResults(countriesService.getCountryData())
//        } catch (e: Exception) {
//            handleError(e)
//        }
//    }

}