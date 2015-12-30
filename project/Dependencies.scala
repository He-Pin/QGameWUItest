import sbt.Keys._
import sbt._
object Dependencies{

  object Versions {
    val buildAKKAVersion = "2.4.1"
    val nettyVersion = "4.1.0.Beta8"
    val scalaCrossBuildVersion = List("2.11.7")
    val scalaVersion = scalaCrossBuildVersion.head
    val vaadinVersion = "7.5.10"
    val servletVersion = "3.1.0"
  }

  import Versions._

  object Compile {
    val sevletAPI =  "javax.servlet" % "javax.servlet-api" % servletVersion

    val vaadinServer = "com.vaadin" % "vaadin-server" % vaadinVersion
    val vaadinClient = "com.vaadin" % "vaadin-client" % vaadinVersion
    val vaadinClientCompiler = "com.vaadin" % "vaadin-client-compiler" % vaadinVersion
    val vaadinThemes = "com.vaadin" % "vaadin-themes" % vaadinVersion

  }

  import Compile._

  val libs = libraryDependencies

  val templeteDeps = libs ++= Seq(vaadinServer,vaadinClient,sevletAPI)

  val `gm-toolDeps` = libs ++= Seq(vaadinClientCompiler % "provided",vaadinThemes)
}
