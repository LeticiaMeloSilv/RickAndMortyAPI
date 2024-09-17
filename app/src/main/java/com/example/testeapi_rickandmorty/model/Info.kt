package com.example.testeapi_rickandmorty.model
//Criando Objeto correspondente a retorno da Api(TEM Q TER O MESMO NOME DO RETORNO)

data class Info(
    val count:Int=0,
    val page:Int=0,
    val next:String="",
    val prev:String=""
    )
