package com.tosmart.launcher.ez_subtitle_subdl.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.tosmart.launcher.ez_subtitle_subdl.R

/**
 * @author wyq
 * @date 2024/4/25 12:05
 * @Description
 */
open abstract class BaseDialogFragment<Binding : ViewDataBinding> : DialogFragment() {
    protected var mViewDataBinding: Binding? = null
    protected var mRootView: View? = null
    protected var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
        setStyle(STYLE_NO_TITLE, getStyle())
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mViewDataBinding == null) {
            mViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getContentViewId(), container, false)
            mRootView = mViewDataBinding?.root
            init()
        }
        return mRootView
    }

    @LayoutRes
    protected abstract fun getContentViewId(): Int

    protected open fun init() {}

    protected open fun getStyle(): Int {
        return R.style.DialogFullScreen
    }
}