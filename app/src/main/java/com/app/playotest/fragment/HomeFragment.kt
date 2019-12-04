package com.app.playotest.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.playotest.R
import com.app.playotest.adapter.NewsAdapter
import com.app.playotest.model.DataModel
import com.app.playotest.utils.Utility
import com.mytrax.webservice.ApiFactory
import com.mytrax.webservice.ApiRepository
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(), NewsAdapter.ItemClick {
    private var pageNo = 1
    private var query = ""
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var newsAdapter: NewsAdapter
    private var arrSearchResult: ArrayList<DataModel.HitModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        setAdapter(view)
        view.fragment_home_ibSearch.setOnClickListener {
            query = view.fragment_home_etSearch.text.toString()
            pageNo = 1
            if (isValid()) {
                if (arrSearchResult.isNotEmpty()) {
                    arrSearchResult.clear()
                }
                callNewsApi(query, pageNo)
                Utility.hideKeyboard(activity!!)
            }
        }

        return view
    }

    /**
     * This method is to set the adapter in recyler view
     */
    private fun setAdapter(view: View) {
        viewManager = LinearLayoutManager(activity)
        newsAdapter = NewsAdapter(activity!!, arrSearchResult, this)
        view.fragment_home_rvNewsList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = newsAdapter
        }

        view.fragment_home_rvNewsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if ((viewManager as LinearLayoutManager).findLastVisibleItemPosition() == viewManager.itemCount - 1) {
                        callNewsApi(query, ++pageNo)
                    }
                }
            }

        })
    }

    /**
     * This function is to call the news api
     */
    private fun callNewsApi(query: String, pageNo: Int) {
        if (!Utility.isNetworkAvailable(activity!!)) {
            Toast.makeText(
                activity!!,
                getString(R.string.alert_msg_no_internet),
                Toast.LENGTH_LONG
            ).show()
            return
        }
        Utility.showProgressBar(activity!!)
        val apiRepository: ApiRepository = ApiFactory.getClient().create(ApiRepository::class.java)
        val call: Call<DataModel.SearchResultModel> = apiRepository.getSearchResult(query, pageNo)
        call.enqueue(object : Callback<DataModel.SearchResultModel> {
            override fun onResponse(
                call: Call<DataModel.SearchResultModel>,
                response: Response<DataModel.SearchResultModel>
            ) {
                Utility.hideProgressBar()
                if (response.isSuccessful) {
                    val searchResponse: DataModel.SearchResultModel = response.body()!!
                    if (searchResponse.hits!!.isNotEmpty()) {
                        arrSearchResult.addAll(searchResponse.hits)
                        newsAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(
                        activity!!,
                        getString(R.string.alert_msg_something_went_worng),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<DataModel.SearchResultModel>, t: Throwable) {
                Utility.hideProgressBar()
                Toast.makeText(
                    activity!!,
                    getString(R.string.alert_msg_something_went_worng),
                    Toast.LENGTH_LONG
                ).show()
                call.cancel()
            }
        })


    }

    override fun onNewsClick(model: DataModel.HitModel, view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(intent)
    }

    /**
     * This method is to chekc if user has entered something or not
     */
    fun isValid(): Boolean {
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(
                activity!!,
                getString(R.string.alert_msg_enter_something_to_search),
                Toast.LENGTH_LONG
            ).show()
            return false
        } else {
            return true
        }
    }
}