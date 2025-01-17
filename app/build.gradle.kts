plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "kz.akaromal.boardchess"
    compileSdk = 34

    defaultConfig {
        applicationId = "kz.akaromal.boardchess"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("char", "FEN_WHITE_PAWN", "'P'")
        buildConfigField("char", "FEN_WHITE_ROOK", "'R'")
        buildConfigField("char", "FEN_WHITE_KNIGHT", "'N'")
        buildConfigField("char", "FEN_WHITE_BISHOP", "'B'")
        buildConfigField("char", "FEN_WHITE_QUEEN", "'Q'")
        buildConfigField("char", "FEN_WHITE_KING", "'K'")
        buildConfigField("char", "FEN_BLACK_PAWN", "'p'")
        buildConfigField("char", "FEN_BLACK_ROOK", "'r'")
        buildConfigField("char", "FEN_BLACK_KNIGHT", "'n'")
        buildConfigField("char", "FEN_BLACK_BISHOP", "'b'")
        buildConfigField("char", "FEN_BLACK_QUEEN", "'q'")
        buildConfigField("char", "FEN_BLACK_KING", "'k'")
        buildConfigField("String", "FEN_WHITE_MOVE", "\"w\"")
        buildConfigField("String", "FEN_BLACK_MOVE", "\"b\"")

        buildConfigField("String", "FEN_NO_CASTLING", "\"-\"")
        buildConfigField("String", "FEN_NO_ENPASSANT", "\"-\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}