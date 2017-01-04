import java.util.LinkedList;
import java.util.ListIterator;

public class DFSInfo {
	private boolean cyclic;
	private VertexInfo[] vI;
	private LinkedList<Integer> finished;
	
	public DFSInfo(int nVertex){
		cyclic=false;
		vI=new VertexInfo[nVertex];
		finished=new LinkedList<>();
		for(int i=0;i<nVertex;i++){
			vI[i]=new VertexInfo();
		}
	}

	public boolean isWhite(int i) {
		return vI[i].isWhite();
	}
	
	public boolean isBlack(int i){
		return vI[i].isBlack();
	}
	
	public boolean isGray(int i){
		return vI[i].isGray();
	}
	
	public boolean isCyclic(){
		return cyclic;
	}
	
	public void setCyclic(){
		cyclic=true;
	}
	
	public void discover(int v, int t){
		vI[v].discovered(t);
	}

	public void finish(int v, int time) {
		vI[v].finished(time);
		finished.addFirst(v);
	}

	public LinkedList<Integer> getFinished(){
		return finished;
	}
	
	
	

}
