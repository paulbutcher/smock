import sbt._ 

class Smock(info: ProjectInfo) extends DefaultProject(info) { 
  val scalatest = "org.scalatest" % "scalatest" % "1.2"
}
