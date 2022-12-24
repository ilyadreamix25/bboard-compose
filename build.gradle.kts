buildscript {
    extra.apply {
        set("composeVersion", "1.3.0")
        set("composeBomVersion", "2022.12.00")
        set("composeActivityVersion", "1.6.1")
        set("composeConstraintLayoutVersion", "1.0.1")
        set("composeNavVersion", "2.5.3")

        set("coreKtxVersion", "1.9.0")
        set("lifecycleRuntimeKtxVersion", "2.5.1")

        set("gsonVersion", "2.10")
        set("retrofitVersion", "2.9.0")
        set("koilVersion", "2.2.2")

        set("daggerHiltVersion", "2.44.2")
    }

    repositories {
        maven { setUrl("https://jitpack.io") }
    }
}

plugins {
    id("com.android.application")      version "7.3.1"  apply false
    id("com.android.library")          version "7.3.1"  apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}