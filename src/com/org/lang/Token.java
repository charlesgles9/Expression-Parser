package com.org.lang;

import java.util.ArrayList;

public class Token {

	 private int start;
	 private int line;
	 private String phrase;
	 private static ArrayList<String> operators= new ArrayList<>() {
		{   add("<");
			add("%");
			add(">");
			add(">>");
			add("<<");
			add("&");
			add("|");
			add("+");
			add("-");
			add("/");
			add("*");
			add("^");
		};
	 };
	 private Token(int start, String phrase,int line) {
		this.start=start;
		this.phrase=phrase;
		this.line=line;
	}
	public int getStart() {
		return start;
	}
	public String getPhrase() {
		return phrase;
	}
	public int getLine() {
		return line;
	}
    public int len() {
	   return phrase.length();
    }
    public int distance() {
	   return start+len();
    }
    public static Token create(int start, String phrase,int line) {
    	return new Token(start,phrase,line);
    }
    
   public static boolean hasPrecedence(Token a, Token b) {
	  if(isBracket(a)|| isBracket(b))
		  return false;
	  return (getPrecedenceLevel(a)>getPrecedenceLevel(b));
   }
   
   public static int getPrecedenceLevel(Token token) {
	   switch(token.getPhrase()) {
	     case "-":
	     case "+":
		   return 0;
	     case "*":
	     case "/":
	     case "%":
	       return 1;
	     case "<<":
	     case ">>":
	     case "&":
	     case "|":
	     case "^":
	       return 2;
	   default:
		   return -1;
	   }
   }
   
    public static boolean isDigit(Token token) {
    	for(int i=0;i<token.getPhrase().length();i++) {
    		char ch=token.getPhrase().charAt(i);
    		if(!(Character.isDigit(ch)||ch=='.')) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean isLetter() {
    	return Character.isLetter(phrase.charAt(0));
    }
    public static boolean isBool(Token token) {
    	return token.getPhrase().equals("false")||
    			token.getPhrase().equals("true");
    }
    public static boolean isOperator(Token token) {
    	return operators.contains(token.getPhrase());
    }
    
   
    public static boolean isBracket(Token token) {
    	return isClosingBracket(token)||isOpeningBracket(token);
    }
    
    public static boolean isClosingBracket(Token token) {
    	return token.getPhrase().equals(")");
    }
    
    public static boolean isOpeningBracket(Token token) {
    	return token.getPhrase().equals("(");
    }
    
    public  boolean isBracket() {
    	return isClosingBracket(this)||isOpeningBracket(this);
    }
    
    public  boolean isClosingBracket() {
    	return phrase.equals(")");
    }
    
    public boolean isOpeningBracket() {
    	return phrase.equals("(");
    }
    
    
}
