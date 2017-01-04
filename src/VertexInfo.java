
public class VertexInfo {
	private Colors color;
	private int discoveryT;
	private int finishingT;
	
	public VertexInfo(){
		color=Colors.White;
		discoveryT=-1;
		finishingT=-1;
	}
	
	public void discovered(int time){
		color=Colors.Grey;
		discoveryT=time;
	}
	
	public boolean isWhite(){
		return color.isWhite();
	}
	
	public boolean isBlack(){
		return color.isBlack();
	}
	
	public boolean isGray(){
		return color.isGrey();
	}

	public void finished(int time) {
		color=Colors.Black;
		finishingT=time;
	}

}
