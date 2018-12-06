package dataframe;

import java.time.LocalDateTime;

public class DateTimeV extends Value{
	LocalDateTime val;
	@Override
	public String toString() {
		String x =this.val.toString();
		return x;
	}

	  public DateTimeV(LocalDateTime val){
	        this.val=val;
	    }

	    public DateTimeV(){};
	    
	    
	public LocalDateTime getVal() {
		return this.val;
	}
	
	
	@Override
	public Value add(Value v) {
	      return null;
	}

	@Override
	public Value sub(Value v) {
		return null;
	}

	@Override
	public Value mul(Value v) {
        return null;   
	}

	@Override
	public Value div(Value v) {
        	return null;
	}

	@Override
	public Value pow(Value v) {
        	return null;
	}

	@Override
	public boolean eq(Value v) {
		if (v instanceof DateTimeV) {
			if (this.val.equals(((DateTimeV)v).val)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean lte(Value v) {
		if (v instanceof DateTimeV) {
			if (!(this.val.isBefore(((DateTimeV)v).val)) ){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean gte(Value v) {
		if (v instanceof DateTimeV) {
			if (!(this.val.isAfter(((DateTimeV)v).val)) ){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean neq(Value v) {
		if (v instanceof DateTimeV) {
			if (!(this.val.equals(((DateTimeV)v).val)) ){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof DateTimeV) {
			DateTimeV x = (DateTimeV)other;
			return this.val==x.val;
		}
		return false;
	}

	@Override
	public int hashCode() {
		 return getVal().hashCode();
	}

	@Override
	public Value create(String s) {
		return new DateTimeV(LocalDateTime.parse(s));
	}

}
