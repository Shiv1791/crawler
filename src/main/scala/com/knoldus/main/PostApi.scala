package com.knoldus.main

import akka.actor.ActorSystem
import akka.http.javadsl.server.directives.LogEntry.info
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.knoldus.routes.PostRoute
import com.knoldus.util.Constants.DEFAULT_PORT
import com.knoldus.util.RoutesConfig

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn
import scala.util.Try

object PostApi extends PostRoute with App {


  implicit val system: ActorSystem = ActorSystem("Post-Actor-System")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val host = Try(RoutesConfig.getValue("host")).toOption.getOrElse("localhost")
  val port = RoutesConfig.getIntOpt("port").getOrElse(DEFAULT_PORT)
  val route = postUrlRoute
  val bindingFuture = Http().bindAndHandle(route, host, port)

  info(s"Server online at http://" + s"$host" + s":$port" + "\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
