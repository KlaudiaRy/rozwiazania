
public class IntegerV extends Value {
 
	private int val;
	
	  public IntegerV(int val){
	        this.val=val;
	    }

	    public IntegerV(){};
	
	
	@Override
	public String toString() {
		return Integer.toString(val);
	}
	
	public Integer getVal() {
		return this.val;
	}

	@Override
	public Value add(Value v) {
		 if(v instanceof IntegerV){
	            return new IntegerV(((IntegerV)v).getVal().intValue()+this.val);
	    }else if (v instanceof DoubleV){
	    	return new DoubleV(((DoubleV)v).getVal().doubleValue()+(double)this.val);
	    }else if (v instanceof FloatV) {
	    	return new FloatV(((FloatV)v).getVal().floatValue()+(float)this.val);
	    }else if (v instanceof StringV) {
	    	return new StringV(((StringV)v).getVal().toString()+String.valueOf(this.val));
	    }else {
	    	return null;
		}
	}

	@Override
	public Value sub(Value v) {
		if(v instanceof IntegerV){
            return new IntegerV(((IntegerV)v).getVal().intValue()-this.val);
		}else if (v instanceof DoubleV){
	    	return new DoubleV(((DoubleV)v).getVal().doubleValue()-(double)this.val);
	    }else if (v instanceof FloatV) {
	    	return new FloatV(((FloatV)v).getVal().floatValue()-(float)this.val);
        }else {
        	return null;
        }
	}

	@Override
	public Value mul(Value v) {
		if(v instanceof IntegerV){
            return new IntegerV(((IntegerV)v).getVal().intValue()*(this.val));
		}else if (v instanceof DoubleV){
	    	return new DoubleV(((DoubleV)v).getVal().doubleValue()*(double)this.val);
	    }else if (v instanceof FloatV) {
	    	return new FloatV(((FloatV)v).getVal().floatValue()*(float)this.val);
        }else {
        	return null;
        }
	}

	@Override
	public Value div(Value v) {
		if(v instanceof IntegerV){
            return new IntegerV(this.val/((IntegerV)v).getVal().intValue());
		}else if (v instanceof DoubleV){
	    	return new DoubleV((double)this.val/((DoubleV)v).getVal().doubleValue());
	    }else if (v instanceof FloatV) {
	    	return new FloatV((float)this.val/((FloatV)v).getVal().floatValue());
        }else {
        	return null;
        }
	}

	@Override
	public Value pow(Value v) {
		if(v instanceof IntegerV){
            return new IntegerV(this.val^((IntegerV)v).getVal().intValue());
        }else {
        	return null;
        }
	}

	@Override
	public boolean eq(Value v) {
		if(v instanceof IntegerV) {
            return ((IntegerV)v).getVal().equals(this.val);
        }else if(v instanceof DoubleV) {
        	return ((DoubleV)v).getVal().equals((double)this.val);
        }else if(v instanceof FloatV) {
        	return ((FloatV)v).getVal().equals((float)this.val);
        }else if(v instanceof StringV) {
        	return ((StringV)v).getVal().equals(String.valueOf(this.val));
        }else 
        return false;
	}

	@Override
	public boolean lte(Value v) {
		if(v instanceof IntegerV) {
			int x = ((IntegerV)v).getVal();
			if (x<this.val) {
				return true;
			}
		}else if (v instanceof DoubleV) {
			double x = ((DoubleV)v).getVal();
			if (x<(double)this.val) {
				return true;
			}
		}else if (v instanceof FloatV) {
			float x = ((FloatV)v).getVal();
			if (x<(float)this.val) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean gte(Value v) {
		if(v instanceof IntegerV) {
			int x = ((IntegerV)v).getVal();
			if (x>this.val) {
				return true;
			}	
		}else if (v instanceof DoubleV) {
			double x = ((DoubleV)v).getVal();
			if (x>(double)this.val) {
				return true;
			}
		}else if (v instanceof FloatV) {
			float x = ((FloatV)v).getVal();
			if (x>(float)this.val) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean neq(Value v) {
		if(v instanceof IntegerV) {
            if (((IntegerV)v).getVal().equals(this.val)) {
            	return false;
            }
        } else if(v instanceof DoubleV) {
	    	if (((DoubleV)v).getVal().equals((double)this.val)){
			return false;
	    	}
		}else if(v instanceof FloatV) {
			if (((FloatV)v).getVal().equals((float)this.val)){
				return false;
			}
		}else if(v instanceof StringV) {
			if (((StringV)v).getVal().equals(String.valueOf(this.val))) {
				return false;
			}
		}
        return true;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof IntegerV) {
			IntegerV x = (IntegerV)other;
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
		 return new IntegerV(Integer.parseInt(s));
	}

}
