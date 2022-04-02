package org.sopt.mvvmdiaryexample.data.executor

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DispatchExecutors(
    val ioThread: Executor = MainThreadExecutor,
    val mainThread: Executor = IoThreadExecutor,
) {
    companion object {
        private var instance: DispatchExecutors? = null

        fun getInstance(): DispatchExecutors {
            return instance ?: synchronized(this) {
                DispatchExecutors().also { instance = it }
            }
        }
    }
}

private object MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mainThreadHandler.post(command)
    }
}

private object IoThreadExecutor : Executor {
    private val ioThreadExecutor = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) {
        ioThreadExecutor.execute(command)
    }
}