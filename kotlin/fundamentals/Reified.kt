fun main(){
  val meetings = listOf(BoardMeeting(), FinanceMeeting(), BoardMeeting())
  val board = meetings.typeOf<BoardMeeting>()
  println("count ${board.count()}")

}

inline fun <reified T> List<*>.typeOf(): List<T>{
  val returnList = mutableListOf<T>()
  for(item in this){
    if( item is T){
      returnList.add(item)
    }
  }
  return returnList
}

open class Meeting
class BoardMeeting : Meeting()
class FinanceMeeting : Meeting()