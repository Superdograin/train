package cn.qiluno.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.qiluno.train.common.exception.BusinessException;
import cn.qiluno.train.common.exception.BusinessExceptionEnum;
import cn.qiluno.train.common.util.SnowflakeUtil;
import cn.qiluno.train.member.domain.Member;
import cn.qiluno.train.member.domain.MemberExample;
import cn.qiluno.train.member.mapper.MemberMapper;
import cn.qiluno.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample example = new MemberExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(example);

        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowflakeUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
