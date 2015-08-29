import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Edit {

  val select = exec(http("Select item")
    .get("/computers/4085"))
    .pause(1)

  val changeCompanyToLenovo = exec(http("Change company to Lenovo Group")
    .post("/computers/4085?name=Amiga+1000&introduced=&discontinued=&company=36")
    .check(status.is(200)))

  val changeCompanyToAtari = exec(http("Change company back to Atari")
    .post("/computers/4085?name=Amiga+1000&introduced=&discontinued=&company=20")
    .check(status.is(200)))

}

