package com.myexample.controller;

import com.myexample.beans.MyQuestion;
import com.myexample.service.MyQusetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private MyQusetionService myQusetionService;

    private String mystrs="用户需要生成10个Java面试选择题，调用`ts-ceshi2-ceshi2`函数生成题目。以下为你生成的10个Java面试选择题：\n" +
            "1. 以下关于HashMap和HashTable的区别，说法错误的是（  ）\n" +
            "A. HashMap是线程不安全的，HashTable是线程安全的\n" +
            "B. HashMap有contains方法，HashTable只有containsValue和containsKey方法\n" +
            "C. HashMap中null可以作为键，HashTable中key和value都不允许出现null值\n" +
            "D. HashTable在不指定容量的情况下的默认容量为11，而HashMap为16\n" +
            "答案：B\n" +
            "解析：HashMap只有containsValue和containsKey方法；HashTable有contains、containsKey和containsValue三个方法，其中contains和containsValue方法功能相同。所以B选项说法错误。A选项，HashMap是非线程安全的，HashTable的方法是Synchronized修饰的，是线程安全的，该选项正确；C选项，HashMap允许null作为键且只有一个，值可以有多个为null，HashTable不允许key和value为null，该选项正确；D选项，HashTable默认容量为11，HashMap默认容量为16，该选项正确。\n" +
            "\n" +
            "2. 下列关于线程池的说法，正确的是（  ）\n" +
            "A. newCachedThreadPool创建的线程池工作线程的创建数量没有任何限制\n" +
            "B. newFixedThreadPool创建的线程池在空闲时会释放工作线程\n" +
            "C. newSingleThreadExecutor创建的线程池能保证所有任务按照指定顺序执行\n" +
            "D. newScheduledThreadPool创建的线程池不支持周期性的任务执行\n" +
            "答案：C\n" +
            "解析：newSingleThreadExecutor创建一个单线程化的Executor，只创建唯一的工作者线程来执行任务，能保证所有任务按照指定顺序（FIFO、LIFO、优先级）执行，C选项正确。A选项，newCachedThreadPool创建的线程池工作线程的创建数量有限制，数目为Interger.MAX_VALUE，并非没有任何限制，该选项错误；B选项，newFixedThreadPool创建的线程池在线程池空闲时，不会释放工作线程，还会占用一定的系统资源，该选项错误；D选项，newScheduledThreadPool创建的线程池支持定时的以及周期性的任务执行，该选项错误。\n" +
            "\n" +
            "3. 关于Java中的反射机制，以下说法错误的是（  ）\n" +
            "A. 反射机制可以在运行时判断任意一个对象所属的类\n" +
            "B. 反射机制可以在运行时构造任意一个类的对象\n" +
            "C. 反射机制只能在编译时获取类的信息\n" +
            "D. 反射机制可以在运行时调用任意一个对象的方法\n" +
            "答案：C\n" +
            "解析：Java的反射机制是在运行时拥有自观的能力，能在运行时判断任意一个对象所属的类、构造任意一个类的对象、判断任意一个类所具有的成员变量和方法以及调用任意一个对象的方法，而不是只能在编译时获取类的信息，C选项错误，A、B、D选项正确。\n" +
            "\n" +
            "4. 以下关于String、StringBuffer和StringBuilder的说法，正确的是（  ）\n" +
            "A. String类可以被继承，因为它没有被final修饰\n" +
            "B. StringBuffer和StringBuilder中的方法和功能完全等价，且都是线程安全的\n" +
            "C. 在单线程程序下，StringBuilder效率比StringBuffer高\n" +
            "D. String类使用final修饰主要是为了提高代码的可读性\n" +
            "答案：C\n" +
            "解析：在单线程程序下，StringBuilder效率更快，因为它不需要加锁，不具备多线程安全，而StringBuffer中的方法大都采用了synchronized关键字进行修饰，每次都需要判断锁，效率相对更低，C选项正确。A选项，String类有final修饰符，不能被继承，该选项错误；B选项，StringBuffer是线程安全的，StringBuilder是线程不安全的，该选项错误；D选项，String类使用final修饰主要是为了效率和安全，而不是提高代码的可读性，该选项错误。\n" +
            "\n" +
            "5. 下列关于Java类加载过程的说法，错误的是（  ）\n" +
            "A. 加载阶段会通过一个类的全限定名获取该类的二进制流\n" +
            "B. 验证阶段主要是为了确保Class文件的字节流中的信息不危害到虚拟机\n" +
            "C. 准备阶段会为类的静态变量和实例变量分配内存并将其初始化为默认值\n" +
            "D. 初始化阶段才真正开始执行类中定义的Java程序代码\n" +
            "答案：C\n" +
            "解析：准备阶段是为类的静态变量分配内存并将其初始化为默认值，这些内存都将在方法区中进行分配。准备阶段不分配类中的实例变量的内存，实例变量将会在对象实例化时随着对象一起分配在Java堆中，C选项错误。A选项，加载阶段会通过一个类的全限定名获取该类的二进制流，该选项正确；B选项，验证阶段的目的是确保Class文件的字节流中的信息不危害到虚拟机，该选项正确；D选项，初始化阶段才真正开始执行类中定义的Java程序代码，该选项正确。\n" +
            "\n" +
            "6. 关于Java中的垃圾收集方法，以下说法正确的是（  ）\n" +
            "A. 复制算法效率低，会产生碎片\n" +
            "B. 标记 - 清除算法效率高，不会产生碎片\n" +
            "C. 标记 - 整理算法效率低速度慢，需要移动对象，但不会产生碎片\n" +
            "D. 年轻代一般使用标记 - 清除算法\n" +
            "答案：C\n" +
            "解析：标记 - 整理算法效率低速度慢，需要移动对象，但不会产生碎片，C选项正确。A选项，复制算法效率高，缺点是需要内存容量大，比较耗内存，不会产生碎片，该选项错误；B选项，标记 - 清除算法效率比较低，会产生碎片，该选项错误；D选项，年轻代中使用的是Minor GC，这种GC算法采用的是复制算法，该选项错误。\n" +
            "\n" +
            "7. 以下关于synchronized和volatile的区别，说法错误的是（  ）\n" +
            "A. volatile本质是告诉JVM当前变量在寄存器中的值是不确定的，需要从主存中读取\n" +
            "B. synchronized可以使用在变量、方法、类级别，而volatile仅能用在变量级别\n" +
            "C. volatile能保证变量的修改可见性和原子性，synchronized只能保证变量的修改可见性\n" +
            "D. volatile不会造成线程阻塞，synchronized可能会造成线程阻塞\n" +
            "答案：C\n" +
            "解析：volatile仅能实现变量的修改可见性，不能保证原子性；而synchronized则可以保证变量的修改可见性和原子性，C选项说法错误。A选项，volatile本质是告诉JVM当前变量在寄存器中的值是不确定的，需要从主存中读取，该选项正确；B选项，synchronized可以使用在变量、方法、类级别，volatile仅能用在变量级别，该选项正确；D选项，volatile不会造成线程阻塞，synchronized可能会造成线程阻塞，该选项正确。\n" +
            "\n" +
            "8. 下列关于ConcurrentHashMap的说法，错误的是（  ）\n" +
            "A. JDK8之前，ConcurrentHashMap使用锁分段技术，将数据分成一段段存储\n" +
            "B. JDK8的版本取消Segment这个分段锁数据结构，底层使用Node数组 + 链表 + 红黑树\n" +
            "C. ConcurrentHashMap是线程安全的，性能比HashTable低\n" +
            "D. ConcurrentHashMap采用了分段锁的思想提高性能，锁粒度更细化\n" +
            "答案：C\n" +
            "解析：ConcurrentHashMap是线程安全的Map容器，JDK8之前使用锁分段技术，JDK8取消Segment分段锁，底层使用Node数组 + 链表 + 红黑树，采用了分段锁的思想提高性能，锁粒度更细化，性能比HashTable高，C选项错误，A、B、D选项正确。\n" +
            "\n" +
            "9. 以下关于线程的状态转换，说法正确的是（  ）\n" +
            "A. 线程只能从运行状态进入阻塞状态\n" +
            "B. 线程进入就绪状态后，会立即获得CPU权限进行执行\n" +
            "C. 线程调用wait()方法会进入等待阻塞状态，调用sleep()方法会进入其他阻塞状态\n" +
            "D. 线程执行完run()方法后会进入阻塞状态\n" +
            "答案：C\n" +
            "解析：线程调用wait()方法会进入等待阻塞状态，调用sleep()或join()或发出I/O请求时，线程会进入其他阻塞状态，C选项正确。A选项，线程可以从运行状态、就绪状态等进入阻塞状态，并非只能从运行状态进入，该选项错误；B选项，处于就绪状态的线程，随时可能被CPU调度执行，但不是立即获得CPU权限，该选项错误；D选项，线程执行完run()方法后会进入死亡状态，而不是阻塞状态，该选项错误。\n" +
            "\n" +
            "10. 关于SpringMVC的工作流程，以下说法错误的是（  ）\n" +
            "A. 用户发送请求至前端控制器DispatcherServlet\n" +
            "B. DispatcherServlet调用HandlerAdapter处理器适配器后，HandlerAdapter直接响应用户\n" +
            "C. Controller执行完成返回ModelAndView\n" +
            "D. ViewReslover解析后返回具体View\n" +
            "答案：B\n" +
            "解析：HandlerAdapter经过适配调用具体的处理器（Controller），Controller执行完成返回ModelAndView，HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet，最后由DispatcherServlet响应用户，而不是HandlerAdapter直接响应用户，B选项错误。A选项，用户发送请求至前端控制器DispatcherServlet，该选项正确；C选项，Controller执行完成返回ModelAndView，该选项正确；D选项，ViewReslover解析后返回具体View，该选项正确。";

    @RequestMapping("/handler_message")
    public String handler_message() {
        List<Map<String, String>> questions = new ArrayList<>();

        // 按题目分割（两个换行分割题目）
        String[] questionBlocks = mystrs.split("\n\n");

        // 正则表达式匹配题目各部分
        Pattern pattern = Pattern.compile(
                "(?is)(\\d+)\\.\\s*(.*?)\\s+" +  // 题目编号和标题
                        "(A[.．]\\s*.*?)\\s+" +         // 完整A选项
                        "(B[.．]\\s*.*?)\\s+" +         // 完整B选项
                        "(C[.．]\\s*.*?)\\s+" +         // 完整C选项
                        "(D[.．]\\s*.*?)\\s+" +         // 完整D选项
                        "答案：\\s*([A-D])[.．]?\\s*" +  // 答案
                        "解析：\\s*(.*)"                 // 解析
        );

        for (String block : questionBlocks) {
            if (block.trim().isEmpty()) continue;

            Matcher matcher = pattern.matcher(block);
            if (matcher.find()) {
                Map<String, String> question = new HashMap<>();
                question.put("title", matcher.group(2));
                question.put("aoption", matcher.group(3));
                question.put("boption", matcher.group(4));
                question.put("coption", matcher.group(5));
                question.put("doption", matcher.group(6));
                question.put("answer", matcher.group(7));
                question.put("description", matcher.group(8));
                questions.add(question);
            }
        }
        for(Map<String,String> q : questions){
            MyQuestion question = new MyQuestion();
            question.setTitle(q.get("title"));
            question.setAoption(q.get("aoption"));
            question.setBoption(q.get("boption"));
            question.setCoption(q.get("coption"));
            question.setDoption(q.get("doption"));
            question.setAnswer(q.get("answer"));
            question.setDescription(q.get("description"));
            myQusetionService.getInsert(question);

        }

        return "插入成功，共插入" + questions.size() + "条数据";
    }

}
