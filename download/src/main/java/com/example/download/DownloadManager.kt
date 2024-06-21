package com.example.download

import com.example.common.ActionHandler
import com.example.common.Status
import com.example.common.StatusListener

class DownloadManager : ActionHandler {

    private var statusListener: StatusListener? = null

    override fun addStatusListener(listener: StatusListener) {
        this.statusListener = listener
    }

    override fun start() {
        this.statusListener?.onStatusChange(Status.OnStart("Downloading started..."))

        Utils.startTimerTask(10_000) {
            if (it >= 100) {
                this.statusListener?.onStatusChange(Status.OnCompleted("Downloading completed!"))
                return@startTimerTask
            }

            this.statusListener?.onStatusChange(Status.OnInProgress("Downloaded ", it))
        }
    }

    override fun cancel() {
        Utils.stopTimerTask {
            this.statusListener?.onStatusChange(Status.OnError("Download cancelled!"))
        }
    }

    override fun stop() {
        Utils.stopTimerTask {
            this.statusListener?.onStatusChange(Status.OnError("Download stopped!"))
        }
    }
}