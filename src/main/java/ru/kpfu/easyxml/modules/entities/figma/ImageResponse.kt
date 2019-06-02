package ru.kpfu.easyxml.modules.entities.figma

data class ImageResponse(
        val err: String,
        val images: Map<String, String>,
        val status: Number
)