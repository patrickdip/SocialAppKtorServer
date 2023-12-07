package com.mrdip.route

import com.mrdip.model.PostResponse
import com.mrdip.model.PostTextParams
import com.mrdip.repository.post.PostRepository
import com.mrdip.util.Constants
import com.mrdip.util.saveFile
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import java.io.File

fun Routing.postRouting(){
    val postRepository by inject<PostRepository>()

    authenticate {
        route(path = "/post"){
            post(path = "/create"){
                var fileName = ""
                var postTextParams: PostTextParams? = null
                val multiPartData = call.receiveMultipart()

                multiPartData.forEachPart { partData ->
                    when(partData){
                        is PartData.FileItem -> {
                            fileName = partData.saveFile(folderPath = Constants.POST_IMAGES_FOLDER_PATH)
                        }
                        is PartData.FormItem -> {
                            if (partData.name == "post_data"){
                                postTextParams = Json.decodeFromString(partData.value)
                            }
                        }
                        else -> {}
                    }
                    partData.dispose()
                }

                val imageUrl = "${Constants.BASE_URL}${Constants.POST_IMAGES_FOLDER}$fileName"

                if (postTextParams == null){
                    File("${Constants.POST_IMAGES_FOLDER_PATH}/$fileName").delete()

                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = PostResponse(
                            success = false,
                            message = "Could not parse post data"
                        )
                    )
                }else {
                    val result = postRepository.createPost(imageUrl, postTextParams!!)
                    call.respond(result.code, message = result.data)
                }
            }

            
        }
    }
}