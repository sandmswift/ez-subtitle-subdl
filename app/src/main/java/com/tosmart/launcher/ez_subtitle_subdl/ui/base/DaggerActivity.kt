package com.tosmart.launcher.ez_subtitle_subdl.ui.base

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Base activity providing Dagger support and [ViewModel] support
 */
@AndroidEntryPoint
abstract class DaggerActivity : AppCompatActivity()