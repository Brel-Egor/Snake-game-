import scala.collection.JavaConverters._
import scala.annotation.tailrec

object Scala_sort {
  def sortInc(toSort: java.util.List[String]) : java.util.List[String] = {
    var l1 = List[String]()
    var l2 = toSort.asScala
    
   @scala.annotation.tailrec
    def outerLoop(i: Int) {

      if (i < l2.size) {
        var min =1000000
        var pos = -1

        
        def innerLoop(j: Int) {
          if (j < l2.size) {
            if (Integer.parseInt(l2(i))> Integer.parseInt(l2(j)) && Integer.parseInt(l2(j)) < min) {
              min = Integer.parseInt(l2(j))
              pos = j
            }

            innerLoop(j + 2)
          }
        }

        innerLoop(i + 2)

        if (pos != -1) {
          l2=l2.updated(i,l2(pos)).updated(pos,l2(i))
          l2=l2.updated(i-1,l2(pos-1)).updated(pos-1,l2(i-1))
        }

        outerLoop(i + 2)
      }
    }

    outerLoop(1)
    var list=l2.asJava
    list
  }
}
/*map filter foldright foldleft */