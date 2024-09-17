package com.example.testeapi_rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.testeapi_rickandmorty.model.Character
import com.example.testeapi_rickandmorty.service.RetrofitFactory
import com.example.testeapi_rickandmorty.ui.theme.TesteAPIRickAndMortyTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            TesteAPIRickAndMortyTheme {
ListAllCharacters()
            }
        }
    }
}
@Composable
fun ListAllCharacters(){
    val characterList= remember {
        mutableStateOf(listOf<Character>())
    }
    val character= remember {
        mutableStateOf(Character())
    }
    val id= remember {
mutableStateOf("")
    }
    Column {
        OutlinedTextField(value = id.value, onValueChange = {id.value=it}
        )

//efetuar a chamada paara a api
    Button(onClick = {
    val callCharacterById=RetrofitFactory()
        .getCharacterService()
        .getCharacterById( id.value.toInt())
//enqueue=enfileirar(fzr uma lista de "preferencia")
    callCharacterById.enqueue(object :Callback<Character>{
        override fun onResponse(p0: Call<Character>, p1: Response<Character>) {
            character.value= p1.body()!!
            Log.i(
                "RICK_MORTY",
                "${character.value.name} - ${character.value.origin!!.name}"
            )
        }

        override fun onFailure(p0: Call<Character>, p1: Throwable) {
            TODO("Not yet implemented")
        }

    })
    }) {
Text(text = "Buscar")
    }
        AsyncImage(model = character.value.image, contentDescription ="aaa")
        Text(text = character.value.name)
        Text(text = character.value.origin?.name?:"")
        Text(text = character.value.species)
        Text(text = character.value.location?.name?:"")
        Text(text = character.value.status)
        Text(text = character.value.gender)
        Text(text = character.value.type?:"Sem tipo definido")

    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TesteAPIRickAndMortyTheme {
        Greeting("Android")
    }
}