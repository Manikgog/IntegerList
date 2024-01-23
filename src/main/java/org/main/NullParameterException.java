package org.main;

public class NullParameterException extends IndexOutOfBoundsException {
    public NullParameterException(){
        super();
    }
    public NullParameterException(String s) {
        super(s);
    }


    @Override
    public String toString() {
        return super.getMessage();
    }
}
