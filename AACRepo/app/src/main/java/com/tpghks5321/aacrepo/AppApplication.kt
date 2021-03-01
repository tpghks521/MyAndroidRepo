package com.tpghks5321.aacrepo

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/*
 * MultiDexApplication는 SdkVersion이 20 이하인 경우 를위 하여 설정된다.
 *
 */


@HiltAndroidApp
class AppApplication :MultiDexApplication(){

}