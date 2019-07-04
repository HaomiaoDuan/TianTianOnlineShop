package com.tiantian.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.mapper.TbItemMapper;
import com.tiantian.pojo.TbItem;
import com.tiantian.pojo.TbItemExample;

public class TestPageHelper {

    @Test
    public void testPageHelper(){
        //1.获得Spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-*.xml");
        //2.获得mapper
        TbItemMapper itemMapper = ac.getBean(TbItemMapper.class);
        //3.分页
        PageHelper.startPage(1, 20);
        //4.执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //5.从PageInfo获取分页后的结果
        PageInfo<TbItem> info = new PageInfo<>(list);
        long total = info.getTotal();
        int pageSize = info.getPageSize();
        int pages = info.getPages();
        System.out.println("total="+total+"\n pageSize="+pageSize+"\n pages="+pages);
        System.out.println(list.size());
    }
}
