package com.ipiecoles.java.javaeval;

public interface Outputter {
	
	public default void outList(Object s) {
		StaticOut.outList(s);
	}
	public default void outQuestion(Object s) {
		StaticOut.outQuestion(s);
	}
	public default void outImportant(Object s) {
		StaticOut.outImportant(s);
	}
	public default void outImportant(Object s, int i) {
		StaticOut.setSpaceNum(i);
		outImportant(s);
	}
	
	public default void outInput(Object s) {
		StaticOut.outInput(s);
	}
	public default void outInput(Object s, int i) {
		StaticOut.setSpaceNum(i);
		outInput(s);
	}
	
	public default void out(Object s) {
		StaticOut.out(s);
	}
	public default void outl(Object s) {
		StaticOut.outl(s);
	}
	public default void outl(Object s, int i) {
		StaticOut.setSpaceNum(i);
		outl(s);
	}
	public default void outl() {
		outl("");
	}
	
	public default String spamChar(String str, int num) {
		return StaticOut.spamChar(str, num);
	}
}

class StaticOut {
	
	public static final String DEFAULT_CHAR = "#";
	public static final String EMPTY_CHAR = "#";
	public static final String QUESTION_CHAR = "#";
	public static final String IMPORTANT_CHAR = "!";
	public static final String LIST_CHAR = "-";
	
	private static String curChar = DEFAULT_CHAR;
	private static int spaceNum = -1;
	public static void setSpaceNum(int i) {spaceNum = i;}
	
	public static void outQuestion(Object s) {
		curChar = QUESTION_CHAR;
		setSpaceNum(1);
		outl(s);
	}
	public static void outImportant(Object s) {
		curChar = IMPORTANT_CHAR;
		outl(s);
	}
	public static void outList(Object s) {
		curChar = LIST_CHAR;
		outl(s);
	}
	public static void outInput(Object s) {
		curChar = EMPTY_CHAR;
		out("> " + s);
	}
	
	private static void charOut() {
		System.out.print(DEFAULT_CHAR + "\n");
	}
	private static void charOut(Object s) {
		System.out.print(curChar + " " + s);
	}
	public static void out(Object s) {
		if(spaceNum == 0 || spaceNum == 2) charOut();
		
		charOut(s);
		
		if(spaceNum >= 1) charOut();
		
		curChar = DEFAULT_CHAR;
		spaceNum = -1;
	}
	public static void outl(Object s) {
		out(s + "\n");
	}
	
	public static String spamChar(String str, int num) {
		return new String(new char[num]).replaceAll("\0", str);
	}
	
}