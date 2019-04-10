  // Safe Call ?. m?.method()
  // Elvis ?: meeting = m ?: Meeting()
  // Safe cast (as?) val saveable = - as? ISaveable
  // Not-Null m!!.close()
fun main(){
  var m:Meeting? = null
  var meeting = m ?: Meeting()
  meeting.save(object:Saveable {
    override fun save(){
      println("Saving")
    }
   })
  closeMeeting(meeting)

  // This is a nice way to check for null when 
  // dealing with function that does not allow null
  m?.let{
    closeMeetingNonNull(m)
    println("This block is never invoked")
  }
}

fun closeMeeting(m:Meeting?):Boolean?{
  // return if(m?.canClose==true) m?.close()
  // else false
  return if(m!!.canClose) m!!.close()
  else false
}

fun closeMeetingNonNull(m:Meeting):Boolean{
  return if(m.canClose) m.close()
  else false
}

class Meeting(){
  val canClose = false
  lateinit var location:Location
  fun close():Boolean{
    return true
  }

  fun save(o:Any){
    val saveable = o as? Saveable
    saveable?.save()
  }

  fun init(location:Location){
    this.location=location
  }
}

interface Saveable{
  fun save()
}

class Location{}