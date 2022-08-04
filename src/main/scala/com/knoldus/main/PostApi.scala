package com.knoldus.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.knoldus.routes.PostCrawlerRoute
import com.knoldus.util.Constants.DEFAULT_PORT
import com.knoldus.util.RoutesConfig

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn
import scala.util.Try

object PostApi extends PostCrawlerRoute with App {


  implicit val system: ActorSystem = ActorSystem("Post-Actor-System")
  val host = Try(RoutesConfig.getValue("host")).toOption.getOrElse("localhost")
  val port = RoutesConfig.getIntOpt("port").getOrElse(DEFAULT_PORT)
  val route = postCrawlerRoute
  val bindingFuture = Http().bindAndHandle(route, host, port)

  println(s"Server online at http://" + s"$host" + s":$port" + "\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
