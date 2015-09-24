package me.heaton.unfiltered

import unfiltered.request._
import unfiltered.response._

object AsyncServer extends App{

  object AsyncPlan extends unfiltered.filter.async.Plan  {
    def intent = {
      case GET(Path("/pass")) => Pass
      case req@GET(Path("/async")) =>
        req.respond(ResponseString("test") ~> Ok)
      case _ => ResponseString("Default 404")
    }
  }

  unfiltered.jetty.Server.http(8080).plan(AsyncPlan).run()

}
