import scala.collection.JavaConverters._
object psevd {
  def choise(choice: String): String = choice match {
    case "0" => "UP"
    case "1" => "DOWN"
    case "2" => "LEFT"
    case "3" => "RIGHT"
    case default => choice
  }

  def parse(toParse: java.util.List[String]) : java.util.List[String]  = {
    val l2=toParse.asScala.toList
    l2.map(choise(_)).asJava
  }
}
          

