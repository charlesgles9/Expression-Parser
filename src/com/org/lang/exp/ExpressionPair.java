package com.org.lang.exp;

import com.org.lang.InvalidSyntaxException;
import com.org.lang.Token;
import com.org.lang.Value;
import com.org.lang.Variable;

public class ExpressionPair extends Node{

	private Value<?> value= new Variable<Object>();
	private Pair<Value,Value> pair= new Pair<>();
	private Token token;
	public ExpressionPair(Value<?> x, Value<?> y,Token token) {
		pair.x=x;
		pair.y=y;
		this.token=token;
	}
	
	@Override
	public Value<?> execute() throws InvalidSyntaxException {
		switch(token.getPhrase()) {
		case "+":
			return pair.x.plus(pair.y);
		case "-":
			return pair.x.minus(pair.y);
		case "*":
			return pair.x.times(pair.y);
		case "/":
			return pair.x.divide(pair.y);
		case "%":
			return pair.x.mod(pair.y);
		case ">":
			return pair.x.greaterThan(pair.y);
		case "<":
			return pair.x.lessThan(pair.y);
			
	   default:
		   throw new InvalidSyntaxException();
		} 
	  
	}
	
	public Value<?> getValue() {
		return value;
	}
}
