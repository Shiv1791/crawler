package scala.com.knoldus.routes

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.MessageEntity
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.json.JsonSupport
import com.knoldus.model.{PostRequest, PostResponse}
import com.knoldus.routes.PostRoute
import com.knoldus.service.CrawlerService
import com.knoldus.service.CrawlerService.getResponse
import org.mockito.Mockito.{mock, when}
import org.scalatest.concurrent.ScalaFutures._
import org.scalatest.{Matchers, WordSpecLike}


class PostRouteSpec extends PostRoute with WordSpecLike with JsonSupport
  with ScalatestRouteTest with Matchers {

  "Post routes" should {

    val entity = Marshal(PostRequest(List("https://www.google.com/"))).to[MessageEntity].futureValue

    val invalidEntity =
      Marshal(PostResponse("as","asd", "" )).to[MessageEntity].futureValue

    "return message in case of validRequest " in {
     when(CrawlerService.getResponse(PostRequest(List("https://www.google.com/")))).thenReturn(List(PostResponse("https://www.google.com/", "data", "")))
      Post("/postUrlRoute").withEntity(entity) ~> postUrlRoute ~> check {
        responseAs[String] shouldEqual {
          s""""url": "https://www.google.com/",
             |"data": "data"
             |"error": ""
             |""".stripMargin

        }
      }
    }

    "return default message in case of invalidRequest " in {
      Post("/postUrlRoute").withEntity(invalidEntity) ~> postUrlRoute ~> check {
        responseAs[String] shouldEqual "Does not support"
      }
    }
  }
}
