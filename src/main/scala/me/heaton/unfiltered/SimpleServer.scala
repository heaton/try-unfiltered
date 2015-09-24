package me.heaton.unfiltered

object SimpleServer extends App{

  import unfiltered.request._
  import unfiltered.response._

  val echo = unfiltered.filter.Planify {
    case Path(Seg(p :: Nil)) => ResponseString(p)
  }

  val nice = unfiltered.filter.Planify {
    case _ => ResponseString(
      "I should be 404"
    )
  }

  val paths = unfiltered.filter.Planify {
    case GET(Path(Seg("record" :: id :: Nil))) =>
      Created ~> ResponseString(s"record id is $id")
    case req @ PUT(Path(Seg("record" :: id :: Nil))) =>
      val bytes = new String(Body.bytes(req))
      ResponseString(s"record id is $id with $bytes")
  }

  val params = unfiltered.filter.Planify {
    case Params(params) =>
      ResponseString(params("test").toString)
  }

  object Pos extends Params.Extract(
    "pos",
    Params.first ~> Params.int ~>
      Params.pred { _ > 0 }
  )
  object Neg extends Params.Extract(
    "neg",
    Params.first ~> Params.int ~>
      Params.pred { _ < 0 }
  )
  val intEcho = unfiltered.filter.Planify {
    case Params(Pos(pos) & Neg(neg)) =>
      ResponseString("%d %d".format(pos,neg))
  }

  unfiltered.jetty.Server.anylocal.plan(echo).plan(paths).plan(params).plan(nice).run()

}
