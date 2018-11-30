package dataframe;

public class StringV extends Value{
	String val;
	@Override
	public String toString() {
		return val;
	}

	  public StringV(String val){
	        this.val=val;
	    }

	    public StringV(){};
	    
	public String getVal() {
		return this.val;
	}
	
	
	@Override
	public Value add(Value v) {
	      return new StringV(this.val+v.toString());
	}

	@Override
	public Value sub(Value v) {
		return new StringV(this.val.replace(v.toString(),""));
	}

	@Override
	public Value mul(Value v) {
		if(v instanceof IntegerV){
            StringV tmp = new StringV();
            for (int i = 0; i < ((IntegerV)v).getVal();i++) {
            	//tmp.add(val);
            }return null;
            
        }else {
        	return null;
        }
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
		return this.val.equals(v.toString());
	}

	@Override
	public boolean lte(Value v) {
		return this.val.compareTo(v.toString()) < 0;
	}

	@Override
	public boolean gte(Value v) {
		return this.val.compareTo(v.toString()) > 0;
	}

	@Override
	public boolean neq(Value v) {
		return !this.val.equals(v.toString());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof StringV) {
			StringV x = (StringV)other;
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
		 return new StringV(s);
	}

}
