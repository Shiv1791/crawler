package com.knoldus.model

case class PostRequest(urls: List[String])
case class PostResponse(url: String, data: String)
