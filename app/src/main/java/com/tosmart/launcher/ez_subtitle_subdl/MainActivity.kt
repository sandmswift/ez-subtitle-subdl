package com.tosmart.launcher.ez_subtitle_subdl

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.tosmart.launcher.ez_subtitle_subdl.databinding.ActivityMainBinding
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.Subtitle
import com.tosmart.launcher.ez_subtitle_subdl.network.domain.SubtitleResult
import com.tosmart.launcher.ez_subtitle_subdl.network.utils.getExternalStoragePath
import com.tosmart.launcher.ez_subtitle_subdl.ui.base.DaggerActivity
import com.tosmart.launcher.ez_subtitle_subdl.ui.dialog.DownloadDialog
import com.tosmart.launcher.ez_subtitle_subdl.ui.viewmodel.SubtitleViewModel


class MainActivity : DaggerActivity() {
    private val TAG = MainActivity::class.simpleName

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSubtitleViewModel: SubtitleViewModel
    private var mSubtitleList = mutableListOf<Subtitle>()
    private var mDownloadDialog: DownloadDialog = DownloadDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        iniData()
    }

    private fun iniData() {
        val viewModelProvider = ViewModelProvider(this)
        mSubtitleViewModel = viewModelProvider.get(SubtitleViewModel::class.java)
        mSubtitleViewModel.programs.observe(this) {
            if (it is SubtitleResult.Success) {
                mBinding.tvContent.text = it.data.toString()
                mSubtitleList = it.data.toMutableList()
            }
        }

        mBinding.btnSubmit.setOnClickListener {
            val fileName = mBinding.editTextMovieName.text.toString()
            val type = mBinding.editTextMovieType.text.toString()
            val language = mBinding.editTextMovieLanguage.text.toString()
            mSubtitleViewModel.getPrograms(fileName, type, language)
        }

        mBinding.btnClear.setOnClickListener {
            mBinding.editTextMovieName.setText("")
            mBinding.editTextMovieType.setText("")
            mBinding.editTextMovieLanguage.setText("")
        }

        mBinding.btnDownload.setOnClickListener {
            if (mSubtitleList.isNotEmpty()) {
                val subtitle = mSubtitleList[0]
                val link = subtitle.url
                Log.i(TAG, "link: " + link)
                if (link.isNotEmpty()) {
                    mSubtitleViewModel.downloadSubtitle(link, subtitle.name)
                    mDownloadDialog.setSubtitle(subtitle, getExternalStoragePath(this, subtitle.name))
                    mDownloadDialog.show(supportFragmentManager, "DownloadDialog")
                }
            }
        }

    }

}

