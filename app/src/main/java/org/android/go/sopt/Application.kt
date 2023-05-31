package org.android.go.sopt

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

object AuthLocalStorage {
    private const val PREFERENCE_NAME = "auth"
    private lateinit var preference: SharedPreferences
    private const val TOKEN = "USER_TOKEN"

    fun init(context: Context) {
        preference = context.getSharedPreferences(
            "${context.packageName}_${PREFERENCE_NAME}",
            Context.MODE_PRIVATE
        )
    }


    fun setString(key: String, value: String?) {
        // 번거롭게 editor 만들필요 없이, androidx.core ktx를 활용하면 쉽습니다.
        preference.edit {
            // Boolean 데이터인 경우 putBoolean,
            putString(key, value)
        }
    }

    fun getString(key: String): String? {
        return preference.getString(key, "")
    }
}