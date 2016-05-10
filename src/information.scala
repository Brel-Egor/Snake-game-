import scala.collection.JavaConverters._

object information {
 def box(x: Int): java.lang.Integer = java.lang.Integer.valueOf(x)
 
 
 def parse(toParse: java.util.List[Integer]) : java.util.List[Integer]  = {
 val l1=toParse.asScala.toList
 val l2=List(0,1,2,3)
 l2.map(x => (box(l1.count(_ == x))))
 val l3=l2.map(x => (box(l1.count(_ == x)))).asJava
 l3
 } 
}