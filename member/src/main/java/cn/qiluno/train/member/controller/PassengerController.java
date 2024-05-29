package cn.qiluno.train.member.controller;

import cn.qiluno.train.common.context.LoginMemberContext;
import cn.qiluno.train.common.resp.CommonResp;
import cn.qiluno.train.common.resp.PageResp;
import cn.qiluno.train.member.req.PassengerQueryReq;
import cn.qiluno.train.member.req.PassengerSaveReq;
import cn.qiluno.train.member.resp.PassengerQueryResp;
import cn.qiluno.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }
}
