package com.tosmart.launcher.ez_subtitle_subdl.network.domain

/**
 * @author wyq
 * @date 2024/4/22 16:21
 * @Description
 */
data class Program(
    val type: String,
    val sdId: String,
    val name: String,
    val imdbId: String,
    val tmdbId: String,
    val year: String,
    val firstAirDate: String

)

data class Subtitle(
    val releaseName: String,
    val name: String,
    val lang: String,
    val author: String,
    val url: String,
    val season: String,
    val episode: String
)

data class ProgramWrapper(
    val status: Boolean,
    val results: List<Program>,
    val subtitles: List<Subtitle>
)

