import sbt._
import sbt.Keys._

object UpBuild extends Build {
  lazy val upNavigation = Project(
    "upNavigation",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(
      organization := "com.oscarvarto",
      scalaVersion := "2.10.3",
      scalacOptions ++= Seq(
        "-language:_",
        "-encoding", "UTF-8"
      ),
      resolvers += "Local m2repository" at "file:///usr/local/opt/android-sdk/extras/android/m2repository",
      libraryDependencies ++= Seq(
        "org.scalaz" % "scalaz-core_2.10" % "7.0.5",
        "com.android.support" % "support-v4" % "19.0.0"
      )
    )
  )
}
