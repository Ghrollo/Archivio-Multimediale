package myutil;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Questa classe viene utilizzata per la registrazione di dati da parte dell'utente che
 * interagisce con il programma, consiste infatti di tutti metodi 'static' che utilizzano
 * oggetti della classe Scanner per acquisire dati da tastiera.
 * @author Francesco Flamini
 *
 */
public class InputDati 

{
	private static String nome;
	private static Scanner in;
	private static int valScelto;
	private static int scelta;
	private static Scanner lettore = creaScanner();
	private final static String ERRORE_STRINGA_VUOTA= "Attenzione: non hai inserito alcun carattere";
	private static final String MESS_ERRORE= "Errore nell'inserimento della scelta,riselenzionate una scelta valida";
	private static final String MESS_ERRORE_FORMATO = "Errore formato,reinserire:";
	private final static String MESSAGGIO_AMMISSIBILI= "Attenzione: i caratteri ammissibili sono: ";
	private final static String INSERISCI_GIORNO = "Inserisci il giorno: ";
	private final static String INSERISCI_MESE = "Inserisci il mese: ";
	private final static String INSERISCI_ANNO = "Inserisci l'anno: ";
	private final static String INSERISCI_ORA = "Inserisci l'ora: ";
	private final static String INSERISCI_MINUTI = "Inserisci i minuti: ";
	private final static String ERRORE_CHAR = "Inserire un carattere";
	private final static String ERRORE_STRINGA_LIMTATA = "Attenzione inserire unicamente %d caratteri per completare la richiesta.";
	private final static String ERRORE_STRINGA_COMPRESA_TRA = "Attenzione! puoi inserire da un minimo di %d caratteri fino ad un massimo di %d caratteri";
	private final static String ERRORE_STRINGA_SEDICI_CHAR = "Attenzione il codice fiscale pu� contenere unicamente 16 caratteri.";
	private final static String ERRORE_NICKNAME = "Attenzione! puoi inserire da un minimo di %d caratteri ad un massimo di %d , esclusi caratteri speciali!";
	private final static String CODICE_FISCALE_NON_VALIDO = "Attenzione: codice fiscale non valido. ";
	private final static String ERRORE_DATA_PRIMA_OGGI = "inserire una data maggiore di oggi";
	private final static String ERRORE_DATA_NON_COMPRESA = "La data dev'essere compresa tra %s e  %s";
	private final static String ERRORE_SOLO_MAGGIORENNI = "mi dispiace ma non risulti maggiorenne, condizione necessaria per completare la richiesta.";
	private final static DateTimeFormatter GIORNOMESEANNO = DateTimeFormatter.ofPattern("dd LLLL yyyy");


	
	
	
	 private static Scanner creaScanner ()
	  {
	   Scanner creato = new Scanner(System.in);
	   creato.useDelimiter(System.getProperty("line.separator"));
	   return creato;
	  }
	 
	/**
	 * Questo metodo chiede all'utente l'inserimento di un nome.
	 * In caso non venga inserito alcun nome la richiesta viene riproposta all'utente finch�
	 * non inserisce un nome valido.
	 * @param chiediNome il messaggio di richiesta per l'inserimento del nome.
	 * @return l'effettivo nome che abbiamo scelto.
	 */
	public static String registraNome(String chiediNome)
	{
		do
		{
			System.out.print(chiediNome);
			in=new Scanner(System.in);
			nome=in.nextLine();
			nome= nome.trim();
		}
		while(nome.length() == 0);
		return nome;
	}
	
	public static String leggiStringa (String messaggio)
	  {
		  System.out.print(messaggio);
		  return lettore.next();
	  }
	
