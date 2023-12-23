package org.example;
import reflection.SomeInterface;
import reflection.SomeOtherInterface;
public class SomeBean {
    @AutoInjectable
    private SomeInterface someField;
    @AutoInjectable
    private SomeOtherInterface someOtherField;


    public SomeBean() {}

    /**
     * A method that calls interface methods from fields with annotations.
     */
    public void go(){
        someField.doSome();
        someOtherField.doSome();
    }
}
