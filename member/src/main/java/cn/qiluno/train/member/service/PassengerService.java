package cn.qiluno.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.qiluno.train.common.util.SnowflakeUtil;
import cn.qiluno.train.member.domain.Passenger;
import cn.qiluno.train.member.mapper.MemberMapper;
import cn.qiluno.train.member.mapper.PassengerMapper;
import cn.qiluno.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;

    @Resource
    private MemberMapper memberMapper;

    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowflakeUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
