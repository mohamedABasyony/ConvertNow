package com.basyony.convertnow.presentation.bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.basyony.convertnow.entities.*
import com.basyony.convertnow.presentation.MainActivity


abstract class BaseFragment : Fragment() {

    protected abstract val viewID: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(viewID, container, false)


    /**
     * Handling errors, generic default handling of errors
     * Can be overwrite it into child fragments
     * @param appError : backend throwable mapped to application types of errors
     */
    fun handleError(appError: AppError) {
        when (appError) {
            is NoInternet -> {
            }
            is TimeOut -> {

            }
            is Business -> {

            }
            is ServerDown -> {

            }
        }
    }

    protected fun hideLoading() {
        activity.let { it as MainActivity }
            .hideLoading()
    }

    protected fun showLoading() {
        activity.let { it as MainActivity }
            .showLoading()
    }

}