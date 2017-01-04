import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Estos algoritmos estan basados en los ejemplos y esquemas del libro de Thomas H.Cormen Capitulo 22
 * @author Pilot 
 * Clase que representa un grafo como un vector de aristas
 *
 */
public class Graph {
	private LinkedList<Integer>[] aristas;
	
	/**
	 * Constructor de la clase
	 * @param l aristas del grafo
	 */
	public Graph(LinkedList<Integer>[] l){
		aristas=l;
	}
	
	/**
	 * 
	 * @return Numero de vertices
	 */
	public int numVertex(){
		if(aristas==null)
			return 0;
		return aristas.length;
	}
	
	/**
	 * Antes de llamar a este metodo comprobar que n<=numVertex()
	 * @param n vertice
	 * @return lista de artistas asociadas a un vertice si existe el vertice, estara vacia si existe pero no las tiene ,y
	 * null si no existe 
	 */
	public LinkedList<Integer> ithEdges(int n){
		if((aristas==null)||(n>aristas.length))
			return null;
		//else
		return aristas[n];
	}
	
	/**
	 * 
	 * @return traspuesto del grafo sin modificarlo.
	 */
	public Graph traspose(){
		int n=numVertex();
		LinkedList<Integer>[] adjuntosT=new LinkedList[n];
		for  (int k=0;k<n;k++)
			adjuntosT[k]=new LinkedList<>();
		for(int i=0;i<n;i++){
			LinkedList<Integer> aristasVi=aristas[i];
			while(!aristasVi.isEmpty()){
				int v=aristasVi.removeFirst();
				if(!adjuntosT[v].contains(i))
					adjuntosT[v].add(i);
			}
		}
		return new Graph(adjuntosT);
	}
	
	/**
	 * 
	 * @return objeto con la informacion necesaria para determinar si el grafo es ciclico,
	 * y proporcionar la informacion necesaria al metodo que mostrara el orden topologico en
	 * caso de que no lo sea, y al que mostrata las componentes fuertemente conexas en caso 
	 * de que lo sea
	 */
	public DFSInfo dfs(){
		int nV=numVertex();
		DFSInfo dfsG=new DFSInfo(nV);
		int time=0;
		for(int i=0;i<nV;i++){
			if (dfsG.isWhite(i))
				time=dfsVisit(dfsG,i,time);
			
		}
		return dfsG;
	}

	/**
	 * Metodo encargado de expadir un nodo
	 * @param dfsG informacion del grafo
	 * @param v nodo a expandir
	 * @param time tiempo de expansion(discovery)
	 * @return
	 */
	private int dfsVisit(DFSInfo dfsG, int v, int time) {
		time++;
		dfsG.discover(v, time);
		ListIterator<Integer> uIt=aristas[v].listIterator();
		while(uIt.hasNext()){
			int u=uIt.next();
			if (dfsG.isWhite(u))
				time=dfsVisit(dfsG, u, time);
			else if(dfsG.isGray(u)&&!dfsG.isCyclic())
				dfsG.setCyclic();		
		}
		time++;
		dfsG.finish(v,time);
		return time;
	}
	
	/**
	 * Metodo que devuelve las componentes conexas de un grafo ya transpuesto
	 * y conociendo el orden de finalizacion de los vertices en dfs(g sin trasponer)
	 * @param fs vertices de g sin transponer en orden de finalizacion tras dfs(g)
	 * @return componentes conexas agrupadas
	 */
	public LinkedList<LinkedList<Integer>> stronglyConnected(LinkedList<Integer> fs){
		int nV=numVertex();
		DFSInfo dfsI = new DFSInfo(nV);
		int t=0;
		LinkedList<LinkedList<Integer>> componentes=new LinkedList<>();
		for(int v=0;v<nV;v++){
			if (dfsI.isWhite(v)){
				LinkedList<Integer> nuevaComp=new LinkedList<>();
				t=dfsVisit4strConn(dfsI,v,t,nuevaComp);
				componentes.add(nuevaComp);
			}
			
		}
		return componentes;
	}
	
	/**
	 * Metodo auxiliar que expande cada componente conexa y la guarda en un contenedor que recibe
	 * como parametro
	 * @param dfsI informacionn acerca del grafo
	 * @param v vertice
	 * @param t de discover
	 * @param nuevaComp contenedor que almacena las componenetes fuertemente conexas con v
	 * @return tiempo transcurrido
	 */
	private int dfsVisit4strConn(DFSInfo dfsI, int v, int t, LinkedList<Integer> nuevaComp) {
		nuevaComp.add(v);
		t++;
		dfsI.discover(v, t);
		ListIterator<Integer> uIt=aristas[v].listIterator();
		while(uIt.hasNext()){
			int u=uIt.next();
			if (dfsI.isWhite(u))
				t=dfsVisit4strConn(dfsI, u, t,nuevaComp);
		}
		t++;
		dfsI.finish(v,t);
		return t;
	}
	
}
