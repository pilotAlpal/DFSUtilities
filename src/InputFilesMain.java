import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.TreeMap;
/**
 * Lee de un fichero las aristas de cada vertice separadas por lineas y los grafos separados por -1s
 * @author Pilot
 *
 */
public class InputFilesMain {

	public static void main(String[] args) {
		TreeMap<Integer,LinkedList<Integer> > map=new TreeMap<>(); 
		try {
			FileReader fReader=new FileReader(new File("entrada.txt"));
			BufferedReader bReader=new BufferedReader(fReader);
			int numGraf=0;
			int a=0;
			int v=0;
			String linea=bReader.readLine();
			while(linea!=null){
				String[] vec=linea.split(" ");
				if(Integer.parseInt(vec[0])==-1){
					numGraf++;
					LinkedList<Integer>[] ars=new LinkedList[v];
					for(int u=0;u<v;u++){
						ars[u]=map.get(u);
					}
					PrintStream ps=new PrintStream("graph"+numGraf+".txt");
					Solver s=new Solver(ars);
					s.solve(ps,v,a);
					a=0;v=0;
					ps.close();
				}
				LinkedList<Integer> aristasI=new LinkedList();
				for(int i=0;i<vec.length;i++){
					aristasI.add(Integer.parseInt(vec[i]));
					a+=vec.length;
				}
				map.put(v, aristasI);
				v++;
				linea=bReader.readLine();
			}
			bReader.close();
			fReader.close();			
		} catch (FileNotFoundException e) {
			System.err.println("No se encuentra el fichero");
		} catch (IOException e) {
			System.err.println("Error abriendo el archivo");
		}
	}
}