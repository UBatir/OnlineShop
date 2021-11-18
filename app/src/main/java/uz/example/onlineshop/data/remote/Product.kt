package uz.example.onlineshop.data.remote

data class Product(
    val name:String,
    val price:String,
    var isFav:Boolean
)