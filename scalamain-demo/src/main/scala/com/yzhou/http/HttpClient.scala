package com.yzhou.http

import io.circe
import sttp.client3._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import java.util.UUID

object HttpClient extends App {

  val client = SimpleHttpClient()

  try {
    val response: Response[Either[String, String]] = client.send(basicRequest.get(uri"https://chat.api.autohome.com.cn/c1/s1/api/getRecommendUserHeadImageList?targetId=987&targetType=1&timestamp=1672245745778&_appid=club.pc"))

    response.body match {
      case Left(body) => println(s"Non-2xx response to GET with code ${response.code}:\n$body")
      //      case Right(body) => println(zhuanDuixiang(body).result.size)
      case Right(body) => zhuanDuixiang(body)
    }

    println("--- End")
  } finally client.close()

  def zhuanDuixiang(result: String): Unit = {
    val respData = io.circe.parser.decode[RespData](result)
    respData match {
      case Left(error) => throw new IllegalArgumentException
      case Right(data) => println(data.result)
    }
  }

}


