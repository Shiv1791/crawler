package scala.com.knoldus.routes

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.MessageEntity
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.json.JsonSupport
import com.knoldus.model.{PostRequest, PostResponse}
import com.knoldus.routes.PostCrawlerRoute
import com.knoldus.service.CrawlerService
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures._
import org.scalatest.{Matchers, WordSpecLike}


class PostRouteSpec extends PostCrawlerRoute with WordSpecLike with JsonSupport
  with ScalatestRouteTest with Matchers {

  "Post routes" should {

    val entity = Marshal(PostRequest(List("https://www.google.com/"))).to[MessageEntity].futureValue

    val invalidEntity =
      Marshal(PostResponse("","asd", "" )).to[MessageEntity].futureValue

    "return message in case of validRequest " in {
      when(CrawlerService.getResponse(PostRequest(List("https://www.google.com/")))).thenReturn(List(PostResponse("https://www.google.com/", "data", "")))
      Post("/postUrlRoute").withEntity(entity) ~> postCrawlerRoute ~> check {
        responseAs[List[String]] shouldEqual {
          s""""url": "https://www.google.com/",
             |"data":
             |"error": ""
             |""".stripMargin

        }
      }
    }

    "return default message in case of invalidRequest " in {
      Post("/postUrlRoute").withEntity(invalidEntity) ~> postCrawlerRoute ~> check {
        responseAs[String] shouldEqual "Does not support"
      }
    }
  }
}
