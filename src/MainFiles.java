import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class MainFiles {

	public static void main(String[] args) {
		TreeMap<Integer,LinkedList<Integer> > map=new TreeMap<>(); 
		try {
			FileReader fReader=new FileReader(new File("entrada.txt"));
			BufferedReader bReader=new BufferedReader(fReader);
			String linea="";
			int v=0;
			while(linea!=null){
				linea=bReader.readLine();
				String[] vec=linea.split(" ");
				LinkedList<Integer> aristasI=new LinkedList();
				for(int i=0;i<vec.length;i++){
					aristasI.add(Integer.parseInt(vec[i]));
				}
				map.put(v, aristasI);
				v++;
			}
			LinkedList<Integer>[] ars=new LinkedList[v];
			for(int u=0;u<v;u++){
				ars[u]=map.get(u);
			}
			bReader.close();
			fReader.close();
			Solver s=new Solver(ars);
			s.solve();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
