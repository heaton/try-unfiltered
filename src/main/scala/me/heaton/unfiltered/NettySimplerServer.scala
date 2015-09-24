package me.heaton.unfiltered

import unfiltered.netty._
import unfiltered.request._
import unfiltered.response._

object NettySimplerServer {

  trait MyPlan extends cycle.Plan with cycle.DeferralExecutor with cycle.DeferredIntent with ServerErrorResponse {
    def underlying = MyExecutor.underlying
  }

  object Hello extends MyPlan {
    def intent = {
      case _ => ResponseString("hello world")
    }
  }

  object MyExecutor {
    import org.jboss.netty.handler.execution._
    lazy val underlying = new MemoryAwareThreadPoolExecutor(
      16, 65536, 1048576)
  }

}
