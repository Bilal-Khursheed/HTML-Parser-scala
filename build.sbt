name := "akka_app"

version := "0.1"

val circeVersion = "0.11.1"


scalaVersion := "2.13.1"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.25"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http-core
libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "10.1.8"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.9"
// https://mvnrepository.com/artifact/com.typesafe.play/play-ws-standalone-json
libraryDependencies += "com.typesafe.play" %% "play-ws-standalone-json" % "2.0.6"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-slf4j
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.5.25"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.25" % Test
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3" % Test
// https://mvnrepository.com/artifact/de.heikoseeberger/akka-http-play-json
libraryDependencies += "de.heikoseeberger" %% "akka-http-play-json" % "1.28.0"
// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-stream
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.25"

// https://mvnrepository.com/artifact/io.circe/circe-core
libraryDependencies += "io.circe" %% "circe-core" % "0.12.1"

// https://mvnrepository.com/artifact/io.circe/circe-generic
libraryDependencies += "io.circe" %% "circe-generic" % "0.12.1"

// https://mvnrepository.com/artifact/io.circe/circe-parser
libraryDependencies += "io.circe" %% "circe-parser" % "0.12.1"

// https://mvnrepository.com/artifact/de.heikoseeberger/akka-http-circe
libraryDependencies += "de.heikoseeberger" %% "akka-http-circe" % "1.29.1"

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test




//this is for HTML Parse
// https://mvnrepository.com/artifact/org.jsoup/jsoup
libraryDependencies += "org.jsoup" % "jsoup" % "1.11.3"
// https://mvnrepository.com/artifact/com.typesafe.play/play-json
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.4"


