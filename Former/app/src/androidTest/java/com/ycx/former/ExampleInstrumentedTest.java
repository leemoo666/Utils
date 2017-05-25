package com.ycx.former;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Base64;
import android.util.Log;

import com.ycx.former.bean.Course;
import com.ycx.former.bean.Student;
import com.ycx.former.suanfa.ErFenSearch;
import com.ycx.former.suanfa.Foctorial;
import com.ycx.former.suanfa.LinkX;
import com.ycx.former.suanfa.QueueX;
import com.ycx.former.suanfa.Sort;
import com.ycx.former.suanfa.StackX;
import com.ycx.former.utils.AESUtils;
import com.ycx.former.utils.RSAUtils;
import com.ycx.former.utils.RSAUtilsBak;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 * <p>
 * http://www.cnblogs.com/zhaoyanjun/p/5502804.html
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.ycx.former", appContext.getPackageName());
    }


    //    1、merge操作符，合并观察对象
    @Test
    public void testMerge() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("1");
        list1.add("2");
        list1.add("3");

        list2.add("a");
        list2.add("b");
        list2.add("c");

        Observable observable1 = Observable.from(list1);
        Observable observable2 = Observable.from(list2);

        //合并数据  先发送observable2的全部数据，然后发送 observable1的全部数据
        Observable observable = Observable.merge(observable2, observable1);

        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i("lxm", "rx-- " + o);
            }
        });

    }

    //    2、zip  操作符，合并多个观察对象的数据。并且允许 Func2（）函数重新发送合并后的数据
    @Test
    public void testZip() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("1");
        list1.add("2");
        list1.add("3");

        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("d");

        Observable observable1 = Observable.from(list1);
        Observable observable2 = Observable.from(list2);

        Observable observable3 = Observable.zip(observable1, observable2, new Func2<String, String, String>() {
            @Override
            public String call(String s1, String s2) {
                return s1 + s2;
            }
        });

        observable3.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i("lxm", "zip-- " + o);
            }
        });
    }

    //        3、scan累加器操作符的使用
    @Test
    public void testScan() {
        Observable observable = Observable.just(1, 2, 3, 4, 5);
        observable.scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer o, Integer o2) {
                return o + o2;
            }
        }).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i("lxm", "scan-- " + o);
            }
        });

    }


    //    4、filter 过滤操作符的使用
    @Test
    public void testFilter() {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer o) {
                //数据大于4的时候才会被发送
                return o > 4;
            }
        }).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i("lxm", "filter-- " + o);
            }
        });
    }

//    5、 消息数量过滤操作符的使用
//    take ：取前n个数据
//    takeLast：取后n个数据
//    first 只发送第一个数据
//    last 只发送最后一个数据
//    skip() 跳过前n个数据发送后面的数据
//    skipLast() 跳过最后n个数据，发送前面的数据

    @Test
    public void testTake() {
        //take 发送前3个数据
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable.take(3)
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("take-- " + o);
                        Log.i("lxm", "take-- " + o);
                    }
                });

        //takeLast 发送最后三个数据
        Observable observable2 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable2.takeLast(3)
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("takeLast-- " + o);
                        Log.i("lxm", "takeLast-- " + o);
                    }
                });

        //first 只发送第一个数据
        Observable observable3 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable3.first()
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("first-- " + o);
                        Log.i("lxm", "first-- " + o);
                    }
                });

        //last 只发送最后一个数据
        Observable observable4 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable4.last()
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("last-- " + o);
                        Log.i("lxm", "last-- " + o);
                    }
                });

        //skip() 跳过前2个数据发送后面的数据
        Observable observable5 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable5.skip(2)
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("skip-- " + o);
                        Log.i("lxm", "skip-- " + o);
                    }
                });

        //skipLast() 跳过最后两个数据，发送前面的数据
        Observable observable6 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable6.skipLast(2)
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("skipLast-- " + o);
                        Log.i("lxm", "skipLast-- " + o);
                    }
                });
    }

    //    6、elementAt 、elementAtOrDefault
    @Test
    public void testElement() {
        //elementAt() 发送数据序列中第n个数据 ，序列号从0开始
        //如果该序号大于数据序列中的最大序列号，则会抛出异常，程序崩溃
        //所以在用elementAt操作符的时候，要注意判断发送的数据序列号是否越界

        Observable observable7 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable7.elementAt(3)
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("elementAt-- " + o);
                        Log.i("lxm", "elementAt-- " + o);
                    }
                });

        //elementAtOrDefault( int n , Object default ) 发送数据序列中第n个数据 ，序列号从0开始。
        //如果序列中没有该序列号，则发送默认值
        Observable observable9 = Observable.just(1, 2, 3, 4, 5);
        observable9.elementAtOrDefault(0, 666)
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("elementAtOrDefault-- " + o);
                        Log.i("lxm", "elementAtOrDefault-- " + o);
                    }
                });
    }

    //    7、startWith() 插入数据
    @Test
    public void testStartWith() {
//插入普通数据
//startWith 数据序列的开头插入一条指定的项 , 最多插入9条数据
        Observable observable = Observable.just("aa", "bb", "cc");
        observable
                .startWith("11", "22")
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("startWith-- " + o);
                        Log.i("lxm", "startWith-- " + o);
                    }
                });

