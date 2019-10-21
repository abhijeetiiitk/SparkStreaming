import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object PrintTweets {

  def main(args: Array[String]) : Unit = {

    Utilities.setUpTwitter()

    val conf = new SparkConf().setAppName("PrintTweet")
    conf.setMaster("local[*]")

    //Set up SparkStreaming context named "PrintTweets" that runs locally
    //using all CPU cores and one second batch of data
    val streamingContext = new StreamingContext(conf, Seconds(1))

    Utilities.setUpLogging()

    //Create a DStream from Twitter using Streaming context
    val tweets = TwitterUtils.createStream(streamingContext, None)

    //extract the text of each status update into RDDs using map
    val status = tweets.map(tweet => tweet.getText)

    status.print(10)

    streamingContext.stop()

    streamingContext.awaitTermination()
  }

}
