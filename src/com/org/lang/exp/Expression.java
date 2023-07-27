package com.org.lang.exp;

import com.org.lang.Value;
import com.org.lang.Variable;

public class Expression<T> extends Node<T> {
	private Value<T> value= new Variable<T>();
	
	public Expression(T input) {
		this.value.setValue(input);
	}
	@Override
	public Value<T> execute() throws Exception {
		return value;
	}

	
}
