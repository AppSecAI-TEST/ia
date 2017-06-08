package ar.edu.untref.ia;

public class BlackJack {

	public boolean randomPolicy() {
	    
		boolean result = false; 
		
		if (Math.random() >= 0.5)  {
			result = true;
		}	
		return result;
	}
	
}
