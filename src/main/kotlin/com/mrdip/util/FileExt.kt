package com.mrdip.util

import io.ktor.http.content.*
import java.io.File
import java.util.UUID

fun PartData.FileItem.saveFile(folderPath: String): String{
    val fileName = "${UUID.randomUUID()}.${File(originalFileName as String).extension}"
    val fileBytes = streamProvider().readBytes()

    val folder = File(folderPath)
    folder.mkdirs()
    File("$folder/$fileName").writeBytes(fileBytes)
    return fileName
}