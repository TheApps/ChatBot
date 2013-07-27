package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Scanner;

import core.database.MatrixDatabase;
import core.database.SpecialDatabase;
import core.processing.StringProcessor;

public class Run
{
	public static boolean end = false;
	public static boolean debug = false;
	public static SpecialDatabase sd;
	public static MatrixDatabase md;
	public static StringProcessor sp;
	public static Scanner scan;
	private static int toggle = 0;
	public static final String ROOT_DIRECTORY = "src/core";
	public static final String DATABASE_DIRECTORY = ROOT_DIRECTORY + "/database";
	
	public static void main(String[] args)
	{
		scan = new Scanner(System.in);
		sp = new StringProcessor();
		File file = new File(DATABASE_DIRECTORY);
		File file2 = new File("log_" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "_" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "_" + Calendar.getInstance().get(Calendar.YEAR) + "_" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "_" + Calendar.getInstance().get(Calendar.MINUTE) + "_" + Calendar.getInstance().get(Calendar.SECOND) + ".txt");
		if(!file.exists())
		{
			file.mkdir();
		}
		if(!file2.exists())
		{
			try
			{
				file2.createNewFile();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		initDatabases();
		System.out.println("chatbot ready");
		if(debug)
		{
			System.err.println("WARNING: debug mode on");
		}
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file2, false));
			while(!end && !debug)
			{
				String input = scan.nextLine();
				bw.write("User: " + input);
				bw.newLine();
				bw.flush();
				String output = sp.processString(input);
				bw.write("Bot: " + output);
				bw.newLine();
				bw.flush();
				System.out.println(output);
			}
			bw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		while(!end && debug)
		{
			switch(toggle)
			{
			case 0:
				specialDebug();
				break;
			case 1:
				matrixDebug();
				break;
			default:
				toggle = 0;
				specialDebug();
			}
		}
	}
	
	public static void initDatabases()
	{
		sd = new SpecialDatabase();
		md = new MatrixDatabase();
	}
	
	public static void specialDebug()
	{
		String input = scan.nextLine();
		if(input.equals("END"))
		{
			end = true;
		}
		else if(input.equals("toggle."))
		{
			toggle++;
			System.out.println("mode switched to " + toggle);
		}
		else if(input.startsWith("delete."))
		{
			int index;
			try
			{
				index = Integer.valueOf(input.substring(7));
			}
			catch(Exception e)
			{
				index = sd.size();
			}
			sd.remove(index - 1);
		}
		else
		{
			sd.add(input);
			sd.saveSpecialDatabase();
		}
	}
	
	public static void matrixDebug()
	{
		String input = scan.nextLine();
		if(input.equals("END"))
		{
			end = true;
		}
		else if(input.equals("toggle."))
		{
			toggle++;
			System.out.println("mode switched to " + toggle);
		}
		else if(input.startsWith("delete."))
		{
			int index;
			try
			{
				index = Integer.valueOf(input.substring(7));
			}
			catch(Exception e)
			{
				index = md.size();
			}
			md.remove(index - 1);
		}
		else
		{
			md.add(input);
			md.saveMatrixDatabase();
		}
	}
}