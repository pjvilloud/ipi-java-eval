package com.ipiecoles.java.javaeval;

public enum State {
	DEFAULT,
	SELECT("one"),
	LIST("all"),
	CREATE,
	EDIT,
	DELETE;
	
	private String suffix;
	
	State() 		{suffix = ""		;}
	State(String s)	{suffix = " " + s	;}
	
	@Override
	public String toString() {
		return super.toString().toLowerCase() + suffix;
	}
}