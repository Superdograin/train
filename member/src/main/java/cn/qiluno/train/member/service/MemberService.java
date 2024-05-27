package cn.qiluno.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.qiluno.train.common.exception.BusinessException;
import cn.qiluno.train.common.exception.BusinessExceptionEnum;
import cn.qiluno.train.common.util.SnowflakeUtil;
import cn.qiluno.train.member.domain.Member;
import cn.qiluno.train.member.domain.MemberExample;
import cn.qiluno.train.member.mapper.MemberMapper;
import cn.qiluno.train.member.req.MemberLoginReq;
import cn.qiluno.train.member.req.MemberRegisterReq;
import cn.qiluno.train.member.req.MemberSendCodeReq;
import cn.qiluno.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowflakeUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，则插入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowflakeUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号存在，不插入记录");
        }

        // 生成验证码
        String code = RandomUtil.randomString(4);
        LOG.info("生成短信验证码： {}", code);

        // 保存短信记录表： 手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("保存短信验证码");

        // 对接短信通道，发送短信
        LOG.info("对接短信通道");
    }

    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);

        // 判断手机号是否已注册
        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        // 校验短信验证码，验证码、手机号、业务是否相匹配且验证码有效
        if (!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        // 生成 token 并返回
        MemberLoginResp resp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        resp.setToken("");
        return resp;
    }

    private Member selectByMobile(String mobile) {
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
