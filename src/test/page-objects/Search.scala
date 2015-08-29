import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Search {

  val search = exec(http("Open home page")
    .get("/"))
    .pause(1)
    .exec(http("Start search")
    .get("/computers?f=macbook"))
    .pause(1)

}
