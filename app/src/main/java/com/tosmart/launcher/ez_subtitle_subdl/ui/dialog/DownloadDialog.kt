package com.tosmart.launcher.ez_subtitle_subdl.ui.dialog

import com.tosmart.launcher.ez_subtitle_subdl.R
import com.tosmart.launcher.ez_subtitle_subdl.databinding.DialogDownloadBinding
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.Subtitle
import com.tosmart.launcher.ez_subtitle_subdl.ui.base.BaseDialogFragment

/**
 * @author wyq
 * @date 2024/4/25 12:22
 * @Description
 */
class DownloadDialog : BaseDialogFragment<DialogDownloadBinding>() {
    private lateinit var mSubtitle: Subtitle
    private lateinit var mFilePath: String


    override fun getContentViewId(): Int {
        return R.layout.dialog_download
    }

    override fun init() {
        mViewDataBinding?.fileName?.text = mSubtitle.name
        mViewDataBinding?.formDownloadUrl?.text = mSubtitle.url
        mViewDataBinding?.fileAddress?.text = mFilePath
    }

    fun setSubtitle(subtitle: Subtitle, filePath: String) {
        mSubtitle = subtitle
        mFilePath = filePath
    }
}