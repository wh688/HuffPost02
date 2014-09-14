package Task02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class QueryDriver {
	public static void main(String[] args) throws UnknownHostException {
		DB dB = (new MongoClient("localhost", 27017)).getDB("HuffPostTask02");
		DBCollection dBCollection = dB.getCollection("Channel");
		DBCursor dBCursor = dBCollection.find();
		DBObject jsonObject;
		try {
			File file = new File("output.csv");
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			fw.append("SSN");
			fw.append(",");
			fw.append("name");
			fw.append(",");
			fw.append("age");
			fw.append(",");
			fw.append("gender");
			fw.append(",");
			fw.append("department");
			fw.append("\n");

			while (dBCursor.hasNext())
			{
				jsonObject = dBCursor.next();
				fw.append(jsonObject.get("SSN").toString());
				fw.append(",");
				fw.append(jsonObject.get("name").toString());
				fw.append(",");
				fw.append(jsonObject.get("age").toString());
				fw.append(",");
				fw.append(jsonObject.get("gender").toString());
				fw.append(",");
				fw.append(jsonObject.get("department").toString());
				fw.append("\n");
				//System.out.println("Done");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
