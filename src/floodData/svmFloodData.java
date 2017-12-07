package floodData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class svmFloodData {

	public static void main(String[] args) {
	
		if(args.length == 0)
		{
			System.out.println("Not enough arguments.");
			System.exit(0);
		}
		String fileName = args[0];
		
		BufferedReader reader = null;
		ArrayList<String> svmFileLines = new ArrayList<String>();
		double[] avgs = new double[19];
		try {
		    File dataFile = new File(fileName);
		    reader = new BufferedReader(new FileReader(dataFile));

		    for(int i = 0; i < avgs.length; i++)
		    {
		    	avgs[i] = 0.0;
		    }
		    
		    String line;
		    while ((line = reader.readLine()) != null) {
		        
		    	String classLabel = "0";
		    	String[] fields = line.split(",", 19);
		    	String svmString =classLabel + " ";
		    	
		    	String val;
		    	for(int i = 1; i <= fields.length; i++)
		    	{
		    		val = fields[i - 1];
		    		if(val.isEmpty() || val == null)
		    			val = "0";

		    		svmString = svmString + i + ":" + val + " ";
		    		
		    		double numVal = Double.parseDouble(val);
		    		avgs[i - 1] += numVal;
		    	}
		    	
		    	System.out.println(svmString);
		    	svmFileLines.add(svmString);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		System.out.println("Training set size: " + svmFileLines.size());
		for(int k = 0; k < avgs.length; k++)
		{
			System.out.println("avg[" + k + "]=" + (avgs[k] / svmFileLines.size()));
		}
				
		writeFile("floodData_SVM_Training_Set.txt", svmFileLines);
	}
	
	public static void writeFile(String outputFilePath, ArrayList<String> linesOfFile)
	{
		System.out.println("Attempting to create file " + outputFilePath);
		BufferedWriter writer = null;
		File outputFile = new File(outputFilePath);
		
		try 
		{
			if(!outputFile.exists())
				outputFile.createNewFile();
			
			FileWriter fw = new FileWriter(outputFile);
			writer = new BufferedWriter(fw);
			
			for(String S : linesOfFile) //Write the file with print statements.
				writer.write(S + "\n");
			
			System.out.println("File successfully written to " + outputFilePath); //Confirmation.
			
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
