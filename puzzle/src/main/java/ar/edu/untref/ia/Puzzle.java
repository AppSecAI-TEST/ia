package ar.edu.untref.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Puzzle {

	static final Nodo SOLUCION = new Nodo(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)); 
	
	private List<Nodo> adyacentes = new ArrayList<Nodo>();
	
	private List<Nodo> recorrido = new ArrayList<Nodo>();
	
	public List<Nodo> resolver(Nodo nodoInicial) {
		
		Queue<Nodo> colaDeVisitas = new LinkedList<Nodo>();
    	nodoInicial.setVisitado(true);
		boolean resuelto = estaResuelto(nodoInicial);
        
    	if (!resuelto) {
    		
    		colaDeVisitas.add(nodoInicial);
    		nodoInicial.setVisitado(true);
    		agregarAdyacentes(nodoInicial);
    		recorrido.add(nodoInicial);
 
    		while (!colaDeVisitas.isEmpty() && !resuelto) {
    			
    			Nodo desacolado = colaDeVisitas.remove();
				agregarAdyacentes(desacolado);
    			
    			for (Nodo nodoActual : adyacentes) {
    				if (!nodoActual.fueVisitado()) {
    					nodoActual.setVisitado(true);
    					colaDeVisitas.add(nodoActual);
    					recorrido.add(nodoActual);
    					resuelto = estaResuelto(nodoActual);
    				}
    			}
    		}
    	}
    			        
        return recorrido;
    }
	
	private boolean estaResuelto(Nodo estadoJuego) {	
		return SOLUCION.equals(estadoJuego);
	}

	private void agregarAdyacentes(Nodo estadoJuego) {
		
		if (!estadoJuego.fueVisitado()) {
		
			Nodo nodo1 = new Nodo(Arrays.asList(1,4,5,6,0,7,2,3,8));
			Nodo nodo2 = new Nodo(Arrays.asList(0,1,5,6,4,7,2,3,8));
			Nodo nodo3 = new Nodo(Arrays.asList(1,5,0,6,4,7,2,3,8));
			
			adyacentes.add(nodo1);
			adyacentes.add(nodo2);
			adyacentes.add(nodo3);
		}
		
	}
}