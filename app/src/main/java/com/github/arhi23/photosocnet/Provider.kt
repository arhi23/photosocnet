package com.github.arhi23.photosocnet

//@InstallIn(SingletonComponent::class)
//@Module
//class Provider {
//
//  @Provides
//  fun providesApiCredentials(sharedPreferences: SharedPreferences): ApiCredentials =
//    ApiCredentials(sharedPreferences.getString("api", "") ?: "")
//
//  @Singleton
//  @Provides
//  fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
////    val masterKey: MasterKey = Builder(context)
////      .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
////      .build()
////
////    val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
////      context,
////      "secret_shared_prefs",
////      masterKey,
////      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
////      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
////    )
////
////    // use the shared preferences and editor as you normally would
////
////    // use the shared preferences and editor as you normally would
////    val editor = sharedPreferences.edit()
//    return context.getSharedPreferences("prefs", MODE_PRIVATE)
//  }
//}