package com.knoldus.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.model.{PostRequest, PostResponse}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val postUrlFormat: RootJsonFormat[PostResponse] = jsonFormat2(PostResponse)
  implicit val postUrlRequest: RootJsonFormat[PostRequest] = jsonFormat1(PostRequest)

}
