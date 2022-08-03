package com.knoldus.routes

import akka.http.scaladsl.server.Directives.{complete, _}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.knoldus.json.JsonSupport
import com.knoldus.model.{PostRequest, PostResponse}
import com.knoldus.service.CalculatorService

import scala.concurrent.duration._
import scala.language.postfixOps

trait PostRoute extends JsonSupport {

  lazy val postUrlRoute: Route =
    post {
      path("postUrlRoute") {
        entity(as[PostRequest]) {
          postResponse => {
            complete(CalculatorService.getResponse(postResponse))
          }
        }
      }
    }


  implicit lazy val timeout: Timeout = Timeout(100.seconds)
}
