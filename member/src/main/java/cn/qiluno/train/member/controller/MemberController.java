package cn.qiluno.train.member.controller;

import cn.qiluno.train.common.resp.CommonResp;
import cn.qiluno.train.member.req.MemberRegisterReq;
import cn.qiluno.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        int count = memberService.count();
        return new CommonResp<>(count);
    }

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq req) {
        long registerId = memberService.register(req);
        return new CommonResp<>(registerId);
    }
}
