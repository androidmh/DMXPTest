package com.dm.dmxptest

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn1 ->loadApps()

            R.id.btn2 -> loadPackage()
        }
    }

    private fun loadPackage() {
        val packageManager = packageManager
        val installedPackages = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES)
        for (installedPackage in installedPackages) {
            Log.e("dmfun1", "${installedPackage.packageName}${installedPackage.sharedUserLabel}----")
        }
    }

    private fun loadApps() {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val apps = packageManager.queryIntentActivities(intent, 0)
        //for循环遍历ResolveInfo对象获取包名和类名
        for (i in apps.indices) {
            val info = apps[i]
            val packageName = info.activityInfo.packageName
            val cls = info.activityInfo.name
            val name = info.activityInfo.loadLabel(packageManager)
            Log.e("dmfun1", "$name----$packageName----$cls")
        }
    }


}