//插入Observable对象
        List<String> list = new ArrayList<>();
        list.add("ww");
        list.add("tt");
        observable.startWith(Observable.from(list))
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        System.out.println("startWith2 -- " + o);
                        Log.i("lxm", "startWith2-- " + o);
                    }
                });
    }

    //    delay操作符，延迟数据发送
    @Test
    public void testDelay() {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6", "7", "8");

        //延迟数据发射的时间，仅仅延时一次，也就是发射第一个数据前延时。发射后面的数据不延时
        observable.delay(3, TimeUnit.SECONDS)  //延迟3秒钟
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        Log.i("lxm", "delay-- " + o);
                    }
                });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimer() {
        //5秒后输出 hello world , 然后显示一张图片
        Observable.timer(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("timer--hello world " + aLong);
                        Log.i("lxm", "timer ---" + aLong);
                    }
                });
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInterval() {

        //参数一：延迟时间  参数二：间隔时间  参数三：时间颗粒度
        Observable observable = Observable.interval(3000, 3000, TimeUnit.MILLISECONDS);
        Subscription subscription = observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("interval-  " + o);
                Log.i("lxm", "interval");
            }
        });
    }

    //     11、doOnNext() 操作符，在每次 OnNext() 方法被调用前执行
    @Test
    public void testDoNext() {
        Observable observable = Observable.just("1", "2", "3", "4");
        observable.doOnNext(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("doOnNext--缓存数据" + o);
                Log.i("lxm", "doOnNext--缓存数据" + o);
            }
        }).subscribe(new Observer() {
            @Override
            public void onCompleted() {
                Log.i("lxm", "onCompleted--");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("lxm", "onError--");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext--" + o);
                Log.i("lxm", "onNext--" + o);
            }
        });
    }

    //     12、Buffer 操作符
//
//    Buffer( int n )      把n个数据打成一个list包，然后再次发送。
//    Buffer( int n , int skip)   把n个数据打成一个list包，然后跳过第skip个数据。
//
//
//    使用场景：一个按钮每点击3次，弹出一个toast
    @Test
    public void testBuffer() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add("" + i);
        }

        Observable<String> observable = Observable.from(list);
        observable
                .buffer(2)   //把每两个数据为一组打成一个包，然后发送
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        System.out.println("buffer---------------");
                        Log.i("lxm", "buffer.....");
                        Observable.from(strings).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println("buffer data --" + s);
                                Log.i("lxm", "buffer data --" + s);
                            }
                        });
                    }
                });
    }

    //    13、throttleFirst 操作符
