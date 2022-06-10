package com.example.projectx.data

import android.content.Context
import android.provider.CallLog
import com.example.projectx.domain.models.CallModel
import com.example.projectx.domain.repository.CallResolver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class CallResolverImpl(
    private val context: Context,
) : CallResolver {

    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss a z", Locale.getDefault())

    override suspend fun getCalls(): List<CallModel> {
        return withContext(Dispatchers.IO) {
            val callModels = mutableListOf<CallModel>()
            val cursor = context.contentResolver.query(
                CallLog.Calls.CONTENT_URI,
                PROJECTION,
                null,
                null,
                "${CallLog.Calls.DATE} DESC"
            )

            cursor?.use {
                while (cursor.moveToNext()) {
                    val number = cursor.getString(1)
                    val type = when (cursor.getString(2).toInt()) {
                        1 -> "Incoming"
                        2 -> "Outgoing"
                        3 -> "Missed Call"
                        4 -> "Black list"
                        5 -> "Rejected"
                        else -> ""
                    }
                    val duration = cursor.getString(3)
                    val date = simpleDateFormat.format(Date(cursor.getLong(4)))

                    callModels.add(
                        CallModel(
                            number = number,
                            type = type,
                            duration = duration,
                            date = date.toString()
                        )
                    )
                }
            }

            callModels
        }
    }

    companion object {
        private val PROJECTION = arrayOf(
            CallLog.Calls._ID,
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,
            CallLog.Calls.DURATION,
            CallLog.Calls.DATE
        )
    }

}