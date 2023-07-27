package com.org.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Lexer {

	
	private static Map<Integer,ArrayList<Token>> tokens= new HashMap<Integer,ArrayList<Token>>();
	private static ArrayList<String> reservedWords= new ArrayList<>() {
		{
		
			add(";");
			add(",");
			add(":");
			add("(");
			add(")");
			add("=");
			/* right & left shift should be at the top
			 of the list since they have the same chars as less than
			 and greater than to prevent logical errors
			  when doing conditional statements and bit manipulation*/
			add(">>");
			add("<<");
			add("<");
			add(">");
			add("%");
			add("&");
			add("|");
			add("+");
			add("^");
			add("-");
			add("/");
			add("*");
			// functions
			add("sqrt");
			add("sin");
			add("cos");
			add("tan");
			add("exp");
			add("toDegrees");
			add("toRadians");
			add("log");
			add("log10");
			
			
			
					
		};
	};
	
	
	public static void evalute(String buffer,final int line) throws InvalidSyntaxException {
	    final ArrayList<Token> data= new ArrayList<>();
	    tokens.put(line, data);
		for( int i=0;i<buffer.length();i++) {
		
			for(String phrase:reservedWords){
				//search for valid reserved words
				 if(buffer.length()>i&&contains(buffer,i,phrase)) {
					 i+=phrase.length()-1;
					 data.add(Token.create(i,phrase,line));
					 break;
				 }
				 // then it may be an identifier 
				 else if(buffer.length()>i&&isIdentifier(buffer.charAt(i))) {
					 int end=i;
					 int start=i;
					 while(buffer.length()>end&&isIdentifier(buffer.charAt(end))) {
						 end++;
					 }
					 String sub=buffer.substring(start,end);
					 i+=sub.length()-1;
					 data.add(Token.create(start, sub, line));
					 break;
				 }
				 // then it may be a decimal digit 
				 else if(buffer.length()>i&&
						 Character.isDigit(buffer.charAt(i))){
					 int start=i;
					 StringBuffer dec=new StringBuffer();
					 while(buffer.length()>start&&(
							 Character.isDigit(buffer.charAt(start))||buffer.charAt(start)=='.')) {
						 dec.append(buffer.charAt(start));
						 start++;
					 }
					 i+=dec.length()-1;
					 data.add(Token.create(start, dec.toString(), line));
					 break;
				 }else {
					//throw new InvalidSyntaxException();
				 }
			};

		}
		
	     
	}
	
	public static ArrayList<Token> tokensAsList(){
		final ArrayList<Token> data= new ArrayList<>();
		 tokens.forEach((key, value)-> data.addAll(value));
		return data;
	}
	
	public static ArrayList<Token> get(int line){
		return tokens.getOrDefault(line, null);
	}
	

	public static boolean isIdentifier(char c) {
	return	(Character.isLetter(c)||Character.isJavaIdentifierStart(c));
	}
	
	public static boolean contains(String buffer, int start, String phrase) {
		if(buffer.length()<phrase.length())
			return false;
		return buffer.regionMatches(start, phrase, 0, phrase.length());
	}

	
	static class VIterable<T> implements Iterator<T>, Iterable<T> {
	   
	    private int currentIndex ;

	    public VIterable() {
	        currentIndex=0;
	    }

	   
		@Override
		public boolean hasNext() {
			return currentIndex<tokens.size();
		}

		@Override
		public T next() {
			
			return (T) get(currentIndex++);
		}

		@Override
		public Iterator<T> iterator() {
			return new VIterable<T>();
		}
	}
}
