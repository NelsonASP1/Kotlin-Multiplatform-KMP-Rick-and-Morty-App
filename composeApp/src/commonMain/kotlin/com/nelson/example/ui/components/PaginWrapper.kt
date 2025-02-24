package com.nelson.example.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems

//Estos son los tipos de layout que voy a recibir o puedo recibir
enum class PagingType{
    ROW,
    COLUMN,
    VERTICAL_GRID,
    HORIZONTAL_GRID
}

//Esto significa que le puedo enviar cualquier tipo de parametro, exepto nulos
@Composable
fun <T : Any> PaginWrapper(
    pagingType: PagingType,
    pagingItems:LazyPagingItems<T>,
    initialView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    extraItemsView: @Composable () -> Unit = {},
    //Le añado la T para que me reciba cualquier tipo de dato
    itemView: @Composable (T) -> Unit
){
   when{
       pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 ->{
           //Carga inicial
           initialView()
       }

       pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
           //Lista vacia
           emptyView()
       }

       else ->{

           when(pagingType){
               PagingType.ROW -> {

                   LazyRow {
                       items(pagingItems.itemCount){ position ->
                           //El let con el signo de interrogacion significa que si el elemento no es nulo ejecute lo que pide entre los corchetes
                           pagingItems[position]?.let { item ->
                               itemView(item)
                           }
                       }
                   }

               }
               PagingType.COLUMN -> {

                   LazyColumn{
                       items(pagingItems.itemCount){ position ->
                           //El let con el signo de interrogacion significa que si el elemento no es nulo ejecute lo que pide entre los corchetes
                           pagingItems[position]?.let { item ->
                               itemView(item)
                           }
                       }
                   }

               }
               PagingType.VERTICAL_GRID -> {

                   LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                       items(pagingItems.itemCount){ position ->
                           //El let con el signo de interrogacion significa que si el elemento no es nulo ejecute lo que pide entre los corchetes
                           pagingItems[position]?.let { item ->
                               itemView(item)
                           }
                       }
                   }

               }
               PagingType.HORIZONTAL_GRID -> {}
           }



           if (pagingItems.loadState.append is LoadState.Loading){
               extraItemsView()
           }
       }

   }
}