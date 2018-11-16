
 final class COOValue extends Value{
	        private final Value wartosc;
	        private final int indeks;

	         COOValue(Value wartosc, int indeks) {
	            this.wartosc = wartosc;
	            this.indeks = indeks;
	        }

	        public int zwrocIndeks() {
	            return indeks;
	        }

	        public Object zwrocWartosc(){
	            return wartosc;
	        }
	    	@Override
	    	public String toString() {
	    		return wartosc.toString();
	    	}

	    /*	public COOValue(Object wartosc){
	    	        this.wartosc=wartosc;
	    	    }

	    	public COOValue(){};
	    	*/
	    	    
	    	public Object getVal() {
	    		return this.wartosc;
	    	}
	    	
	    	
	    	@Override
	    	public Value add(Value v) {
	    	      return this.wartosc.add(v);
	    	}

	    	@Override
	    	public Value sub(Value v) {
	    		return this.wartosc.sub(v);
	    	}

	    	@Override
	    	public Value mul(Value v) {
	    		return this.wartosc.mul(v);
	    	}

	    	@Override
	    	public Value div(Value v) {
	            	return this.wartosc.div(v);
	    	}

	    	@Override
	    	public Value pow(Value v) {
	            	return this.wartosc.pow(v);
	    	}

	    	@Override
	    	public boolean eq(Value v) {
	    		return this.wartosc.eq(v);
	    	}

	    	@Override
	    	public boolean lte(Value v) {
	    		return this.wartosc.lte(v);
	    	}

	    	@Override
	    	public boolean gte(Value v) {
	    		return this.wartosc.gte(v);
	    	}

	    	@Override
	    	public boolean neq(Value v) {
	    		return this.wartosc.neq(v);
	    	}

	    	@Override
	    	public boolean equals(Object other) {
	    		return this.wartosc.equals(other);
	    	}

	    	@Override
	    	public int hashCode() {
	    		 return this.wartosc.hashCode();
	    	}

	    	@Override
	    	public Value create(String s) {
	    		 return this.wartosc.create(s);
	    	}
	        
	    }