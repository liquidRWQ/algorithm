import java.io.ObjectStreamException;
import java.io.Serializable;
import java.rmi.AccessException;

/**
 * @author Liquid
 * @类名： Singleton
 * @描述： 懒汉式单例模式模板
 * @date 2019/3/26
 */
public class Singleton implements Serializable {
    /**
     * 单例对象（volatile实现变量的可见性）
     */
    private static volatile Singleton uniqueInstance;

    /**
     * 构造方法私有 （用判断防止反射创建实例）
     */
    private Singleton() {
        if (uniqueInstance != null) {
            throw new RuntimeException("不能创建第二个实例");
        }
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    //new Singleton(）可能会因为重排序而产生线程安全问题
                    uniqueInstance = new Singleton();
                    System.out.println(Thread.currentThread().getName() + "  :get a new singleton");
                }
            }
        }
        return uniqueInstance;
    }

    private Object readResolve() throws ObjectStreamException {
        //反序列化调用该方法产生新的实例，反正产生多个实例。
        return uniqueInstance;
    }

    /**
     * 测试单例模式的准确性
     */
    public static void main(String[] args) {
        for (int i = 1; i < 100000; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton uniqueInstance = Singleton.getUniqueInstance();
                    uniqueInstance.toString();
                }
            });
            t.setName("线程" + i);
            t.start();
        }

    }

}
