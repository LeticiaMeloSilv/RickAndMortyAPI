package com.example.testeapi_rickandmorty.service

import com.example.testeapi_rickandmorty.model.Character
import com.example.testeapi_rickandmorty.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    //https://rickandmortyapi.com/api/character
    //dentro do get, vc coloca a parte do endereco q nn é padrao(https://rickandmortyapi.com/api/  vai ter em todas as requisicoes dessa api, mas character, vai ter só nessa requisicao)
    @GET("character")
    fun getAllCharacters():Call<Result>
    @GET("character/{id}")
    fun getCharacterById(@Path("id") id:Int):Call<Character>
}