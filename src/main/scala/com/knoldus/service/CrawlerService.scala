package com.knoldus.service

import com.knoldus.model.{PostRequest, PostResponse}


object CrawlerService {

  //get the data from the web page of given url
  def getResponse(postResponse: PostRequest): List[PostResponse] = {
    postResponse.urls.map {
      case url: String =>
      val urlData = io.Source.fromURL(url).getLines().next()
      val data = urlData.mkString
      PostResponse(url,  data, "")
      case _=> throw new Exception("Does not support")
    }
  }
}
