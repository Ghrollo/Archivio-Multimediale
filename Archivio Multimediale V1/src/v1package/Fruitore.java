package v1package;

import java.time.LocalDate;

import myutil.BelleStringhe;
import myutil.ConvertiDati;

public class Fruitore
{
	private final static String PRESENTAZIONE = "Scheda riassuntiva di %s";
	private final static String INFO_PERSONALI = " NOME: %s \n COGNOME: %s \n RESIDENZA: %s \n DATA DI NASCITÀ: %s";
	private final static String INFO_ISCRIZIONE = " DATA DI ISCRIZIONE: %s \n DATA DI SCADENZA: %s";
	private final static String INFO_RIDOTTE = "NICKNAME: %s \t ISCRITTO IL: %s "; 
	private final static String DECADE_IL = "\t DECADE IL: %s";
	private final static String DECADUTO_IL = "\t DECADUTO IL: %s";
	private final static String RINNOVABILE = "\t RINNOVABILE";
	private final static String RINNOVO_OK = "il Rinnovo dell'abbonamento è stato eseguito con successo.";
	private final static String RINNOVO_NOT_OK = "impossibile effettuare il rinnovo,la data di scadenza dell'iscrizione deve essere minimo dieci giorni distante per eseguire il rinnovo.";
	
	private String nickname;
	private String password;
	private String nome;
	private String cognome;
	private String residenza;
	private LocalDate dataDiNascita;
	private LocalDate dataDiIscrizione;
	private LocalDate dataDiScadenza;
	
	public Fruitore(String nickname, String psw, String nome, String cognome, String residenza, LocalDate nascita)
	{
		this.nickname = nickname;
		this.password = psw;
		this.nome = nome;
		this.cognome = cognome;
		this.residenza = residenza;
		this.dataDiNascita = nascita;
		this.dataDiIscrizione = LocalDate.now();
		this.dataDiScadenza = dataDiIscrizione.plusYears(5);
		
	}
	
	public boolean isFruitore()
	{
		LocalDate oggi = LocalDate.now();
		LocalDate scaduto = this.dataDiScadenza.plusDays(1);
		
		return oggi.isBefore(scaduto);	
	}
	
	public boolean isRinnovabile()
	{
		LocalDate oggi = LocalDate.now();
		LocalDate rinnovo = this.dataDiScadenza.minusDays(10);
		
		return (isFruitore() && oggi.isAfter(rinnovo));
	}
	
	public boolean isDecaduto()
	{
		return !isFruitore();
	}
	
	public String getNickname()
	{
		return this.nickname;
	}
	
	public void setPassword(String psw)
	{
		this.password = psw;
	}
	
	public void setDecaduto()
	{
		this.dataDiScadenza = LocalDate.now();
	}
	
	public boolean checkPassword(String psw)
	{
		if(this.password.equals(psw))
			return true;
		else
			return false;
	}
	
	public void richiestaRinnovo()
	{
		if(isRinnovabile())
		{
			this.dataDiScadenza.plusYears(5);
			System.out.println(RINNOVO_OK);
		}
		else
			System.out.println(RINNOVO_NOT_OK);
	}
	
	public String descrizione()
	{
		String titolo = String.format(PRESENTAZIONE, getNickname());
		String iscrizione = String.format(INFO_ISCRIZIONE,ConvertiDati.convertiData(this.dataDiIscrizione),ConvertiDati.convertiData(this.dataDiScadenza));
		String infoPersonali = String.format(INFO_PERSONALI,this.nome,this.cognome,this.residenza,ConvertiDati.convertiData(this.dataDiNascita));
		StringBuffer descrizione = new StringBuffer();
		descrizione.append(BelleStringhe.centraIncornica(titolo));
		descrizione.append(BelleStringhe.tabellizza(iscrizione));
		descrizione.append(BelleStringhe.tabellizza(infoPersonali));
		
		String testo = descrizione.toString();
		
		return testo;
		
	}
	
	public String toString()
	{
		String dataIscrizione = ConvertiDati.convertiData(this.dataDiIscrizione);
		String dataDecadenza = ConvertiDati.convertiData(this.dataDiScadenza);
		String testo = String.format(INFO_RIDOTTE,this.nickname,dataIscrizione);
		if(isDecaduto())
			testo = testo + String.format(DECADUTO_IL, dataDecadenza);
		else
			testo = testo + String.format(DECADE_IL, dataDecadenza);
		if( isRinnovabile())
		{
			testo = testo + RINNOVABILE;
		}
		
		return testo;
	}
	
}
