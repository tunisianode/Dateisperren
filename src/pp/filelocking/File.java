//UP1
package pp.filelocking;

import java.util.LinkedList;

//Klasse File abgeleitet aus Package java.lang Klasse Thread
//wir nutzen run() und start()
public class File extends Thread
{
	//Attribute
	//Gesamtlänge der Datei
	private int length;
	//static int mylength;
	int begin, end;
	private LinkedList<Sperrintervall> llname; 
	//private ListIterator<Sperrintervall> itr = llname.listIterator();
	
	
	//Konstruktor
	public File (int length)
	{
		//UP2
		//try 
		//{
			if(length >= 0)
			{	
				this.length = length;
				this.llname = new LinkedList<Sperrintervall>();
				
			}
			else
			{
				throw new IllegalArgumentException();
			}
		//}

		/*catch (IllegalArgumentException e)
		{
			System.out.println ("Argument des Konstuktor ungültig, da negativer Wert");
		}*/
	}
	
	//Methode lock mit synchronized versehen, zum sperren eines Intervalls von Bytes
	public synchronized void lock (int begin, int end) throws InterruptedException
	{
			/*if(begin >= 0 && end >= 0 && end > begin && end <= this.length)
			{
				System.out.println ("UP3 Bedingungen OK");
			
				//solange noch Kollision
				while (intervallCollisionWith(begin, end))
				{
					this.wait();
				}
					llname.add(new Sperrintervall(begin, end));
			}
			else
			{
				throw new IllegalArgumentException();
			}
	}*/
		
		//UP3 Bedingungen auf gültige Intervall
		System.out.println ("Methode lock begin: " + begin);
		System.out.println ("Methode lock end : " + end);
		System.out.println ("Methode lock länge : " + this.length);
		if (begin < 0 || end < 0 || begin > end || end > this.length-1)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			//solange noch Kollision
			while (intervallCollisionWith(begin, end))
			{
				System.out.println ("wait");
				this.wait();
			}
			System.out.println ("Methode lock sperrt Intervall : " + begin + " " + end);
			llname.add(new Sperrintervall(begin, end, true));
		}
	}

	
	//Methode unlock mit synchronized versehen zum freigeben eines Intervalls von Bytes
	public synchronized void unlock (int begin, int end)
	{		
		/*if (begin >= 0 && end >= 0 && end >= begin && end <= this.length-1)
		{
		for (int i=0; i < this.llname.size(); i++)
			{
				//kram is plausibel und kram is in der liste
				if (begin == llname.get(i).begin && end == llname.get(i).end)   
				{
					System.out.println (this.llname.get(i).begin);
					System.out.println (this.llname.get(i).end);
					llname.remove(i);
					this.notifyAll();
				}
				else 
				{
					throw new IllegalArgumentException();
				}
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
		}*/
		System.out.println ("Methode unlock begin: " + begin);
		System.out.println ("Methode unlock end : " + end);
		System.out.println ("Methode unlock länge : " + this.length);
		if (begin < 0 || end < 0 || begin > end || end > this.length-1)
		{
			throw new IllegalArgumentException();
		}
		else 
		{	
			if (this.llname.isEmpty())
			{
				throw new IllegalArgumentException();
			}
			else
			{
				System.out.println ("Methode unlock for-Schleife: " + this.llname.size());
				for (int i=0; i < this.llname.size(); i++)
				{
					if (begin == llname.get(i).getBegin() && end == llname.get(i).getEnd())
					//Prüfung ob Intervall vorher überhaupt gesperrt wurde
					//System.out.println ("Methode unlock boolean: " + llname.get(i).isLocked());
					//if (llname.get(i).isLocked() == true)
					{			
						System.out.println ("Methode unlock: removed: " + llname.get(i).getBegin() + " und " + llname.get(i).getEnd());
						llname.remove(i);
						//zurücksetzen
						//llname.get(i).setLocked(false);
						//System.out.println ("Methode unlock: setLocket to false");
					//notify all hier?
					}
					else 
					{ 
						throw new IllegalArgumentException();
					}
					System.out.println ("Methode unlock: notifyAll");
					this.notifyAll();
				}
			}
		}
	}
	
	/*Methode zum prüfen Collisionen zw Intervallen - Zwei Intervalle kollidieren dann NICHT miteinander,
	wenn entweder (der eigene Anfang UND das eigene Ende vor dem Anfang des Anderen Intervals) oder 
	(der eigene Anfang und das eigene Ende hinter dem Ende des Anderen Intervals) liegen*/
	public boolean intervallCollisionWith(int begin, int end)
	{
		System.out.println ("Methode intervallCollisionWith: Listengröße"  + this.llname.size());
		if (this.llname.size() >= 0)	
		{
			System.out.println ("Methode intervallCollisionWith: Listengröße ist größer gleich 0 nämlich: " + this.llname.size());
			for (int i=0; i < this.llname.size(); i++)
			{
				System.out.println ("Methode IntervallCollisionWith prüfe : " + this.llname.get(i).getBegin());
				System.out.println ("Methode IntervallCollisionWith prüfe : " + this.llname.get(i).getEnd());
				System.out.println ("Methode intervallCollisionWith: Wert von i for-schleife ist: " + i);
				//eigentliche Collision
				if (!((begin < llname.get(i).getBegin() && end < llname.get(i).getBegin()) || (begin > llname.get(i).getEnd() && end > llname.get(i).getEnd())))
				{
				System.out.println ("Methode intervallCollisionWith: return true for Collision");
				return true;
				}
			}
		}
		System.out.println ("Methode intervallCollisionWith: return false for Collision");
		return false;
	}
	


	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		File f1 = new File(111);
		//File f2 = new File(2048);
		f1.start();
		//f1.start();
		//f1.start();
		//f1.lock(50, 60);
		//f1.lock(70, 75);
		//f1.lock(0, 15);
		f1.unlock(50, 60);
	}

}
