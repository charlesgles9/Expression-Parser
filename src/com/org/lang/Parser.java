package com.org.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.org.lang.exp.Expression;
import com.org.lang.exp.ExpressionNested;
import com.org.lang.exp.FuncExpression;
import com.org.lang.exp.Node;

public class Parser {

	public static int RAD=0;
	public static int DEG=1;
	public static int MODE=DEG;
	interface ParseListener{
		void onSuccess(String value,Node<?> expression);
		void onError(String message);
	}
	
	
	static class  Cursor{
		private int index;
		public void increment() {
			index++;
		}
		public void decrement() {
			index++;
		}
		
		public void set(int index) {
			this.index=index;
		}
		
	}
	private static void evaluate(Stack<Node<?>> nodes, Stack<Token>operators) {
		while(!(operators.isEmpty())) {
			  Token token=operators.pop();
			  Node<?> x=nodes.pop();
			  Node<?> y= nodes.pop();
			  ExpressionNested<Expression<Node<?>>> xp= 
					  new ExpressionNested<Expression<Node<?>>>(y,x,token);
			nodes.push(xp);
		}
	}
	private static void evaluate(Stack<Node<?>> nodes, Stack<Token>operators, Token token) {
		while(!operators.isEmpty()&&Token.hasPrecedence(operators.peek(), token)) {
			  Token ntoken=operators.pop();
			  Node<?>  x=nodes.pop();
			  Node<?>  y= nodes.pop();
			  ExpressionNested<Expression< Node<?> >> xp= 
					  new ExpressionNested<Expression< Node<?> >>(y,x,ntoken);
			nodes.push(xp);
		}
	}
	private static void evaluateBracket(Stack<Node<?>> nodes, Stack<Token> operators) {
		while (!operators.isEmpty() && !operators.peek().isOpeningBracket()) {
			Token token = operators.pop();
			Node<?> x = nodes.pop();
			Node<?> y = nodes.pop();
			ExpressionNested<Expression<Node<?>>> xp = new ExpressionNested<Expression<Node<?>>>(y, x, token);
			nodes.push(xp);
		}
	}
	
	public static void evaluate(List<Token> tokens,ParseListener listener) {
		evaluate(new Cursor(),tokens,listener);
	}
	
	public static void evaluate(Cursor cursor,List<Token> tokens, ParseListener listener) {
		Stack<Node<?>> nodes= new Stack<>();
		Stack<Token> operators= new Stack<>();
	
		for(;cursor.index<tokens.size();cursor.increment()) {
			Token token=tokens.get(cursor.index);
			
			if(Token.isDigit(token)) {
				nodes.push(new Expression<Variable<String>>
				(new Variable<String>(token.getPhrase())));
			}else if(Token.isOperator(token)) {
				 if(!operators.isEmpty()) {
					 evaluate(nodes,operators,token);
				 }
				operators.push(token);
			}else if(token.isBracket()) {
				if(token.isOpeningBracket()) {
					operators.push(token);
				}else if(token.isClosingBracket()) {
					
					evaluateBracket(nodes,operators);
					operators.pop();
				}
			}else if(Token.isBool(token)) {
				nodes.push(new Expression<Variable<String>>
				(new Variable<String>(token.getPhrase())));				
			}else if(token.isLetter()) {
				 Stack<Token> br= new Stack<>();
				 int start=cursor.index+1;
				 int end=start;
	              if(tokens.get(start).isBracket()) {
	            	  br.push(tokens.get(start));
	            	  end++;
	            	  while(!br.isEmpty()) {
	            		  Token t=tokens.get(end);
	            		  if(t.isOpeningBracket())
	            			  br.push(t);
	            		  else if(t.isClosingBracket())
	            			  br.pop();
	            		  end++;
	            	  }
	            	  
	            	 cursor.set(end);
	            	 
	            	 evaluate(new Cursor(),tokens.subList(start+1, end-1),new ParseListener() {
						@Override
						public void onSuccess(String value, Node<?> expression) {
							nodes.push(new FuncExpression(expression,token));
						}
						@Override
						public void onError(String message) {
							listener.onError(message+" on Line "+start);
						}
	            		 
	            	 });
	              }
				 
				
			}
		
		}
		
				if(!operators.isEmpty())
		evaluate(nodes,operators);
		
		
		try {
		   Node<?> expression=nodes.pop();
	   	  listener.onSuccess(expression.execute().toString(),expression);
		} catch (Exception e1) {
			
			e1.printStackTrace();
			listener.onError(e1.getMessage());
		}
		
		
	}
}
