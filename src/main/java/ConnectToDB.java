import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

/*
*Write a Java program that does the following*
*accesses the database you created last week,
*creates a document,
*updates a document
*deletes a document.
 */




public class ConnectToDB {





        public static void main( String args[] ) throws InterruptedException {

               MongoClient mongo = new MongoClient();


                //accesses the database you created last week,
                try {
                    mongo = new MongoClient("localhost",27017);
                    System.out.println("Database connected");
                }catch (Exception e){

                    System.out.println("Connection unavailable, check your database url");


                }


                try {
                    MongoDatabase cs485 = mongo.getDatabase("CS485DB");
                    System.out.println("connection to Database successful");
                    try {
                        MongoCollection firstcollection = cs485.getCollection("firstcollection");
                        //creates a document


                        try {
                            Document document = new Document("Name", "Olde_Jamal").append("Grade", "A");

                            firstcollection.insertOne(document);
                            System.out.println("Document inserted");
                        }catch (Exception e){

                            System.out.println("Unable to insert document");
                        }
                        try {
                            //updates a document
                            firstcollection.updateOne(eq("Name", "Olde_Jamal"), combine(set("Name", "Jamal")));
                            System.out.println("document update");
                        }catch (Exception e){
                            System.out.println("Unable to update Document, make sure document exists in Database");

                        }
                        try {
                            //delete a document
                            firstcollection.deleteOne(new BasicDBObject("Name", "Jamal"));
                        }catch (Exception e){

                            System.out.println("Unable to delete document. make sure document exists in database");
                        }
                    }
                    catch (Exception e){
                        System.out.println("Collection not found in database, check name of collection");

                    }

                }
                catch (Exception e){

                    System.out.println("Unable to connect to database");

                }



        }




}

