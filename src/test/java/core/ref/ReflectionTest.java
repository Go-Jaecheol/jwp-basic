package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;
import next.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        Field[] fields = clazz.getDeclaredFields();
        Constructor[] constructors = clazz.getConstructors();
        Method[] methods = clazz.getMethods();
        logger.debug(clazz.getName());
        for (Field field : fields) {
            logger.debug(String.valueOf(field));
        }
        for (Constructor constructor : constructors) {
            logger.debug(String.valueOf(constructor));
        }
        for (Method method : methods) {
            logger.debug(String.valueOf(method));
        }
    }
    
    @Test
    public void newInstanceWithConstructorArgs() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            constructor.newInstance("test", "password", "test", "test@abc");
        }
    }
    
    @Test
    public void privateFieldAccess() throws IllegalAccessException {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());

        Student student = new Student();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (String.valueOf(field.getType()).equals("int")) {
                field.setInt(student, 10);
            }
            else {
                field.set(student, "test");
            }
        }
    }
}
