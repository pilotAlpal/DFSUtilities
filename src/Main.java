import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		System.out.println("¿Cuantos vertices tiene el grafo?");
		int nVertices=teclado.nextInt();
		LinkedList<Integer>[] aristas=new LinkedList[nVertices];
		for (int v=0;v<nVertices;v++){
			aristas[v]=new LinkedList<>();
			System.out.println("Cuantas aristas tiene el vertice "+v+"?");
			int nAristas=teclado.nextInt();
			for(int a=0;a<nAristas;a++){
				System.out.println("A que vertice va dirigida la arista "+a+" del vertice "+v+"?");
				int u=teclado.nextInt();
				if(!aristas[v].contains(u))
					aristas[v].add(u);
			}
		}
		teclado.close();
		Solver s=new Solver(aristas);
		s.solve();
	}

}
