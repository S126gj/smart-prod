package com.smart.prod.core;

import cn.hutool.json.JSONUtil;
import com.smart.prod.common.utils.DateUtil;
import com.smart.prod.es.entity.DataAcquisition;
import com.smart.prod.es.mapper.DataAcquisitionMapper;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryChainWrapper;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/25 08:18
 */
@SpringBootTest
public class EsTest {

    @Autowired
    private DataAcquisitionMapper dataAcquisitionMapper;

    @Test
    void queryEs() {
        long l = System.currentTimeMillis();
        // 支持设置多个查询的索引,用逗号隔开即可
        LambdaEsQueryWrapper<DataAcquisition> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.eq(DataAcquisition::getBoxCode, "YOUNGSUN/FEIHONG/IO/V001/JZBZX_07/DEVICE_STATUS")
            .index("feihong").orderByDesc(DataAcquisition::getCreateTime).between(DataAcquisition::getCreateTime,
                DateUtil.subHour(new Date(), 1), new Date());
        List<DataAcquisition> document = dataAcquisitionMapper.selectList(wrapper);
        System.out.println(JSONUtil.toJsonStr(document));
        System.out.println(String.format("耗时:[%s]", System.currentTimeMillis() - l));
    }

    @Test
    void insert() {
        DataAcquisition dataAcquisition = new DataAcquisition();
        dataAcquisition.setBoxCode("123");
        dataAcquisition.setOriginData("test");
        dataAcquisition.setState("测试状态");
        dataAcquisition.setRemark("11111");
        dataAcquisition.setCreateTime(new Date());
        dataAcquisitionMapper.insert(dataAcquisition, "feihong");
    }

    @Test
    void createIndex() {
        dataAcquisitionMapper.createIndex("huananzhan");
    }

    @Test
    void test() {
        long l = System.currentTimeMillis();
        String boxCode = "YOUNGSUN/FEIHONG/SBOX/V001/YSJ_10/DEVICE_STATUS";
        String date = "2024-01-08";
        List<DataAcquisition> dataAcquisitions = new LambdaEsQueryChainWrapper<>(dataAcquisitionMapper)
            .index("feihong")
            .eq(DataAcquisition::getBoxCode, boxCode)
            .between(DataAcquisition::getCreateTime, DateUtil.getDateStartTime(DateUtil.getDate1(date)),
                DateUtil.getDateEndTime(DateUtil.getDate1(date)))
            .list();
        System.out.println(JSONUtil.toJsonStr(dataAcquisitions));
        System.out.println(dataAcquisitions.size() + "条");
        System.out.println(System.currentTimeMillis() - l + "豪秒");
    }
}