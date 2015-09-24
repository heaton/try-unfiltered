organization := "me.heaton"

name := "justplayin"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-directives" % "0.8.4",
  "net.databinder" %% "unfiltered-filter" % "0.8.4",
  "net.databinder" %% "unfiltered-filter-async" % "0.8.4",
  "net.databinder" %% "unfiltered-jetty" % "0.8.4",
  "net.databinder" %% "unfiltered-netty-server" % "0.8.4",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "net.databinder" %% "unfiltered-specs2" % "0.8.4" % "test"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2",
  "jboss repo" at "http://repository.jboss.org/nexus/content/groups/public-jboss/"
)
