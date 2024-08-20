package com.example.chatia.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

// Modelos de datos para la solicitud y respuesta
data class Part(val text: String)
data class Content(val parts: List<Part>)
data class ChatRequest(val contents: List<Content>)
data class SafetyRating(val category: String, val probability: String)

data class Candidate(
    val content: Content,
    val finishReason: String,
    val index: Int,
    val safetyRatings: List<SafetyRating>
)
data class UsageMetadata(
    val promptTokenCount: Int,
    val candidatesTokenCount: Int,
    val totalTokenCount: Int
)
data class ChatResponse(
    val candidates: List<Candidate>,
    val usageMetadata: UsageMetadata
)

interface GeminiApiService {
    @POST("v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyAYj0mM8rDh4lg8AazoV-4pTi-xzhRShuA")
    fun sendMessage(@Body requestBody: ChatRequest): Call<ChatResponse>
}

// Helper para la API de Gemini
class GeminiApiServiceHelper {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://generativelanguage.googleapis.com/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(GeminiApiService::class.java)

    fun sendMessageToGemini(prompt: String): Call<ChatResponse> {
        val requestBody = ChatRequest(
            contents = listOf(
                Content(parts = listOf(Part(text = prompt)))
            )
        )

        return service.sendMessage(requestBody)
    }
}
