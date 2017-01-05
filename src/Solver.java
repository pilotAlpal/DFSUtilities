import java.io.PrintStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * . . . El algoritmo  Cap´ıtulo 22 del Cormen
 * @author Pilot
 *
 */
public class Solver {
	
	private LinkedList<Integer>[] aristas;
	
	public Solver(LinkedList<Integer>[] a){
		aristas=a;
	}
	/**
	 * Implementar o Java o en C++ un algoritmo, que dado un grafo dirigido, detecte si tiene o no
	 * ciclos.
	 *  En caso de ser ac´ıclico, ha de listar sus v´ertices en orden topol´ogico. Si hay m´as de uno 
		 posible, los puede listar en cualquiera de ellos
	 	 En caso de ser c´ıclico, ha de listar sus componentes
		  fuertemente conexas (cada una es un conjunto de v´ertices)
	 * @param nAs 
	 * @param nVs 
	 */
	public void solve(PrintStream ps, int nVs, int nAs) {
		long tIni=Calendar.getInstance().getTimeInMillis();
		ps.println("Grafo con "+nVs+" vertices y "+nAs+" aristas.");
		Graph grafo=new Graph(aristas);
		DFSInfo dfsG=grafo.dfs();
		LinkedList<Integer> terminados=dfsG.getFinished();
		if(!dfsG.isCyclic()){
			ps.println("Grafo aciclico");	
			ListIterator<Integer> it=terminados.listIterator();
			while(it.hasNext())
				ps.println("->"+it.next());
		}
		else{
			Graph grafoT=grafo.traspose();
			LinkedList<LinkedList<Integer>> componentes=grafoT.stronglyConnected(terminados);
			ps.println("Grafo ciclico");
			ListIterator<LinkedList<Integer>> itComps=componentes.listIterator();
			int nComps=0;
			while(itComps.hasNext()){
				nComps++;
				ps.println("Componente "+nComps+"-esima:");
				ListIterator<Integer> itNtC=itComps.next().listIterator();
				while(itNtC.hasNext())
					ps.println("->"+itNtC.next());

			}
		}
		long tFin=Calendar.getInstance().getTimeInMillis();
		ps.println("Tiempo transcurrido(en ms)"+(tFin-tIni));
	}
	
	public void solve(int nVertices, int tArs){
		solve(System.out,nVertices,tArs);
	}
}
