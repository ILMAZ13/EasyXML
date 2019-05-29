package ru.kpfu.easyxml.modules.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kpfu.easyxml.modules.entities.figma.ImageResponse
import ru.kpfu.easyxml.modules.entities.figma.NodesResponse

interface Api {

    @GET("v1/files/{key}/nodes")
    fun getRes(@Header("X-Figma-Token") token: String,
               @Path("key") key: String,
               @Query("ids") ids: String): Single<NodesResponse>

    @GET("v1/images/{key}")
    fun getImages(@Header("X-Figma-Token") token: String,
                  @Path("key") key: String,
                  @Query("ids") ids: String,
                  @Query("format") format: String = "png"): Single<ImageResponse>
}