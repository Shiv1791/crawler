package com.knoldus.service

import com.knoldus.model.{PostRequest, PostResponse}


object CrawlerService {

  def getResponse(postResponse: PostRequest): List[PostResponse] = {
    postResponse.urls.map { url=>
      val urlData = io.Source.fromURL(url).getLines().next()
      val data = urlData.mkString
      PostResponse(url,  data, "")
    }
  }
}
