package com.example.download

import android.os.CountDownTimer

internal object Utils {

    fun timer(timeInMillis: Long, action: (Int) -> Unit) {
        if (timeInMillis <= 0) {
            action(0)
            return
        }

        val timer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                action(millisUntilFinished.toInt())
            }

            override fun onFinish() {
                action(-1)
            }
        }
        timer.start()
    }
}