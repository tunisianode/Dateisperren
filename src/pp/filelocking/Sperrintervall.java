package pp.filelocking;

//import java.util.*;
import java.util.LinkedList;

public class Sperrintervall 
{
	//Attribute 
	private int begin, end;
	
	
	//Konstruktor
	public Sperrintervall (int begin, int end, boolean locked)
	{
		this.setBegin(begin);
		this.setEnd(end);
	}

	
	//Getter und Setter
	public int getBegin() {
		return begin;
	}


	public void setBegin(int begin) {
		this.begin = begin;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}
}
