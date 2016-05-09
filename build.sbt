lazy val commonOptions = Seq(
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked")

lazy val consoleOptions = commonOptions diff Seq("-Ywarn-unused-import")

lazy val dependencies = Seq(
  "org.scalatest"  %% "scalatest" % "2.2.6"  % "test",
  "org.spire-math" %% "spire"     % "0.11.0")

lazy val commonSettings = Seq(
  name := "spigot",
  organization := "net.xngns",
  scalaVersion := "2.11.8",
  scalacOptions := commonOptions,
  scalacOptions in (Compile, console) := consoleOptions,
  scalacOptions in (Test, console) := consoleOptions,
  wartremoverErrors in (Compile, compile) ++= Warts.allBut(Wart.Throw))

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= dependencies)
