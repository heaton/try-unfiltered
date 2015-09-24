package me.heaton.unfiltered

import unfiltered.request._
import unfiltered.response._
import unfiltered.directives._, Directives._

object AdvanceServer extends App{

  implicit val implyIntValue =
    data.as.String ~> data.as.Int.fail { (k,v) =>
      BadRequest ~> ResponseString(
        s"'$v' is not a valid int for $k"
      )
    }

  implicit def required[T] = data.Requiring[T].fail(name =>
    BadRequest ~> ResponseString(name + " is missing\n")
  )

  unfiltered.jetty.Server.http(8080).plan(
    unfiltered.filter.Planify { Directive.Intent {
      case Path("/")  =>
        for {
          a <- data.as.Option[Int] named "a"
          b <- data.as.Option[Int] named "b"
        } yield ResponseString(
          (a ++ b).sum + "\n"
        )
      case Path("/other") =>
        for {
          opt <- data.as.Option[String] named "opt"
          req <- data.as.Required[String] named "req"
        } yield ResponseString(
          s"opt: $opt req: $req"
        )
    } }
  ).run()

}


