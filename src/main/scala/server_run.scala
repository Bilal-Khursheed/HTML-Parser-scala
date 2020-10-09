import akka.http.scaladsl.server.{Directives, Route}
import io.circe.Json
import org.jsoup.Jsoup
import scala.io.StdIn._
import play.api.libs.json.{JsValue, Json}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

trait Router {
  def route: Route
}

class server_run extends Router with Directives{
  // that will take the input from user inconsole to parse
  println("Kindly enter URL to Parse it")
  //will get URL from user
  var url= readLine()
 // var url ="https://www.spiegel.de/meinspiegel/login.html"
  //will get data of parsed URL in json form
  def get_address(): JsValue= {
    var details = HTML_Parse.main_function(url)
    return details
  }

 //will make routing address
  override def route: Route = pathPrefix("parse"){
    pathEndOrSingleSlash{
      get{
        complete(get_address())
      }
    }
  }
}