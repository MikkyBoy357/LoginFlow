package com.mikkyboy.loginflow.data.favorite

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Source
import com.mikkyboy.loginflow.data.NavigationItem
import com.mikkyboy.loginflow.data.login.LoginUIEvent
import com.mikkyboy.loginflow.data.login.LoginUIState
import com.mikkyboy.loginflow.data.model.FavoriteModel
import com.mikkyboy.loginflow.data.signup.SignupViewModel
import com.mikkyboy.loginflow.navigation.PostOfficeAppRouter
import com.mikkyboy.loginflow.navigation.Screen

class FavoriteViewModel : ViewModel() {

    private val TAG = FavoriteViewModel::class.simpleName

    var favoriteUIState = mutableStateOf(FavoriteUIState())

    private val FAVORITES_COLLECTION_REF = "favorites"

    private val favoritesRef: CollectionReference =
        FirebaseFirestore.getInstance().collection(FAVORITES_COLLECTION_REF)

    var favoriteCryptos = listOf<String>()

    init {
        println("FAV => INIT")
        getFavoriteCryptos {
            favoriteUIState.value = favoriteUIState.value.copy(
                favoriteCryptos = it
            )
        }
    }

//    fun getFavoriteCryptos() {
//        var uid: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""
//        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//        var source = Source.DEFAULT
//
////        firestore.collection("favorites").get(source).addOnSuccessListener { snapshot ->
////            for (document in snapshot.documents) {
////                //             👆
////                Log.d(TAG, snapshot.metadata.toString())
////            }
////        }
//    }

    fun getFavoriteCryptos(afterFunction: (favoriteCryptos: List<String>) -> Unit): List<String> {
        var uid: String = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val db = FirebaseFirestore.getInstance()

        println("UserID => $uid")

        val data = db.collection("favorites").document(uid)
            .get()
            .addOnFailureListener {
                Log.d("Repository", "exception:" + it.message.toString())
            }
            .addOnSuccessListener { document ->
                Log.d("Repository", "data:" + document.data.toString())
                val tempFavs = document.data!!["favs"]
                Log.d("Repository", "tempFavs:" + tempFavs)
                favoriteCryptos = tempFavs as List<String>
            }

        Log.d(TAG, data.toString())

        afterFunction.invoke(favoriteCryptos)

        return favoriteCryptos
    }

    fun toHomeScreen() {
        PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
    }

    fun onEvent(event: FavoriteUIEvent) {
        when (event) {
            is FavoriteUIEvent.FavoriteCryptosFetched -> {
                favoriteUIState.value = favoriteUIState.value.copy(
                    favoriteCryptos = event.favoriteCryptos
                )
            }
        }
    }

}

//        db.collection("favorites").document(uid).get().addOnCompleteListener { task ->
//            print("MIKKY -> ${task.result}")
//            if (task.isSuccessful) {
//                val document = task.result
//                println("RESULT => ${document?.data?.toString()}")
//                if (document != null) {
//                    if (document.exists()) {
//                            val favs = document.data?.get("favs")
//                            Log.d("TAG", favs.toString())
////                        Log.d(TAG, document.toString())
//                    } else {
//                        Log.d(TAG, "The document doesn't exist.")
//                    }
//                }
//            } else {
//                task.exception?.message?.let {
//                    Log.d(TAG, it) //Never ignore potential errors!
//                }
//            }
//        }

//        return FavoriteModel(favs = listOf("Hello"))