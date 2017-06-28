name := "toy-robot-scala"

version := "1.0"

scalaVersion := "2.12.1"

val testLibraries = Seq(
  "junit" % "junit" % "4.12" % "test"
)

libraryDependencies ++= testLibraries
