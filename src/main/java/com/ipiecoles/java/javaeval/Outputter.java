package com.ipiecoles.java.javaeval;

public interface Outputter {
	
	public default void outList(String s) {
		StaticOut.outList(s);
	}
	public default void outQuestion(String s) {
		StaticOut.outQuestion(s);
	}
	public default void outImportant(String s) {
		StaticOut.outImportant(s);
	}
	public default void outImportant(String s, int i) {
		StaticOut.setSpaceNum(i);
		outImportant(s);
	}
	
	public default void outInput(String s) {
		StaticOut.outInput(s);
	}
	public default void outInput(String s, int i) {
		StaticOut.setSpaceNum(i);
		outInput(s);
	}
	
	/**
	 * Prints a String.
	 * @param s
	 */
	public default void out(String s) {
		StaticOut.out(s);
	}
	/**
	 * Prints a String and then terminates the line.
	 * @param s - The String to be printed
	 */
	public default void outl(String s) {
		StaticOut.outl(s);
	}
	/**
	 * Prints a String and then terminates the line.
	 * @param s - The String to be printed
	 * @param i - Inputs a newline 0: before, 1: after, 2: both
	 */
	public default void outl(String s, int i) {
		StaticOut.setSpaceNum(i);
		outl(s);
	}
	/**
	 * Prints a newline.
	 */
	public default void outl() {
		outl("");
	}
	
	public default String spamChar(String str, int num) {
		return StaticOut.spamChar(str, num);
	}
}

class StaticOut {
	
	public static final int PRINT_DELAY = 30;
	
	public static final String DEFAULT_CHAR = "#";
	public static final String EMPTY_CHAR = "#";
	public static final String QUESTION_CHAR = "#";
	public static final String IMPORTANT_CHAR = "!";
	public static final String LIST_CHAR = "-";
	
	private static String curChar = DEFAULT_CHAR;
	private static int spaceNum = -1;
	public static void setSpaceNum(int i) {spaceNum = i;}
	
	public static void outQuestion(String s) {
		curChar = QUESTION_CHAR;
		setSpaceNum(1);
		outl(s);
	}
	public static void outImportant(String s) {
		curChar = IMPORTANT_CHAR;
		outl(s);
	}
	public static void outList(String s) {
		curChar = LIST_CHAR;
		outl(s);
	}
	public static void outInput(String s) {
		curChar = EMPTY_CHAR;
		out("> " + s);
	}
	
	private static void charOut() {
		System.out.print(DEFAULT_CHAR + "\n");
		pause();
	}
	private static void charOut(String s) {
		System.out.print(curChar + " " + s);
		pause();
	}
	public static void out(String s) {
		if(spaceNum == 0 || spaceNum == 2) charOut();
		
		charOut(s);
		
		if(spaceNum >= 1) charOut();
		
		curChar = DEFAULT_CHAR;
		spaceNum = -1;
	}
	public static void outl(String s) {
		out(s + "\n");
	}
	
	private static void pause() {
		try {
			Thread.sleep(PRINT_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String spamChar(String str, int num) {
		return new String(new char[num]).replaceAll("\0", str);
	}
	
}