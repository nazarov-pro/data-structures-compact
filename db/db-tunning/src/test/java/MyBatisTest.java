import org.apache.ibatis.session.SqlSession;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.shahin.nazarov.db.tunning.domain.Test1;
import org.shahin.nazarov.db.tunning.mybatis.mapper.Test1Mapper;
import org.shahin.nazarov.db.tunning.util.MyBatisFactory;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class MyBatisTest {

    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        protected void succeeded(long nanos, Description description) {
            double seconds = (double) nanos / 1_000_000_000.0;
            System.out.println(description.getMethodName() + " succeeded, time taken " + seconds + " seconds");
        }

        /**
         * Invoked when a test fails
         */
        protected void failed(long nanos, Throwable e, Description description) {
            System.out.println(description.getMethodName() + " failed, time taken " + nanos);
        }

        /**
         * Invoked when a test is skipped due to a failed assumption.
         */
        protected void skipped(long nanos, AssumptionViolatedException e,
                               Description description) {
            System.out.println(description.getMethodName() + " skipped, time taken " + nanos);
        }

        /**
         * Invoked when a test method finishes (whether passing or failing)
         */
        protected void finished(long nanos, Description description) {
            System.out.println(description.getMethodName() + " finished, time taken " + nanos);
        }

    };


    private MyBatisFactory myBatisFactory = new MyBatisFactory();

    private  void processPostConstruct(Class type) {
        Method[] declaredMethods = type.getDeclaredMethods();

        Arrays.stream(declaredMethods)
                .filter(method -> method.getAnnotation(PostConstruct.class) != null)
                .forEach(postConstructMethod -> {
                    try {
                        Class<?> c = Class.forName(type.getName());
                        Constructor<?> cons = c.getConstructor();
                        Object object = cons.newInstance();
                        postConstructMethod.setAccessible(true);
                        postConstructMethod.invoke(object, new Object[]{});
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    public void test() {

//        processPostConstruct(HikariDataSource.class);
        SqlSession session = myBatisFactory.getSqlSession();
        Test1Mapper mapper = session.getMapper(Test1Mapper.class);
        System.out.println("Size " + mapper.listTest1().size());
        session.commit();
        session.close();
    }

    @Test
    public void testGet(){
        SqlSession session = new MyBatisFactory().getSqlSession();
        Test1Mapper mapper = session.getMapper(Test1Mapper.class);
        String test1 = "c7b13c29-5389-43e5-926b-f5c230498aa8";
        String test2 = "5ef0d15b-4d33-4a63-b1e1-33105bf45d71";
        String test3 = "20190129da8c8dde-01ef-42cb-a3ac-dd2195b65ec7";
        System.out.println("Size " + mapper.selectById(test3).getAmount());
        session.commit();
        session.close();
    }

    @Test
    public void multipleInsertion() {
        SqlSession session = new MyBatisFactory().getSqlSession();
        Test1Mapper mapper = session.getMapper(Test1Mapper.class);
        Random random = new Random();

        for (int i = 0; i < 100000; i++) {
            Test1 test1 = new Test1();
            test1.setId("20190130" + UUID.randomUUID().toString());
            test1.setAmount(random.nextDouble());
            test1.setData("{\"data\": " + random.nextInt() + "}");
            test1.setGender(random.nextBoolean());
            test1.setName(randomText(100));
            test1.setTimestamp(LocalDateTime.now());
            test1.setStatus(randomText(5));
            mapper.insertTest1(test1);
        }
        session.commit();
        session.close();
    }


    public String randomText(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
