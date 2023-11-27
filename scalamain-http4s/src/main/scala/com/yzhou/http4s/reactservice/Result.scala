package com.yzhou.http4s.reactservice

case class Result[T](code: Int, data: T, msg: String)
