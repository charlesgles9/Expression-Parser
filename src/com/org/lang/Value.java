package com.org.lang;
@SuppressWarnings("unchecked")
public abstract class Value<T> {
protected T value;

	public Value() {}
	public Value(T value) {
		setValue(value);
	}
	public T getValue() {
		return value;
	}
	
	public void  setValue(T value) {
		this.value=value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
	public Value<T>plus(Value<?> value2) {
			return new Variable<>(toDouble()+value2.toDouble());
	}
	
	public Value<T> times(Value<?> other){
			return new Variable<>(toDouble()*other.toDouble());
	}
	
	public Value<T> divide(Value<?> other){;
		   	return new Variable<>(toDouble()/other.toDouble());
	}
	
	public Value<T> minus(Value<?> other){
			return new Variable<>(toDouble()-other.toDouble());
	
	}
	public Value<T> mod(Value<?> other){	
			return new Variable<>(toDouble()%other.toDouble());
	
	}
	public Value<T> greaterThan(Value<?> other){
		
			return new Variable<>(toDouble()>other.toDouble());
	
	}
	public Value<T> lessThan(Value<?> other){
		
			return new Variable<>(toDouble()<other.toDouble());
		
	}
	
	public Value<T> leftShift(Value<?>other){
		return new Variable<>(toInt()<<other.toInt());
	}
	
	public Value<T> rightShift(Value<?> other){
		return new Variable<>(toInt()>>other.toInt());
	}
	
	public Value<T> and(Value<?> other){
		
		if(isBool()& other.isBool()) {
			return new Variable<>(toBool()&other.toBool());
		}
		return new Variable<>(toInt()&other.toInt());
	}
	
	
	public Value<T> pow(Value<?> other){
		return new Variable<>(Math.pow(toDouble(), other.toDouble()));
	}
	
	public Value<T> or(Value<?> other){
		if(isBool()& other.isBool()) {
			return new Variable<>(toBool()|other.toBool());
		}
		return new Variable<>(toInt()|other.toInt());
	}
	
   public boolean isBool() {
	   return value.toString().equals("false")||
			   value.toString().equals("true");
   }
	public Integer toInt() {
		return Integer.parseInt(value.toString());
	}
	
	public Double toDouble() {
		return Double.parseDouble(value.toString());
	}
	
	public Boolean toBool() {
		return Boolean.parseBoolean(value.toString());
	}
	
}
