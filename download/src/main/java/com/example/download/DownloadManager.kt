package com.example.download

import com.example.common.ActionHandler
import com.example.common.Status
import com.example.common.StatusListener

class DownloadManager : ActionHandler {

    private var statusListener: StatusListener? = null

    fun addStatusListener(listener: StatusListener) {
        this.statusListener = listener
    }

    override fun start() {
        this.statusListener?.onStatusChange(Status.OnStart)

        Utils.timer(10_000) {
            if (it == -1) {
                this.statusListener?.onStatusChange(Status.OnCompleted)
                return@timer
            }

            this.statusListener?.onStatusChange(Status.OnInProgress(it))
        }
    }

    override fun cancel() {
        this.statusListener?.onStatusChange(Status.OnError("Cancelled"))
    }

    override fun stop() {
        this.statusListener?.onStatusChange(Status.OnError("Stopped"))
    }
}