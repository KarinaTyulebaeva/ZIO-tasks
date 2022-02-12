
import zio.{ExitCode, Fiber, IO, Task, URIO, ZIO}
import zio.ZIO.effect
import zio.clock.{Clock, currentTime}
import zio.duration.Duration
import zio.stream.ZStream

import scala.io.StdIn._


object Main extends zio.App {

  def printThread = s"[${Thread.currentThread().getName}]"

  def makePower(res: Int, num: Int, wait: Int):Unit = {
    if(res>10000) return
    else {
      println(printThread+"  " + res)
      Thread.sleep(wait * 1000)
      makePower(res * num, num, wait)
    }
  }

    def solve(n: Int): ZIO[Any, Nothing, Any] ={
      if(n==1) {                                            
        return ZIO.succeed(makePower(1, n, 2))
      }
      else {
        ZIO.succeed(makePower(1, n, 2)).zipPar(solve(n-1))
      }
    }
  val N = readInt()

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    solve(N).exitCode
}
