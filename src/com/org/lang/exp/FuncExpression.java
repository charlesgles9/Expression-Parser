package com.org.lang.exp;

import com.org.lang.Parser;
import com.org.lang.Token;
import com.org.lang.Value;
import com.org.lang.Variable;

public class FuncExpression extends Node{
	 private Token token;
	 private Node<?> input;
	 public FuncExpression(Node<?> input, Token token) {
		  this.input=input;
		  this.token=token;
		 
	  }
	 
	 
	 @Override
		public Value<?> execute() throws Exception {
		 
		 switch(token.getPhrase()) {
		 
		 case "sqrt":
			return new Variable<Double>( Math.sqrt(input.execute().toDouble()));
		 case "sin":
				return new Variable<Double>(Parser.MODE==Parser.DEG?
						Math.sin(Math.toRadians(input.execute().toDouble())):
						Math.sin(input.execute().toDouble()));
		 case "cos":
				return new Variable<Double>( Parser.MODE==Parser.DEG?
						Math.cos(Math.toRadians(input.execute().toDouble())):
						Math.cos(input.execute().toDouble()));
		 case "tan":
				return new Variable<Double>( Parser.MODE==Parser.DEG?
						Math.tan(Math.toRadians(input.execute().toDouble())):
						Math.tan(input.execute().toDouble()));
		 case "exp":
				return new Variable<Double>(Math.exp(input.execute().toDouble()));
		 case "toDegrees":
				return new Variable<Double>(Math.toDegrees(input.execute().toDouble()));
		 case "toRadians":
				return new Variable<Double>(Math.toRadians(input.execute().toDouble()));
		 case "log":
				return new Variable<Double>(Math.log(input.execute().toDouble()));
		 case "log10":
				return new Variable<Double>(Math.log10(input.execute().toDouble()));
		 default:
			 return super.execute();
		 }
		 
	 
	 }
}
