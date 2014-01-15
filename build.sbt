import android.Keys._, java.io.File

android.Plugin.androidBuild

useProguard in Android := true

proguardOptions in Android ++= Seq("-dontobfuscate", "-dontoptimize")

target in Android := new File("project.properties")

run <<= run in Android

install <<= install in Android

debugIncludesTests in Android := false

apkbuildExcludes in Android ++= Seq("reference.conf", "META-INF/LICENSE.txt", "META-INF/NOTICE.txt", "META-INF/mime.types")

dexMaxHeap in Android := "2048m"

proguardCache in Android ++= Seq(
  ProguardCache("scalaz") % "org.scalaz" % "scalaz-core_2.10"
)
