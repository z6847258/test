import org.junit.Test;

import redis.clients.jedis.Jedis;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DemoApplicationTests {

    private Jedis jedis;

    /**
     * redis操作字符串
     */
    @Test
    public void testString() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
        // 添加数据
        jedis.set("name", "xiangjun");
        System.out.println("redis中name的字符串:" + jedis.get("name"));

        // 拼接字符串
        jedis.append("name", ".com");
        System.out.println("redis中name后面追加字符串:" + jedis.get("name"));

        // 删除数据
        jedis.del("name");
        System.out.println("redis删除name:" + jedis.get("name"));

        // 设置多个键值对
        jedis.mset("name", "xiangjun", "age", "23", "qq", "47670002");
        jedis.incr("age"); // 加1操作
        System.out.println("redis删除name多个键值对:" + jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
    }
    /**
     * redis操作map集合(hash)
     */
    @Test
    public void testMap() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
        // 添加数据
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xiangjun");
        map.put("age", "22");
        map.put("qq", "5443343");
        jedis.hmset("user", map);

        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println("redis中map对象:" + rsmap);


        jedis.hdel("user", "age");
        System.out.println("user中是否有age数据:" + jedis.hmget("user", "age"));
        System.out.println("user的键中存放的值的个数:" + jedis.hlen("user"));
        System.out.println("是否存在key为user的记录:" + jedis.exists("user"));
        System.out.println("返回map对象中的所有key:" + jedis.hkeys("user"));
        System.out.println("返回map对象中的所有value:" + jedis.hvals("user"));

        //获取user,通过迭代器的方式,遍历拿到每一个数据
        Iterator<String> iter = jedis.hkeys("user").iterator();
        System.out.println("返回map对象中的对象:" + iter);
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
    }
    /**
     * redis操作list集合
     */
    @Test()
    public void testList() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
        // 开始前，先移除所有的内容
        jedis.del("list");
        System.out.println(jedis.lrange("list", 0, -1));
        // 先向key java framework中存放三条数据
        jedis.lpush("list", "spring");
        jedis.lpush("list", "struts");
        jedis.lpush("list", "hibernate");
        // 再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println("从左边放入：" + jedis.lrange("list", 0, -1));

        jedis.del("list");
        jedis.rpush("list", "spring");
        jedis.rpush("list", "struts");
        jedis.rpush("list", "hibernate");
        System.out.println("从右边放入：" + jedis.lrange("list", 0, -1));
    }
    /**
     * redis操作set集合(自动排重)
     */
    @Test()
    public void testSet() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
        // 移除myuser
        jedis.del("myuser");
        // 添加
        jedis.sadd("myuser", "liuling");
        jedis.sadd("myuser", "xinxin");
        jedis.sadd("myuser", "ling");
        jedis.sadd("myuser", "zhangxinxin");
        jedis.sadd("myuser", "who");
        // 移除myuser中元素
        jedis.srem("myuser", "who");
        System.out.println("获取集合中所有元素的value:" + jedis.smembers("myuser"));
        System.out.println("判断元素是否存在：" + jedis.sismember("myuser", "who"));
        System.out.println("返回集合的一个随机元素:" + jedis.srandmember("myuser"));
        System.out.println("返回集合的元素个数:" + jedis.scard("myuser"));
    }
    /**
     * redis排序
     */
    @Test()
    public void testSort() {
        // 连接redis服务器
        jedis = RedisUtil.getJedis();
        // jedis 排序
        // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.del("a");// 先清除数据，再加入数据进行测试
        jedis.rpush("a", "1");
        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");
        System.out.println("输出结果：" + jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
        System.out.println("输出排序后结果：" + jedis.sort("a")); // [1, 3, 6, 9] //输出排序后结果
        System.out.println("输出结果：" + jedis.lrange("a", 0, -1));
    }
    /**
     * redis连接池
     */
    @Test()
    public void testRedisPool() {

        RedisUtil.getJedis().set("newname", "test");
        System.out.println(RedisUtil.getJedis().get("newname"));


    }

}