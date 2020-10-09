import java.io.{File, PrintWriter}

import org.jsoup.nodes.Document
import org.jsoup.nodes.DocumentType
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URL
import java.net.URI

import play.api.libs.json.{JsValue, Json}


object HTML_Parse {

  // main_function will take uRL And will check all required working then return a data in json formate
  def main_function(url: String):JsValue = {
    val document = Jsoup.connect(url).get()
    val htmlVersion = HTML_version(document)
    val pageTitle = page_title(document)
    val Number_of_headings = check_heading_and_group(document)
    val Number_of_hyperlinks = Check_Hypermedia_links(url, document)
    val Check_for_login = Check_Login(document)
    // println("HTML version is :" + htmlVersion + "\n" + "Page Title :" + pageTitle + "\n" + "" + Number_of_headings + "\n" + "Number of hyperlinks :" + Number_of_hyperlinks + "\n" + "This is login page :" + Check_for_login)

    val map = Map[String, JsValue](
      "HTML version" -> Json.toJson(htmlVersion),
      "Page Title" -> Json.toJson(pageTitle),
      "Headings" -> Json.toJson(Number_of_headings),
      "Number of hypermedia" -> Json.toJson(Number_of_hyperlinks),
      "Login Status" -> Json.toJson(Check_for_login)
    )
    var injson = Json.toJson(map)
    var display: String = Json.stringify(injson)
    val save_file=new PrintWriter(new File(pageTitle))
    save_file.write(display)
    save_file.close()
    println(display)
    println(injson)
    println("done in json formate")
    return injson;

  }
  def page_title(document: Document): String ={
    document.title()
  }

  // will check if page have login ot is this is a login page
  def Check_Login(document: Document): Boolean = {

    !document.select("input[type=password]").isEmpty()

  }
  // will check the heading and make groups of it
  def check_heading_and_group(document: Document): Map[String, Int] = {

    val doc = document.select("h1, h2, h3, h4, h5, h6")


    Map(
      "Total Headings" -> doc.size(),
      "h1" -> doc.select("h1").size,
      "h2" -> doc.select("h2").size,
      "h3" -> doc.select("h3").size,
      "h4" -> doc.select("h4").size,
      "h5" -> doc.select("h5").size,
      "h6" -> doc.select("h6").size)

  }

  //check all link and will arrange them

  def Check_Hypermedia_links(givenURL: String, document: Document): Map[String, Int] = {

    val media_links = document.select("[src]")

    val mediaURLs = media_links.toArray().map { link =>
      link.asInstanceOf[Element].attr("abs:src")
    }

    val links = document.select("a[href]")

    val URLLinks = links.toArray().map { link =>
      link.asInstanceOf[Element].attr("abs:href")
    }

    val imports = document.select("link[href]")

    val importsURLs = imports.toArray().map { link =>
      link.asInstanceOf[Element].attr("abs:href")
    }

    val allURLs = (mediaURLs ++ URLLinks ++ importsURLs).filter {
      !_.isEmpty()
    }

    var external = 0
    var internal = 0
    var aURL = new URL(document.baseUri());
    var domain = aURL.getHost;

    allURLs.map { url =>

      //if (getURI(new URL(url)).isOpaque && !Check_links(givenURL, url)) {
      if(url.contains(domain)){

      external += 1
      } else {
        internal += 1
      }

    }

    Map("Valid Total (Links, Media, Imports)" -> allURLs.size,
      "Valid Links (URLs)" -> links.size,
      "Valid Media (URLs)" -> media_links.size,
      "Valid Imports (URLs)" -> imports.size,
      "Internal Links" -> internal,
      "External Links" -> external)

  }

  //use to check inner and outer links
  def Check_links(givenUrl: String, innerUrl: String): Boolean = {

    if ((new URL(givenUrl).getHost) == ( new URL(innerUrl).getHost) ||
      (new URL(innerUrl).getHost.endsWith(new URL(givenUrl).getHost))) {
      true
    } else {
      false
    }

  }

  def getURI(url: URL): URI = {
    new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null)

  }
  // will check the HTML verison of the given page
  def HTML_version(document: Document): String = {

    document.childNodes().toArray.foreach { node =>

      if (node.isInstanceOf[DocumentType]) {
        val documentType = node.asInstanceOf[DocumentType]
        if (!documentType.attr("publicId").isEmpty && !documentType.attr("systemId").isEmpty()) {
          return "HTML 4"
        } else {
          return "HTML 5"
        }
      }

    }
    return "HTML less than 4"
  }

}