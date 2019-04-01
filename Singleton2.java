import java.io.ObjectStreamException;

/**
 * @author Liquid
 * @类名： Singleton2
 * @描述： 内部类单例模式，安全效率都有保障
 * @date 2019/3/26
 */
public class Singleton2 {

    /**
    *   内部类创建实例（内部类只在线程第一次访问时初始化化（延迟加载）
    */
    private static class SingletonClass {
        private static Singleton2 uniqueInstance = new Singleton2();
    }

    /**
     * 构造方法私有 （用判断防止反射创建实例）
     */
    private Singleton2() {
        if (SingletonClass.uniqueInstance != null) {
            throw new RuntimeException("不能创建第二个实例");
        }
    }

    /**
    *   获取单例对象，jvm会自动保证内部类中实例的线程安全
    */
    public static Singleton2 getUniqueInstance() {
        return SingletonClass.uniqueInstance;
    }

    private Object readResolve() throws ObjectStreamException {
        //反序列化调用该方法产生新的实例，反正产生多个实例。
        return SingletonClass.uniqueInstance;
    }
}
