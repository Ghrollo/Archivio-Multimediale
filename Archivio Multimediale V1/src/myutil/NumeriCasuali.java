package myutil;
import java.util.Random;

/**
 * Questa classe contiene i metodi riguardanti la generazione di un valore casuale.
 * 
 * @author Marco Miccoli
 * @author Francesco Flamini
 *
 */

public class NumeriCasuali 
{

	/**
	 * Genera un intero random compreso tra i parametri min e max.
	 */
	private static Random rand = new Random();
	
	public static int intRandom(int min,int max)
	{
		int random = rand.nextInt(max - min + 1) + min;
		return random;
	}
}
