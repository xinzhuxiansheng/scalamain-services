package com.yzhou.json

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object TestJson extends App {

  var str:String = "{\"returncode\":10,\"message\":\"yzhou\",\"result\":{\"list\":[\"https://i2.autoimg.cn/userscenter/g27/M0A/A1/C1/120X120_0_q87_autohomecar__ChsEbGM3y0GARX2kAAH-e7_kXwo506.jpg\",\"https://i3.autoimg.cn/userscenter//g27/M0A/23/A9/120X120_0_q87_autohomecar__ChxkmWOhVxCAcix_AAH81rfOK_c013.jpg\",\"https://i2.autoimg.cn/userscenter/g2/M02/7F/20/120X120_0_q87_autohomecar__ChsEkF3eTCiAAqSsAABI4lYawik278.jpg\"]}}";

  zhuanDuixiang(str)

  def zhuanDuixiang(result: String): Unit = {
    val respData = io.circe.parser.decode[RespData](result)
    respData match {
      case Left(error) => throw new IllegalArgumentException
      case Right(data) => println(data.message)
    }
  }
}
