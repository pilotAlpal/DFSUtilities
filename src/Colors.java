
public enum Colors {	
	White,Grey,Black,Red;
	
	public boolean isWhite(){
		if (this==White)
			return true;
		return false;
	}
	public boolean isRed(){
		return (this==Red);
	}
	public boolean isGrey(){
		if(this==Grey)
			return true;
		return false;
	}
	
	public boolean isBlack(){
		if (this==Black)
			return true;
		return false;
	}
}