	  public static String leggiStringaLimitata(String messaggio, int limite)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 if (lettura.length() == limite)
		  finito=true;
		 else
		  System.out.println(String.format(ERRORE_STRINGA_LIMTATA, limite));
	   } while (!finito);
	   
	   return lettura;
	  }
	  /* metodo simile a leggiStringaLimitata ma indirizzato a nickname con un limite di caratteri impostato 
	   * e l'esclusione di simboli (caratteri speciali)
	   * 
	   */
	  public static String leggiNickname(String messaggio, int min, int max)
	  {
		  
	   Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 Matcher m = p.matcher(lettura);
		 boolean b = m.find();
		 if (lettura.length() >= min && lettura.length() <= max && !b)
		  finito=true;
		 else
		  System.out.println(String.format(ERRORE_NICKNAME, min, max));
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  /* metodo uguale a leggiNickname ma con un eccezione che passa attraverso
	   * i controlli.
	   */
	  public static String leggiNickname(String messaggio, int min, int max, String eccezione)
	  {
		  
	   Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 Matcher m = p.matcher(lettura);
		 boolean b = m.find();
		 if(lettura.toUpperCase().equals(eccezione.toUpperCase()))
			 return lettura;
		 if (lettura.length() >= min && lettura.length() <= max && !b)
		  finito=true;
		 else
		  System.out.println(String.format(ERRORE_NICKNAME, min, max));
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  /* Questo metodo è stato pensato principalmente per richieste come l'inserimento di password
	   * dove caratteri speciali,inclusi spazi, sono ammessi e per questo è stato scelto
	   * di non tarare la stringa di lettura con un trim().
	   */
	  public static String leggiStringaCompresaTra(String messaggio, int min, int max)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 if (lettura.length() <= max && lettura.length() >= min)
		  finito=true;
		 else
		  System.out.println(String.format(ERRORE_STRINGA_COMPRESA_TRA, min, max));
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  /* Questo metodo è identico a leggiStringaCompresaTra
	   * ma trapassa una stringa di eccezione.
	   */
	  public static String leggiStringaCompresaTra(String messaggio, int min, int max, String eccezione)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 if(lettura.toUpperCase().equals(eccezione.toUpperCase()))
			 return lettura;
		 if (lettura.length() <= max && lettura.length() >= min)
		  finito=true;
		 else
		  System.out.println(String.format(ERRORE_STRINGA_COMPRESA_TRA, min, max));
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  public static LocalDate dopoOggi()
	  {
		  LocalDate data;
		  boolean termine = false;
		do
		{
		  data = leggiData();
		  LocalDate oggi = LocalDate.now();
		  if(data.isAfter(oggi))
			  termine = true;
		  else System.out.println(ERRORE_DATA_PRIMA_OGGI);
		}
		while(!termine);
			  return data;
	  }
	  
	  /* metodo vecchio con Date
	   * public static Date dopoOggi()
	  {
		  Date data;
		  boolean termine = false;
		do
		{
		  data = leggiDataConOra();
		  Date oggi = new Date ();
		  if(data.after(oggi))
			  termine = true;
		  else System.out.println(ERRORE_DATA_PRIMA_OGGI);
		}
		while(!termine);
			  return data;
	  }
	  */
	  public static LocalDate dataCompresaTra(LocalDate datamin, LocalDate datamax)
	  {
		  LocalDate data;
		  boolean termine = false;
		do
		{
		  data = leggiData();
		  if(data.isBefore(datamax) && data.isAfter(datamin))
			  termine = true;
		  else System.out.println(String.format(ERRORE_DATA_NON_COMPRESA, datamin.format(GIORNOMESEANNO), datamax.format(GIORNOMESEANNO)));
		}
		while(!termine);
			  return data;
	  }
	  
	  /* metodo vecchio con Date
	   * public static Date dataCompresaTra(Date datamin, Date datamax)
	  {
		  Date data;
		  boolean termine = false;
		do
		{
		  data = leggiDataConOra();
		  if(data.before(datamax) && data.after(datamin))
			  termine = true;
		  else System.out.println(String.format(ERRORE_DATA_NON_COMPRESA, convertiData(datamin), convertiData(datamax)));
		}
		while(!termine);
			  return data;
	  }
	  */
	  public static String leggiCodiceFiscale(String messaggio)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 if (lettura.length() == 16)
		 {
			 if(lettura.substring(0,6).matches("[a-zA-Z]+") && lettura.substring(6,8).matches("[0-9]+") && lettura.substring(8,9).matches("[a-zA-Z]+") && lettura.substring(9,11).matches("[0-9]+") && lettura.substring(11,12).matches("[a-zA-Z]+")  && lettura.substring(12,15).matches("[0-9]+") && lettura.substring(15).matches("[a-zA-Z]+"))				 finito=true;
			 else
				 System.out.println(CODICE_FISCALE_NON_VALIDO);
		 }
		 else
		  System.out.println(ERRORE_STRINGA_SEDICI_CHAR);
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	/**
	 * Questo metodo chiede all'utente di inserire un numero intero compreso tra '1' e '3'.
	 * Se l'utente inserisce un valore non compreso nell'intervallo da 1 a 3, viene stampato 
	 * a video un messaggio di errore e, successivamente viene riproposta la richiesta 
	 * finch? non viene inserito un valore valido.
	 * @param presentazioneAzione Messaggio di presentazione della selezione di azioni.
	 * @param richiestaAzione Messaggio di presentazione delle possibili azioni eseguibili. 
	 * @return Il numero corrispondete all'azione selezionata.
	 */
	public static int sceltaAzione(String presentazioneAzione, String richiestaAzione)
	{
		System.out.println(presentazioneAzione);
		in=new Scanner(System.in);
		do
		{
			System.out.println(richiestaAzione);
			scelta=in.nextInt();
			if(scelta != 1 && scelta != 2 && scelta != 3 )
			System.out.println(MESS_ERRORE);
		}
		while(scelta != 1 && scelta != 2 && scelta != 3 );
		return scelta;
	}
	
//-------------------------------------------------------------------------------------------------
	
	public static char leggiCarattere(String chiediCarattere)
	{
		boolean finito = false;
		   char valoreLetto = '\0';
		   do
		    {
		     System.out.print(chiediCarattere);
		     String lettura = lettore.next();
		     if (lettura.length() == 1)
		      {
		       valoreLetto = lettura.charAt(0);
		       finito = true;
		      }
		     else
		      {
		       System.out.println(ERRORE_CHAR);
		      }
		    } while (!finito);
		   return valoreLetto;
	}
	
	public static char leggiUpperChar (String messaggio, String ammissibili)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	   {
	    valoreLetto = leggiCarattere(messaggio);
	    valoreLetto = Character.toUpperCase(valoreLetto);
	    if (ammissibili.indexOf(valoreLetto) != -1)
		 finito  = true;
	    else
	     System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
	   } while (!finito);
	   return valoreLetto;
	  }
	  
	public static LocalDate leggiData(String messaggio)
	  {
			System.out.println(messaggio);
		    int giorno = leggiIntero(INSERISCI_GIORNO,1,30);
		    int mese = leggiIntero(INSERISCI_MESE,1,12);
		    int anno = leggiIntero(INSERISCI_ANNO,0);

		    LocalDate date= LocalDate.of(anno, mese, giorno);
			
		    
		    return date;
	  }
	
	public static LocalDate soloMaggiorenni(String messaggio)
	  {
			System.out.println(messaggio);
			boolean fine = false;
			LocalDate date;
		    do
		    {
		    	int giorno = leggiIntero(INSERISCI_GIORNO,1,30);
		    	int mese = leggiIntero(INSERISCI_MESE,1,12);
		    	int anno = leggiIntero(INSERISCI_ANNO,0);
		    	date = LocalDate.of(anno, mese, giorno);
		    	LocalDate limite = LocalDate.now().minusYears(18).plusDays(1);
		    	if(date.isBefore(limite))
		    		fine = true;
		    	else
		    		System.out.println(ERRORE_SOLO_MAGGIORENNI);
		    }
		    while(!fine);
		    
		    return date;
	  }
	
	public static LocalDate leggiData()
	  {
		    int giorno = leggiIntero(INSERISCI_GIORNO,1,30);
		    int mese = leggiIntero(INSERISCI_MESE,1,12);
		    int anno = leggiIntero(INSERISCI_ANNO,0);

		    LocalDate date= LocalDate.of(anno, mese, giorno);
			
		    
		    return date;
	  }
	
	/* metodo vecchio con Date
	 * public static Date leggiData()
	  {
		  SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");

		    String str = leggiIntero(INSERISCI_GIORNO,1,30) + "-" + leggiIntero(INSERISCI_MESE,1,12) +  "-" + leggiIntero(INSERISCI_ANNO,0);

		    Date date= null;
			try 
			{
				date = sf.parse(str);
			} 
			catch (ParseException e) 
			{
				System.out.println(MESS_ERRORE_FORMATO);
			}

		    return date;
	  }
	  */
	
	
	public static Date leggiDataConOra()
	  {
		  SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		    String str = leggiIntero(INSERISCI_GIORNO,1,30) + "-" + leggiIntero(INSERISCI_MESE,1,12) +  "-" + leggiIntero(INSERISCI_ANNO,0) + " " + leggiIntero(INSERISCI_ORA,0,23) + ":" + String.format("%02d", leggiIntero(INSERISCI_MINUTI,0,59));

		    Date date= null;
			try 
			{
				date = sf.parse(str);
			} 
			catch (ParseException e) 
			{
				System.out.println(MESS_ERRORE_FORMATO);
			}

		    return date;
	  }
	

	public static int leggiIntero(int valMin,int valMax)
	{
		in = new Scanner(System.in);
		boolean termine = false;
		
		do
		{	
			try
			{
				valScelto=in.nextInt();
				if(valScelto >= valMin && valScelto <= valMax)
					termine = true;
				else
					System.out.println("Input non valido,inserire un numero intero compreso tra " + valMin + " e " + valMax);
			}
			catch(InputMismatchException e)
			{
				System.out.println(MESS_ERRORE_FORMATO);
				in.next();
			}
		}
		while(!termine);
		return valScelto;
	}
	
	public static int leggiIntero(String messaggio,int valMin,int valMax)
	{
		System.out.println(messaggio);
		
		in = new Scanner(System.in);
		boolean termine = false;
		
		do
		{
			try
			{
				valScelto=in.nextInt();
				if(valScelto >= valMin && valScelto <= valMax)
					termine = true;
				else
					System.out.println("Input non valido,inserire un numero intero compreso tra " + valMin + " e " + valMax);
			}
			catch(InputMismatchException e)
			{
				System.out.println(MESS_ERRORE_FORMATO);
				in.next();
			}
			
		}
		while(!termine);
		return valScelto;
	}
	
	public static int leggiIntero()
	{
		in = new Scanner(System.in);
		
		try
		{
			valScelto = in.nextInt();
		}
		catch(InputMismatchException e)
		{
			System.out.println(MESS_ERRORE_FORMATO); //In caso d'eccezione il metodo ritorna il valore
													 //di default di valScelto (0)	
			in.next();
		}
		return valScelto;
	}
	
	public static int leggiIntero(String messaggio,int valMin)
	{
		System.out.println(messaggio);
		
		boolean termine = false;
		in = new Scanner(System.in);
		
		do
		{
			try
			{
				valScelto = in.nextInt();
				if(valScelto > valMin)
					termine = true;
				else
					System.out.println("Input non valido,inserire un numero intero maggiore o uguale a " + valMin);
				
			}
			catch(InputMismatchException e)
			{
				System.out.println(MESS_ERRORE_FORMATO);
				in.next();
			}
			
		}while(!termine);
		
		return valScelto;
	}
	
	 public static double leggiDouble (String messaggio)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     try
	      {
	       valoreLetto = lettore.nextDouble();
	       finito = true;
	      }
	     catch (InputMismatchException e)
	      {
	       System.out.println(MESS_ERRORE_FORMATO);
	       String daButtare = lettore.next();
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	
	public static int [] leggiArray(String messaggio,int dimensioneArray)
	{
		System.out.println(messaggio);
		
		int [] Array = new int [dimensioneArray];
		for(int i = 0;i < dimensioneArray;i++)
		{
			System.out.println("Valore " + (i + 1) + ":");
			Array[i] = in.nextInt();
		}
		
		return Array;
	}
	
}
