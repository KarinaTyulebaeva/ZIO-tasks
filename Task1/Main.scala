import zio.{ExitCode, URIO, ZIO}
import zio.ZIO.effect
import zio.clock.{Clock, currentTime}
import zio.duration.Duration
import zio.stream.ZStream

import scala.io.StdIn._
import java.text.SimpleDateFormat
import java.util.Calendar


object Main extends zio.App{

  val now = Calendar.getInstance().getTime()
  val minuteFormat = new SimpleDateFormat("mm")
  val hourFormat = new SimpleDateFormat("hh")

  val currentHour = hourFormat.format(now)
  val currentMinute = minuteFormat.format(now)

  def printCurrentTime(time: Int)={
    while(true){
      println(currentHour+":"+currentMinute)
      Thread.sleep(time*1000)
    }
  }
  val zprintTime = ZIO.effectTotal(printCurrentTime(readInt()))

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = zprintTime.exitCode
}
