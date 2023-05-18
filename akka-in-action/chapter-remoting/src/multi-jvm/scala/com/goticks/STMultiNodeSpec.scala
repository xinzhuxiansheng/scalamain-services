package com.goticks

import akka.remote.testkit.MultiNodeSpecCallbacks
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatest.BeforeAndAfterAll

trait STMultiNodeSpec extends MultiNodeSpecCallbacks
  with AnyWordSpecLike with Matchers with BeforeAndAfterAll {

  override def beforeAll() = multiNodeSpecBeforeAll()

  override def afterAll() = multiNodeSpecAfterAll()
}
