package v1package;

import java.util.Vector;

public class ArchivioFruitori
{
	private final static String INTESTAZIONE_FRUITORI = "LISTA COMPLETA DEI SOLI FRUITORI (COMPRESI QUELLI IN STATO DI RINNOVO)";
	private final static String INTESTAZIONE_RINNOVABILI = "LISTA COMPLETA DEI SOLI FRUITORI IN STATO DI POSSIBILE RINNOVO";
	private final static String INTESTAZIONE_DECADUTI = "LISTA COMPLETA DEGLI EX-FRUITORI ORMAI DECADUTI";
	private final static String LISTA_VUOTA = "NON VI E' ALCUN FRUITORE NELL'ARCHIVIO SELEZIONATO";
	
	private Vector <Fruitore> archivioFruitori;
	private Vector <Fruitore> archivioDecaduti;
	private String password;
	
	public ArchivioFruitori()
	{
		this.archivioFruitori = new Vector <Fruitore>();
		this.archivioDecaduti = new Vector <Fruitore>();
		this.password = "comandoio";

	}
	
	public boolean isOmonimo(String nickname)
	{
		Fruitore fruitore;
		int i;
		for(i = 0; i < this.archivioFruitori.size(); i++)
		{
			fruitore = this.archivioFruitori.get(i);
			if(fruitore.getNickname().equals(nickname))
				return true;
		}
		return false;
	}
	
	
	public Fruitore getFruitore(String nickname)
	{
		Fruitore fruitore = null;
		int i;
		for(i = 0; i < this.archivioFruitori.size(); i++)
		{	
			fruitore = this.archivioFruitori.get(i);
			if(fruitore.getNickname() == nickname)
				break;
		}
		
		return fruitore;
	}
	
	
	public void stampaFruitori()
	{
		if(!archivioFruitori.isEmpty())
		{
			System.out.println(INTESTAZIONE_FRUITORI);
			for(int i = 0; i < this.archivioFruitori.size(); i++)
			{
				Fruitore fruitore = archivioFruitori.get(i);
				if(fruitore.isFruitore())
				{
					String descrizione = fruitore.toString();
					System.out.println(descrizione);
				}
			}
		}
		else System.out.println(LISTA_VUOTA);
	}
	
	public void stampaRinnovabili()
	{
		if(!isRinnovabiliEmpty() && !archivioFruitori.isEmpty())
		{
			System.out.println(INTESTAZIONE_RINNOVABILI);
			for(int i = 0; i < this.archivioFruitori.size(); i++)
			{
				Fruitore fruitore = archivioFruitori.get(i);
				if(fruitore.isRinnovabile())
				{
					String descrizione = fruitore.toString();
					System.out.println(descrizione);
				}
			}
		}
		else System.out.println(LISTA_VUOTA);
	}
	
	public void stampaDecaduti()
	{
		if(!archivioDecaduti.isEmpty())
		{
			System.out.println(INTESTAZIONE_DECADUTI);
			for(int i = 0; i < this.archivioDecaduti.size(); i++)
			{
				Fruitore fruitore = archivioDecaduti.get(i);
				String descrizione = fruitore.toString();
				System.out.println(descrizione);
				
			}
		}
		else System.out.println(LISTA_VUOTA);
	}
	
	public boolean isRinnovabiliEmpty()
	{
		boolean vuoto = false;
		for(int i = 0; i < this.archivioFruitori.size(); i++)
		{
			Fruitore fruitore = archivioFruitori.get(i);
			if(fruitore.isRinnovabile())
			break;
			vuoto = true;
		}
		
		return vuoto;
	}
	
	public void setPassword(String psw)
	{
		this.password = psw;
	}
	
	public boolean checkPassword(String psw)
	{
		if(this.password.equals(psw))
			return true;
		else
			return false;
	}
	
	public void aggiungiFruitore(Fruitore fruitore)
	{
		archivioFruitori.add(fruitore);
	}
	
	public void rimozioneFruitore(Fruitore fruitore)
	{
		fruitore.setDecaduto();
		archivioDecaduti.add(fruitore);
		archivioFruitori.remove(fruitore);
	}
	
	public void rimozioneDecaduti()
	{
		for(int i = 0; i < this.archivioFruitori.size(); i++)
		{
			Fruitore fruitore = archivioFruitori.get(i);
			if(fruitore.isDecaduto())
			{
				fruitore.setDecaduto();
				archivioDecaduti.add(fruitore);
				archivioFruitori.remove(i);
			}
			
		}
	}
}
