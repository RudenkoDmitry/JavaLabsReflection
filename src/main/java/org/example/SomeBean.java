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
     * Метод вызывающий методы из полей полученных в анотации.
     */
    public void go(){
        someField.doSome();
        someOtherField.doSome();
    }
}
