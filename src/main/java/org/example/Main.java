package org.example;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        SomeBean sb = (new Injector<SomeBean>("src/main/java/config/scratch.properties").inject(new SomeBean()));
        sb.go();
    }
}