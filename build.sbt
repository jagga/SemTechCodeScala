import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.5"

lazy val root = (project in file("."))
  .settings(
      libraryDependencies += "org.scalatestplus" %% "junit-4-13" % "3.2.19.1" % "test",
      libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.19",
      name := "SemTechScalaCode"
  )

