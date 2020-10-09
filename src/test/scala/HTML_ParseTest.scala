import org.jsoup.Jsoup
import org.scalatest.FunSuite

class HTML_ParseTest extends FunSuite {

  test("Check Login"){
    val add= Jsoup.connect("https://www.google.com").get()
    assert(HTML_Parse.Check_Login(add) === false)
  }
  test("Check Login2"){
    val add= Jsoup.connect("https://account.genndi.com/login").get()
    assert(HTML_Parse.Check_Login(add) === true)
  }
  test("Check Login3"){
    val add= Jsoup.connect("https://www.spiegel.de/meinspiegel/login.html").get()
    assert(HTML_Parse.Check_Login(add) === true)
  }
  test("Check version2"){
    val add= Jsoup.connect("https://www.spiegel.de/meinspiegel/login.html").get()
    assert(HTML_Parse.HTML_version(add) === "HTML 4")
  }

  //https://www.spiegel.de/meinspiegel/login.html
  test("Check Version"){
    val add= Jsoup.connect("https://www.google.com").get()
    assert(HTML_Parse.HTML_version(add) === "HTML 5")
  }
  test("Check Title"){
    val add= Jsoup.connect("https://www.google.com").get()
    assert(HTML_Parse.page_title(add) === "Google")
  }
  test("check headings"){
    val add= Jsoup.parse("<html><h1></h1><h2></h2></html>")
    val map=Map("Total Headings" -> 2,
      "h1" -> 1,
      "h2"-> 1,
      "h3"-> 0,
      "h4"-> 0,
      "h5"-> 0,
      "h6"-> 0)
    assert(HTML_Parse.check_heading_and_group(add) === map)
  }
  test("check headings 2"){
    val add= Jsoup.parse("<html> <h1></h1> <h2></h2> <h3></h3> <h5></h5> <h1></h1> <h4></h4> </html>")
    val map=Map("Total Headings" -> 6,
      "h1" -> 2,
      "h2"-> 1,
      "h3"-> 1,
      "h4"-> 1,
      "h5"-> 1,
      "h6"-> 0)
    assert(HTML_Parse.check_heading_and_group(add) === map)
  }

}
