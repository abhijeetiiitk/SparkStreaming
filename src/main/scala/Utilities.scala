object Utilities {

  def setUpLogging() :Unit = {

    import org.apache.log4j.{Level, Logger}
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.ERROR)
  }

  def setUpTwitter() : Unit = {

    import scala.io.Source
    val resourcesPath = getClass.getResourceAsStream("/twitter.txt")
    val lines = Source.fromInputStream(resourcesPath).getLines()
    for(line <- lines) {
      val fields = line.split(' ')
      System.setProperty("twitter4j.outh." + fields(0), fields(1))
    }
  }

}
