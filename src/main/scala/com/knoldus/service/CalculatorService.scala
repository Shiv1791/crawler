package com.knoldus.service

import com.knoldus.model.{PostRequest, PostResponse}


object CalculatorService {

  def getResponse(postResponse: PostRequest): List[PostResponse] = {
    postResponse.urls.map{ url=>
      val urlData = io.Source.fromURL(url)
      val data = urlData.mkString
      urlData.close()
      PostResponse(url,  data)
    }
  }
}
