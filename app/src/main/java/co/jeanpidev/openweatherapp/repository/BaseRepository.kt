package co.jeanpidev.openweatherapp.repository

import android.util.Log
import co.jeanpidev.openweatherapp.model.ErrorModel
import co.jeanpidev.openweatherapp.utils.ERROR_TAG
import co.jeanpidev.openweatherapp.utils.ERROR_UNKNOWN
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    companion object {

        fun <T> handleSuccess(data: T): Result<T> = Result.success(data)

        fun <T> handleError(e: ResponseBody): Result<T> {
            // log error
            Log.e(ERROR_TAG, e.toString())
            return Result.failure(parseErrorBody(e))
        }

        private fun parseErrorBody(e: ResponseBody): ErrorModel {
            val gson = GsonBuilder().create()
            var error = ErrorModel(ERROR_UNKNOWN)

            try {
                val errorBody = e.string()
                error = gson.fromJson(errorBody, ErrorModel::class.java)
            } catch (e: IOException) {
                Log.e(ERROR_TAG, e.toString())
            } catch (e: JsonSyntaxException) { // json is malformed
                Log.e(ERROR_TAG, e.toString())
            } catch (e: IllegalStateException) { // parsed json resulted in null
                Log.e(ERROR_TAG, e.toString())
            }
            return error
        }
    }
}
