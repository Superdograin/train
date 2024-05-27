package cn.qiluno.train.member.controller;

import cn.qiluno.train.common.resp.CommonResp;
import cn.qiluno.train.member.req.MemberLoginReq;
import cn.qiluno.train.member.req.MemberRegisterReq;
import cn.qiluno.train.member.req.MemberSendCodeReq;
import cn.qiluno.train.member.resp.MemberLoginResp;
import cn.qiluno.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long registerId = memberService.register(req);
        return new CommonResp<>(registerId);
    }

    @PostMapping("/send-code")
    public CommonResp<Object> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
