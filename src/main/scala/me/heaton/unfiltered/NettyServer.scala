package me.heaton.unfiltered

import unfiltered.request._
import unfiltered.response._

object NettyServer extends App{

  val hello = unfiltered.netty.cycle.Planify {
    case _ => ResponseString("hello world")
  }
  unfiltered.netty.Server.http(8080).plan(hello).run()

}
