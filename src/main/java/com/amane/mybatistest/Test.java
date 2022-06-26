package com.amane.mybatistest;


import com.amane.mybatistest.mapper.UserMapper;
import com.amane.mybatistest.pojo.User;
import com.amane.mybatistest.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

@SpringBootTest
@Slf4j
public class Test {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @org.junit.jupiter.api.Test
    public void test(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    public void test02(){
        List<User> list = new ArrayList();
        List<Map<String,Object>> list2 = new ArrayList();
        List<Object> list3 = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        list = userService.list(new QueryWrapper<User>().eq("id",1));
        list.forEach(System.out::println);
        list = userService.listByIds(ids);
        list.forEach(System.out::println);
        list2 = userService.listMaps(new QueryWrapper<User>().eq("age",22));
        list2.forEach(System.out::println);
        list3 = userService.listObjs();
        list3.forEach(System.out::println);
        log.info("======================================");
        list3 = userService.listObjs(null,a->{
            return Integer.valueOf(a.toString());
        });
        list3.forEach(System.out::println);
        IPage<User> page = new Page<>(1,2);
        IPage<User> page2 = new LambdaQueryChainWrapper<>(userService.getBaseMapper()).page(page);
        List<User> records = page2.getRecords();
        LambdaQueryWrapper<User> lambdaQueryWrapper1 = new QueryWrapper<User>().orderByDesc("id").lambda();
        lambdaQueryWrapper1.eq(User::getAddress,"newadd");
        List<User> list1 = userService.list(lambdaQueryWrapper1);
        for(User user : list1){
            log.info(user.toString()+"=======================================");
        }
        records.forEach(System.out::println);
        log.info(String.valueOf(userService.count(new QueryWrapper<User>().eq("address","newadd"))));
        Map<String,Object> maps = new HashMap<>();
        maps.put("address","newadd");
        Integer res = userMapper.deleteByMap(maps);
        System.out.println("删除记录条数："+res);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        updateWrapper.set("age",222);
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id",2));
        System.out.println(user+"hhh");
        user.setAge(234);
        Integer res2 = userMapper.updateById(user);
        System.out.println("更新数量："+res2);
        User user1 = new User();
        user1.setId(1);
        user1.setAge(11);
        user1.setName("111");
        user1.setAddress("1111");
        userService.update(user1,new QueryWrapper<User>().eq("id",2));
        List<Integer> ages = SimpleQuery.list(Wrappers.lambdaQuery(),User::getAge);
        ages.forEach(System.out::println);
        Map<Integer,String> mapss = SimpleQuery.map(Wrappers.lambdaQuery(),User::getId,User::getName);
        System.out.println(mapss);
        /*Map<Integer,String> testMap = new HashMap<>();
        testMap.put(1,"a");
        testMap.put(2,"b");
        testMap.put(3,"c");
        Iterator<Map.Entry<Integer, String>> iterators = testMap.entrySet().iterator();
        while(iterators.hasNext()){
            Map.Entry<Integer,String> entry = iterators.next();
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ":" + value);
        }

        for(Map.Entry<Integer,String> entry : testMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }*/
        Map<String,List<User>> mapsss = SimpleQuery.group(Wrappers.lambdaQuery(),User::getAddress);
        Iterator<Map.Entry<String,List<User>>> iterator02 = mapsss.entrySet().iterator();
        while (iterator02.hasNext()){
            Map.Entry<String,List<User>> entry = iterator02.next();
            String address = entry.getKey();
            System.out.println("address:" + address + entry.getValue());
        }
    }
    @org.junit.jupiter.api.Test
    public void test03(){
        Integer[] arr = {1,4,2,5,7,6,9};
        Integer max = getLength(arr);
        System.out.println(max);
    }
    private static Integer getLength(Integer[] arr){
        Integer[] dp = new Integer[arr.length];
        Integer maxLength = 1;
        for(int i = 0; i < arr.length; i++){
            dp[i] = 1;
            for (int j = 0; j<i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxLength = Math.max(dp[i],maxLength);
        }
        return maxLength;
    }

}
