package com.mytrax.webservice

import com.app.playotest.model.DataModel
import com.app.playotest.webservice.WsConstants

import retrofit2.Call
import retrofit2.http.*

/**
 * Retrofit callback methods.
 */

interface ApiRepository {

    @GET(WsConstants.METHOD_SEARCH)
    fun getSearchResult(
        @Query(WsConstants.PARAM_QUERY) query: String,
        @Query(WsConstants.PARAM_PAGE) pageNo: Int
    ): Call<DataModel.SearchResultModel>

}
