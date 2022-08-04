package com.knoldus.routes

import akka.http.scaladsl.server.Directives.{complete, _}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.knoldus.json.JsonSupport
import com.knoldus.model.PostRequest
import com.knoldus.service.CrawlerService

import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * post route that gave post response according to given input
 *
 * */
trait PostRoute extends JsonSupport {

  lazy val postUrlRoute: Route =
    post {
      path("postUrlRoute") {
        entity(as[PostRequest]) {
          postRequest => {
            complete(CrawlerService.getResponse(postRequest))
          }
        }
      }
    }


  implicit lazy val timeout: Timeout = Timeout(100.seconds)
}
