package myutil;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class ConvertiDati {
	
	
	public static String doubleDueCifre(double numero)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(numero);
	}
	
	
	public static String convertiData(LocalDate data)
	{
		DateTimeFormatter sf = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String riportaData = data.format(sf);
		return riportaData;
	}
	

	public static String convertiDataConOra(Date data)
	{
		DateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String riportaData = sf.format(data);
		return riportaData;
	}
	
}
