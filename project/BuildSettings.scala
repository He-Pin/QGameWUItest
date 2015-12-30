import com.earldouglas.xwp.JettyPlugin
import sbt.Keys._
import sbt._

object BuildSettings{
  val buildVersion      = sys.props.getOrElse("version", "1.0-SNAPSHOT")
  val buildScalaVersion = Dependencies.Versions.scalaVersion
  val buildHomepage     = "http://www.q-game.cn"

  private val typeSafeResovler = "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

  def commonSettings: Seq[Setting[_]] = Seq(
    organization := "cn.q-game",
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    homepage := Some.apply(url(buildHomepage)),
    javacOptions ++= makeJavacOptions("1.8"),
    fork in Test := true,
    testListeners in(Test, test) := Nil,
    resolvers := resolvers.value :+ typeSafeResovler :+ Resolver.bintrayRepo("hepin1989", "maven") :+ Resolver.mavenLocal,
    sources in(Compile, doc) := Seq.empty,
    publishArtifact in(Compile, packageDoc) := false
  )

  def makeJavacOptions(version: String) = Seq("-source", version, "-target", version, "-encoding", "UTF-8")


  def runtimeProject(name: String, dir: String, base: String = "framework"): Project = {
    //project in file(dir)
    Project(name, file(base + "/" + dir))
      .settings(commonSettings: _*)
      .settings {
        sources in(Compile, doc) := Seq.empty
        publishArtifact in(Compile, packageDoc) := false
        cancelable in Global := true
      }
  }

  def frameworkProject(name: String, dir: String) = {
    runtimeProject(name, dir, base = "framework")
  }

  def frameworkComponentProject(name: String, dir: String, sub: String) = {
    runtimeProject(name, dir + "/" + sub, base = "framework")
  }

  def stackProject(name: String, dir: String) = {
    runtimeProject(name, dir, base = "stack")
  }

  def anyComponentProject(name: String, base: String, dir: String, sub: String) = {
    runtimeProject(name, dir + "/" + sub, base = base)
  }
}

object ServerBuild extends Build{
  import BuildSettings._
  import org.vaadin.sbt.VaadinPlugin._

  lazy val root = Project(
    "root",
    file(".")
  ).settings(commonSettings: _*)
    //    .settings(releaseSettings:_*)
    .aggregate(frameWorkAndStackProjects: _*)

  lazy val frameWorkAndStackProjects = Seq[ProjectReference](
    templet,
    `gm-tools`
  )
  //templet,gm-tools
  lazy val templet = frameworkProject("templete","templete")
    .settings(vaadinWebSettings)
    .settings(Dependencies.templeteDeps)

  lazy val `gm-tools` = stackProject("gm-tools","gm-tools")
    .enablePlugins(JettyPlugin)
    .settings(vaadinWebSettings: _*)
    .settings(Dependencies.`gm-toolDeps`)
    .dependsOn(templet)
}