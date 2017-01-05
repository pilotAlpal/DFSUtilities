import java.io.FileNotFoundException;
import java.io.PrintStream;

public class RandomGrafsMain {
	
	private final static int nGrafos=20;
	public static void main(String[] args) {
		int nV=8;int nA=4;
		for(int i=0;i<nGrafos;i++){
			Graph g=Graph.grafoAleatorio(nV, nA);
			PrintStream ps;
			try {
				ps = new PrintStream("graph"+i+".txt");
				Solver s=new Solver(g.getAristas());
				s.solve(ps,nV,nA);
				ps.close();
				if(i%2==0){
					nV=2*nV;
					nA=nV;
				}
				else nA=nV/4;
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			

		}
	}

}
