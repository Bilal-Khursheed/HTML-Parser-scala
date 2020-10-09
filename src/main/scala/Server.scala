import akka.actor.ActorSystem

import akka.stream.ActorMaterializer


import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.util.{Failure, Success}

object Server extends App {
  val host = "0.0.0.0"
  val port = 9000
  implicit val system: ActorSystem = ActorSystem("helloworld")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val router= new server_run();
  val server= new Server_work(router, host,port)
  val binding=server.bind()
  binding.onComplete{
    case Success(_) =>println("Success")
    case Failure(error) => println(s"Failure: ${error.getMessage}")
  }

  Await.result(binding, 3.seconds)





}


