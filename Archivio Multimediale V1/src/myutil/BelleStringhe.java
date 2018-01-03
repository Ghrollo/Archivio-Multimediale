package myutil;
public class BelleStringhe
{

 private final static String SPAZIO = " ";
 private final static String CORNICE = "--------------------------------------------------"; /* sono 50 caratteri */
 private final static String BORDO = "|";
 private final static String ACAPO = "\n";



 public static String incornicia (String s)
	{ 
	 StringBuffer res = new StringBuffer();
		
	 res.append(CORNICE+ACAPO);
	 res.append(s+ACAPO);
	 res.append(CORNICE+ACAPO);

 	 return res.toString();

  }

 
 public static String incolonna (String s, int larghezza)
	{
	 StringBuffer res = new StringBuffer(larghezza);
	 int numCharDaStampare = Math.min(larghezza,s.length());
	 res.append(s.substring(0, numCharDaStampare));
	 for (int i=s.length()+1; i<=larghezza; i++)
		res.append(SPAZIO);
	 return res.toString();
	}
	
 public static String centrata (String s, int larghezza)
	{
	 StringBuffer res = new StringBuffer(larghezza);
	 if (larghezza <= s.length())
		res.append(s.substring(larghezza));
	 else
		{
		 int spaziPrima = (larghezza - s.length())/2;
		 int spaziDopo = larghezza - spaziPrima - s.length();
		 for (int i=1; i<=spaziPrima; i++)
			res.append(SPAZIO);
			
		 res.append(s);
		
		 for (int i=1; i<=spaziDopo; i++)
			res.append(SPAZIO);
		}
	 	 return res.toString();

	}

	public static String ripetiChar (char elemento, int larghezza)
	 {
		 StringBuffer result = new StringBuffer(larghezza);
		 for (int i = 0; i < larghezza; i++)
			{
			 result.append(elemento);
			}
		 return result.toString();

	 }

	public static String ripetiSpazi (int larghezza)
	 {
		 StringBuffer result = new StringBuffer(larghezza);
		 for (int i = 0; i < larghezza; i++)
			{
			 result.append(SPAZIO);
			}
		 return result.toString();

	 }
	
	public static String rigaIsolata(String daIsolare)
	 {
		 StringBuffer result = new StringBuffer();
		 result.append(ACAPO);
		 result.append(daIsolare);
		 result.append(ACAPO);
		 return result.toString();
	 }
	
	public static String centraIncornica(String s)
	{
		String miaStringa = centrata(s, 50);
		miaStringa = incornicia(miaStringa);
		
		return miaStringa;
		
	}
	
	public static String tabellizza(String s)
	{
		 StringBuffer res = new StringBuffer();
		 String[] lines = s.split("\n");
		 res.append(CORNICE+ACAPO);
		 res.append(BORDO+ripetiSpazi(48)+BORDO+ACAPO);
		 for(int i=0; i < lines.length; i++)
		 {
				 int differenza = 48 - lines[i].length(); //48 è la lunghezza di caratteri scrivibili in una riga della nostra tabella
				 res.append(BORDO+lines[i]+ripetiSpazi(differenza)+BORDO+ACAPO);
		 }
		 res.append(CORNICE+ACAPO);

	 	 return res.toString();

	}
 
}