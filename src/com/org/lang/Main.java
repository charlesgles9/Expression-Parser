package com.org.lang;

import java.util.ArrayList;

import com.org.lang.exp.Node;

public class Main {

	public static void main(String[] args) {
		
		String input[]= new String[]{
				"5^2",
				"8<<2",
				"5*log(8<<2*sqrt(255+log(3.0+sin(9.0383991))))",
				"56+78/8",
			    "56*(5*7*954548*(5*5)*12/4)"
		};
	
		try {
			for(int i=0;i<input.length;i++) {
				String str=input[i];
				Lexer.evalute(str,i);
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		
      for(ArrayList<Token> tokens:new Lexer.VIterable<ArrayList<Token>>()) {
    	  Parser.evaluate(tokens,new Parser.ParseListener() {
    			
    			@Override
    			public void onSuccess(String value, Node<?> expression) {
    			   System.out.println(value);
    				
    			}
    			
    			@Override
    			public void onError(String message) {
    				System.out.println(message);
    				
    			}
    		});
      }
      
      
     
      
     
	}

}
