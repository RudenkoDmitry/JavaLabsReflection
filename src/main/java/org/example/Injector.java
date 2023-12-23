package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Данный класс реализует внедрение зависимостей в любой объект, в котором содержатся поля, помеченные аннотацией @AutoInjectable
 *
 */
public class Injector <T>{
    /**
     * Поле ссылки объекта конфигурации
     */
    private final Properties properties;

    /**
     * Конструктор класса
     * @param pathToPropertiesFile Параметр хранит путь в файлу конфигурации.
     * @throws IOException исключение, которое генерируется при возникновении ошибки ввода-вывода.
     */
    public Injector(String pathToPropertiesFile) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(new File(pathToPropertiesFile)));
    }

    /**
     * Метод принимает в качестве параметра объект любого класса и проверяет его на наличие полей с аннотацией AutoInjectable
     * Если поле найденно, функция исходя из его типа определяет реализацию в файле scratch.properties.
     * @param obj Параметр объекта любого класса
     * @return Объект с инициализированными полями аннотацией AutoInjectable
     */
    public T inject(T obj) throws IOException, IllegalAccessException, InstantiationException {

        Class dependency;
        Class curClass = obj.getClass();

        Field[] fields = curClass.getDeclaredFields();
        for (Field field: fields){

            Annotation a = field.getAnnotation(AutoInjectable.class);
            if (a != null){

                String[] fieldType = field.getType().toString().split(" ");
                String equalsClassName = properties.getProperty(fieldType[1], null);

                if (equalsClassName != null){

                    try {
                        dependency = Class.forName(equalsClassName);

                    } catch (ClassNotFoundException e){
                        System.out.println("Class for " + equalsClassName+ " not found");
                        continue;
                    }

                    field.setAccessible(true);
                    field.set(obj, dependency.newInstance());
                }
                else
                    System.out.println("Properties for field type " + fieldType[1]+ " not found");
            }
        }
        return obj;
    }
}
