package org.android.go.sopt

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool.imageService
import org.android.go.sopt.util.ContentUriRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel : ViewModel() {
    private val _image = MutableLiveData<ContentUriRequestBody>()

    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(contentUriRequestBody: ContentUriRequestBody) {
        _image.value = contentUriRequestBody
    }

    fun uploadImage() {
        if (image.value != null) {
            imageService.uploadImage(_image.value!!.toFormData()).enqueue(object :
                Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Log.e("sopt", "image upload success")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {

                }


            })
        }
    }
}