//
//    在一段时间内，只取第一个事件，然后其他事件都丢弃。
//
//    使用场景：1、button按钮防抖操作，防连续点击   2、百度关键词联想，在一段时间内只联想一次，防止频繁请求服务器

    //    这段代码，是循环发送数据，每秒发送一个。throttleFirst( 3 , TimeUnit.SECONDS )   在3秒内只取第一个事件，其他的事件丢弃。
    @Test
    public void testThrottleFirst() {

        Observable.interval(1, TimeUnit.SECONDS)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("throttleFirst--" + aLong);
                        Log.i("lxm", "throttleFirst --" + aLong);
                    }
                });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //    14、distinct    过滤重复的数据
    @Test
    public void testDistinct() {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("3");
        list.add("4");
        list.add("2");
        list.add("1");
        list.add("1");

        Observable.from(list)
                .distinct()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("distinct--" + s);
                        Log.i("lxm", "distinct --" + s);
                    }
                });
    }


    //    distinctUntilChanged()  过滤连续重复的数据
    @Test
    public void testDistinctUntilChanged() {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("3");
        list.add("4");
        list.add("4");
        list.add("2");
        list.add("1");
        list.add("1");

        Observable.from(list)
                .distinctUntilChanged()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("distinctUntilChanged--" + s);
                        Log.i("lxm", "distinctUntilChanged --" + s);
                    }
                });
    }


    @Test
    public void testDoOnSubscribe() {
//        16、doOnSubscribe()
//
//        使用场景： 可以在事件发出之前做一些初始化的工作，比如弹出进度条等等
//
//        注意：
//
//        1、doOnSubscribe() 默认运行在事件产生的线程里面，然而事件产生的线程一般都会运行在 io 线程里。那么这个时候做一些，更新UI的操作，是线程不安全的。
//
//        所以如果事件产生的线程是io线程，但是我们又要在doOnSubscribe() 更新UI ， 这时候就需要线程切换。
//
//        2、如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
//
//        3、 subscribeOn() 事件产生的线程 ； observeOn() : 事件消费的线程

        Observable.just("1111", "2222", "3333").create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i("lxm", ".....");
            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                Log.i("lxm", "call");
            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i("lxm", "xxxxx = " + s);
                    }
                });
    }

    @Test
    public void testRange() {
        Observable.range(10, 20)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i("lxm", "" + integer);
                    }
                });
    }

    @Test
    public void testMap() {

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("course111", "000000001"));
        courses.add(new Course("course222", "000000002"));
        courses.add(new Course("course333", "000000003"));
        courses.add(new Course("course444", "000000004"));
        Student[] students = {new Student("1111", "0001", 20, courses),
                new Student("2222", "0002", 21, courses),
                new Student("3333", "0003", 22, courses),
                new Student("4444", "0004", 23, courses)};

        Observable.from(students)
                .map(new Func1<Student, List<Course>>() {
                    @Override
                    public List<Course> call(Student student) {
                        Log.i("lxm", "student=" + student.getName());
                        return student.getCourse();
                    }
                })
                //被观察者处于新io线程
//                .subscribeOn(Schedulers.io())
                //观察者处于主线程
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        Log.i("lxm", "course size = " + courses.size());
                        for (int i = 0; i < courses.size(); i++) {
                            Log.i("lxm", "观察者:" + courses.get(i).getCourseId() + ":" + courses.get(i).getCourseName());
                        }
                    }
                });
    }

    @Test
    public void testFlatMap() {

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("course111", "000000001"));
        courses.add(new Course("course222", "000000002"));
        courses.add(new Course("course333", "000000003"));
        courses.add(new Course("course444", "000000004"));
        Student[] students = {new Student("1111", "0001", 20, courses),
                new Student("2222", "0002", 21, courses),
                new Student("3333", "0003", 22, courses),
                new Student("4444", "0004", 23, courses)};

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourse());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.i("lxm", "course = " + course.getCourseName());
                    }
                });
    }

    @Test
    public void testRSA() throws Exception {

        String str = RSAUtils.encryptByPublicKey("你好啊,云车行网络技术(北京)有限公司");
        Log.i("lxm", "加密后 = " + str);
        String ss = RSAUtils.decryptByPrivateKey(str);
        Log.i("lxm", "解密后 = " + ss);

//        RSAUtils.getKeys();

    }

    @Test
    public void testRSABak() throws Exception {

        String str = RSAUtilsBak.encryptByPublicKey("你好啊,云车行网络技术(北京)有限公司");

        Log.i("lxm", "加密后 = " + str);

        String ss = RSAUtilsBak.decryptByPrivateKey(str);
        Log.i("lxm", "解密后 = " + ss);
    }

    @Test
    public void testAES() {

        String str = AESUtils.encryptData("1bd83b249a414036", "你好啊,云车行网络技术(北京)有限公司");
        Log.i("lxm", "str = " + str);

        String ss = AESUtils.decryptData("1bd83b249a414036", str);
        Log.i("lxm", "ss = " + ss);
    }


    //二分查找
    @Test
    public void testErFenSearch() {
        Log.i("lxm", " find index = " + ErFenSearch.testFind(14));
    }

    //冒泡排序
    @Test
    public void testSort() {
        Sort.sortAsc();
    }


    //选择排序
    @Test
    public void testSelectSort() {
        Sort.selectSort();
    }

    //选择排序
    @Test
    public void insertSort() {
        Sort.insertSort();
    }

    //单次逆序
    @Test
    public void reverseStack() {
        StackX.reverseChar();
    }

    //校验分隔符
    @Test
    public void separatorStack() {
        StackX.separator();
    }

    //校验分隔符
    @Test
    public void testQueue() {
        QueueX.queue();
    }

    @Test
    public void testLink() {
        LinkX.testLink();
    }

    @Test
    public void testFactorial() {
       Log.i("lxm","total = "+Foctorial.factorial(10));
    }

}
