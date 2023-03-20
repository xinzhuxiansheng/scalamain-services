package ch1.scala

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.language.postfixOps

class AkkademyDbSpec extends AnyFunSpecLike with Matchers with BeforeAndAfterEach {

  implicit val system = ActorSystem()
//  implicit val timeout = Timeout(5 seconds)

  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        // Akka Testkit创建一个TestActorRef，提供同步API，并且允许我们访问其指向的Actor
        val actorRef = TestActorRef(new AkkademyDb)
        // tell
        actorRef ! SetRequest("key", "value")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }
}
