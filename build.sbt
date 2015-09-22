import sbt.Keys._
import spray.revolver.RevolverPlugin.Revolver

name          := "labs"

version       := "0.1"

scalaVersion  := "2.11.7"

libraryDependencies ++= {
  val akkaV = "2.3.12"
  val sprayV = "1.3.3"
  Seq(
    "org.scala-lang"      % "scala-compiler"  % scalaVersion.value,
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-json"    % "1.3.2",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %% "specs2-core"    % "3.6.4" % "test"
  )
}

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
scalacOptions in Test ++= Seq("-Yrangepos")

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

fork in test := true

Revolver.settings
