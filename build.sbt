name := """Clippers"""

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
     Resolver.file("Local Repository", file("/"+Path.userHome+"/.ivy2/local"))(Resolver.ivyStylePatterns),
  "Typesafe" at "http://repo.typesafe.com/typesafe/releases",
  "net.fwbrasil" at "http://repo1.maven.org/maven2/"
)

val activateVersion = "1.6.2"
libraryDependencies ++= Seq( 
  jdbc,
  anorm,
  cache,
  ws,
   "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
  "org.webjars" % "angularjs" % "1.3.0-beta.2",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "net.fwbrasil" %% "activate-play" % activateVersion,
  "net.fwbrasil" %% "activate-jdbc" % activateVersion,
  "net.fwbrasil" %% "activate-core" % activateVersion,
  "net.fwbrasil" %% "activate-jdbc-async" % activateVersion,
  "net.fwbrasil" %% "activate-spray-json" % activateVersion
  )

lazy val root = (project in file(".")).enablePlugins(PlayScala)

pipelineStages := Seq(rjs, digest, gzip)

fork in run := true


fork in run := true