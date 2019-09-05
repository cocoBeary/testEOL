import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


class MyData {
    volatile int number = 0;

    public void addNumber() {
        number = 60;
    }
}


public class Job {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {

        volatileKeepVisible();

    }

    private static void volatileKeepVisible() {
        MyData data = new MyData();
        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addNumber();
            System.out.println("number" + data.number);
        },"AAA").start();

        while(data.number==0){

        }

        System.out.println("haha");
    }

    static void test2() {
        MyClass child1 = new MyChild();
        MyClass child2 = new MyChild2();
        child1.gender = "haha";
        System.out.println(child1.toString());
        System.out.println(child2.toString());
        try {
            throw new IOException("Test IO Ex QJ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void test1() {
        try {
            MyClass parent = new MyChild();
            parent.setAge(13);
            parent.gender = "unknow";
            ((MyChild) parent).gender = "female";
            System.out.println(parent.gender); // get from parent, member variable follow the reference type.
            System.out.println(((MyChild) parent).gender); // get from child, member variable follow the reference type.
            System.out.println(parent.getAge()); // always get from child, member method follow the actual implementation
            System.out.println(((MyClass) parent).getAge()); // always get from child, member method follow the actual implementation
            System.out.println(((MyChild) parent).getAge()); // always get from child, member method follow the actual implementation

            MyChild child = new MyChild();
            child.setAge(13);
            child.gender = "female";
            ((MyClass) child).gender = "unknown";
            System.out.println(child.gender); // get from child, member variable follow the reference type.
            System.out.println(((MyClass) child).gender); // get from parent, member variable follow the reference type.
            System.out.println(child.getAge()); // always get from child, member method follow the actual implementation
            System.out.println(((MyClass) child).getAge()); // always get from child, member method follow the actual implementation

            ClassLoader classLoader = Job.class.getClassLoader();
            ClassLoader classLoader1 = MyClass.class.getClassLoader();
            System.out.println(classLoader);
            System.out.println(classLoader1);
            InputStream resourceAsStream = classLoader.getResourceAsStream("");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
