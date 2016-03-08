import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val users = scenario("Users")
    .exec(
      Search.search,
      Browse.browse,
      Browse.printMe(2)
    )

  val admins = scenario("Admins")
    .exec(
      Search.search,
      Browse.browseLoop,
      Edit.select,
      Edit.changeCompanyToLenovo,
      Edit.changeCompanyToAtari
    )

  setUp(
    users.inject(atOnceUsers(5)),
    admins.inject(rampUsers(10) over (10 seconds))
  ).protocols(httpConf)
    .assertions(
      details("Open home page").responseTime.max.lessThan(240),
      global.successfulRequests.percent.greaterThan(50)
    )

}
