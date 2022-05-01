package com.kkuber.myapp_05_frame

import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun bindIsolatedService(
        service: Intent,
        flags: Int,
        instanceName: String,
        executor: Executor,
        conn: ServiceConnection
    ): Boolean {
        return super.bindIsolatedService(service, flags, instanceName, executor, conn)
    }
}