package com.riffaells.hellocodehub.di

import org.kodein.di.DI


val allModules = DI.Module("AllModules") {
}



val kodein = DI() {
    // Для удобства с Android
    import(allModules)
}
