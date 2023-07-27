package com.org.lang.exp;

import com.org.lang.InvalidSyntaxException;
import com.org.lang.Token;
import com.org.lang.Value;

public class ExpressionNested<T> extends Node{

	 private Pair<Node<?>,Node<?>> pair= new Pair<>();
     private Token token;
	  public ExpressionNested(Node<?> y, Node<?> x, Token token) {
		  this.pair.x=y;
		  this.pair.y=x;
		  this.token=token;
	  }
	@Override
	public Value<?> execute() throws InvalidSyntaxException {
		
		try {
	switch(token.getPhrase()) {
	   case "+":
			    return pair.x.execute().plus(pair.y.execute());
		case "-":
				return pair.x.execute().minus(pair.y.execute());
		case "*":
				return pair.x.execute().times(pair.y.execute());
		case "/":
			    return pair.x.execute().divide(pair.y.execute());
		case "%":
			    return pair.x.execute().mod(pair.y.execute());
		case "^":
			    return pair.x.execute().pow(pair.y.execute());
		case "<<":
		    return pair.x.execute().leftShift(pair.y.execute());
		case ">>":
		    return pair.x.execute().rightShift(pair.y.execute());
		case ">":
			    return pair.x.execute().greaterThan(pair.y.execute());
		case "<":
			    return pair.x.execute().lessThan(pair.y.execute());
		case "&":
			    return pair.x.execute().and(pair.y.execute());
		case "|":
		    return pair.x.execute().or(pair.y.execute());
	   default:
		   throw new InvalidSyntaxException();
		} 
		} catch (Exception e) {
			//syntax error
			 throw new InvalidSyntaxException();
		}
		
	}
	 
	 
}
