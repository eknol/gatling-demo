import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Search {

  /**
   * Feeders:
   */


  val feeder = csv("user-files/data/search.csv.txt").random

  /**
   * Search
   */

  val search = exec(http("Open home page")
    .get("/"))
    .pause(1)
    .feed(feeder)
    .exec(http("Start search")

    /**
     * Get variables from feeder with Gatling EL (Expression Language)
     */

    .get("/computers?f=${searchCriterion}")
    .check(css("a:contains('${searchComputerName}')", "href")
      .saveAs("computerURL")))
    .pause(1)
    .exec(http("Select")
    .get("${computerURL}"))
    .pause(1)

    /**
     * Debugging: the statement below prints your current session in the console.
     */

    .exec { session =>
      println(session)

      session
    }

}
