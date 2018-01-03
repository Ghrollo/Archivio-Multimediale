package myutil;

import java.util.ArrayList;

public class MyMenu 
{
	
public static int sceltaMultipla(String... scelte)
{
	int i;
	for(i=0;i < scelte.length;i++)
	{
		System.out.println(i + 1 +")  " + scelte[i]);
	}
	return InputDati.leggiIntero(1,i);
	
}


public static int sceltaMultipla(ArrayList <String> scelte)
{
int i;
for(i=0;i < scelte.size() ;i++)
{
	System.out.println(i + 1 +")  " + scelte.get(i));
}
return InputDati.leggiIntero(1,i);

}

public static String restituisciScelta(String... scelte)
{

	int i;
	int k;
	for(i=0;i < scelte.length;i++)
	{
		System.out.println(i + 1 +")  " + scelte[i]);
	}
	k = InputDati.leggiIntero(1,i) -1;
	return scelte[k];
}

public static String restituisciScelta(ArrayList <String> scelte)
{

	int i;
	int k;
	for(i=0;i < scelte.size();i++)
	{
		System.out.println(i + 1 +")  " + scelte.get(i));
	}
	k = InputDati.leggiIntero(1,i) -1;
	return scelte.get(k);
}

}