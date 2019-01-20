name          := "shithead"
organization  := "de.htwg.se"
scalaVersion  := "2.12.7"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.3"

libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

enablePlugins(DockerPlugin)

coverageEnabled := true
coverageExcludedPackages := "de.htwg.se.shithead.view.gui.*;de.htwg.se.shithead.model.fileIoComponent.*;de.htwg.se.shithead.model.cardStackComponent.mockImp.*;
de.htwg.se.shithead.model.stackComponent.mockImp.*;de.htwg.se.shithead.model.userListComponent.mockImp.*;de.htwg.se.shithead.controller.controllerBase.controllerMock.*;
de.htwg.se.shithead.controller.controllerBase.controllerBaseIm.commands.*;de.htwg.se.shithead.controller.Events.scala;de.htwg.se.shithead.controller.GameState.scala;"

dockerfile in docker := {
  val jarFile: File = sbt.Keys.`package`.in(Compile, packageBin).value
  val classpath = (managedClasspath in Compile).value
  val mainclass = mainClass.in(Compile, packageBin).value.getOrElse(sys.error("Expected exactly one main class"))
  val jarTarget = s"/app/${jarFile.getName}"
  // Make a colon separated classpath with the JAR file
  val classpathString = classpath.files.map("/app/" + _.getName)
    .mkString(":") + ":" + jarTarget
  new Dockerfile {
    // Base image
    from("openjdk:8-jre")
    // Add all files on the classpath
    add(classpath.files, "/home/julius/IdeaProjects/Shithead/de.htwg.se.shithead/")
    // Add the JAR file
    add(jarFile, jarTarget)
    // On launch run Java with the classpath and the main class
    entryPoint("java", "-cp", classpathString, mainclass)
  }
}
