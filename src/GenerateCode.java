import javassist.*;

import java.io.IOException;

/**
 * author ywb
 * date 2022/1/17
 * desc
 */
public class GenerateCode {

    public static void main(String[] args) {
//        modifyMethod();
//        addMethod();
    }

    /**
     * 作用：a+b -> a*b
     * 修改对象：{@link test.Test#addNumber(int, int)}
     */
    public static void modifyMethod() {
        System.out.println("======== 开始修改 ========");

        try {
            ClassPool cPool = new ClassPool(true);

            // 如果该文件引入了其它类，需要利用类似如下方式声明
            // cPool.importPackage("java.util.List");

            // 设置class文件的位置
            cPool.insertClassPath("D:\\apk_fby\\javassist\\source");

            // 获取该class对象
            CtClass cClass = cPool.get("test.Test");

            // 获取到对应的方法
            CtMethod cMethod = cClass.getDeclaredMethod("addNumber");

            // 更改该方法的内部实现
            // 需要注意的是对于参数的引用要以$开始，不能直接输入参数名称
            cMethod.setBody("{ return $1*$2; }");

            // 替换原有的文件
            cClass.writeFile("D:\\apk_fby\\javassist\\modify_method");

            System.out.println("======== 修改完成 ========");

        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * add showParameters(int a,int b)
     */
    public static void addMethod() {
        System.out.println("======== 开始修改 ========");

        try {
            ClassPool cPool = new ClassPool(true);

            cPool.insertClassPath("D:\\apk_fby\\javassist\\source");

            CtClass cClass = cPool.get("test.Test");

            CtMethod cMethod = cClass.getDeclaredMethod("addNumber");

            // 增加一个新方法
            String methodStr = "public void showParameters(int a,int b){"
                    + "  System.out.println(\"First parameter: \"+a);"
                    + "  System.out.println(\"Second parameter: \"+b);"
                    + "}";
            CtMethod newMethod = CtNewMethod.make(methodStr, cClass);
            cClass.addMethod(newMethod);

            // 调用新增的方法
            cMethod.setBody("{ showParameters($1,$2);return $1-$2; }");

            cClass.writeFile("D:\\apk_fby\\javassist\\add_method");

            System.out.println("======== 修改完成 ========");
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个静态方法，无关联
     */
    public static void addMStaticMethod() {
        System.out.println("======== 开始修改 ========");

        try {
            ClassPool cPool = new ClassPool(true);

            cPool.insertClassPath("D:\\apk_fby\\javassist\\source");

            CtClass cClass = cPool.get("test.Test");

            String methodStr = "public static void showLog(String suffix) {"
                    + "  System.out.println(\"Stub!\");"
                    + "}";
            CtMethod newMethod = CtNewMethod.make(methodStr, cClass);
            cClass.addMethod(newMethod);

            cClass.writeFile("D:\\apk_fby");

            System.out.println("======== 修改完成 ========");
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
