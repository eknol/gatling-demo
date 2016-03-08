import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object Browse {

  val browse = exec(http("Results page 1")
    .get("/computers?p=1&f=macbook"))
    .pause(1)
    .exec(http("Results page 2")
    .get("/computers?p=2&f=macbook"))
    .pause(1)

  val browseLoop = repeat(2, "n") {
    exec(http("Page ${n}")
      .get("/computers?p=${n}"))
      .pause(1)
  }

  def printMe(arg1: Int): ChainBuilder = {
    repeat(arg1, "n") {
      exec(http("Page ${n}")
        .get("/computers?p=${n}"))
        .pause(1)
    }
  }

}
