import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object CSVRead {

  val sparkSession = SparkSession.builder().appName("csvRead").master("local[*]").getOrCreate()
  val sparkConf = new SparkConf().setAppName("csvRead").setMaster("local[*]")
  val streamingContext = new StreamingContext(sparkConf, Seconds(10))

  val schema = StructType(Array(StructField("customerId", StringType),
    StructField("pId",StringType),
    StructField("pName", StringType),
    StructField("date",StringType)))

  //stream the orders from the csv files. //dynamic
  val ordersStreamDF =
  sparkSession.readStream
    .option("header","true")
    .schema(schema)
    .csv("src/main/resources/input/cutomer_info/customer.csv")

  case class Customer(customer_id : String, customer_name: String, customer_location: String)
  //second way

  //static
  val customerDataSet =
    sparkSession
    .read
    .format("csv")
    .option("header","true")
    .load("")
    .map(x => Customer(x.getString(0),x.getString(1),x.getString(2)))

  //Join the streaming dataframe ordersStreamDF with dataset customerDS on the customer_id field.
  //The resultant dataframe is now a streaming dataframe containing the resultant aggregation.
  val finalDF = ordersStreamDF.join(customerDataSet,"customer_id")

  //write the joined stream to json/parquet output.
  finalDF
    .writeStream
  .queryName("count_customer")
    .outputMode(OutputMode.Append())
    .format("Json")
    .partitionBy("date")
    .option("path","")
    .option("checkpointLocation","")
    .start()




}
