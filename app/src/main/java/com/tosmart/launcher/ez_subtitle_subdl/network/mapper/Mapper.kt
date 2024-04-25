package com.tosmart.launcher.ez_subtitle.network.mapper

/**
 * @author wyq
 * @date 2024/4/22 15:53
 * @Description Convert two data model classes to each other
 */

interface Mapper<Resource, Target> {
    fun Resource.mapToTarget(): Target
    fun Target.mapToResource(): Resource
    fun List<Resource>.mapToTarget(): List<Target>
    fun List<Target>.mapToResource(): List<Resource>
}