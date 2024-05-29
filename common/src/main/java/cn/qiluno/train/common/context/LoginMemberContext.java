package cn.qiluno.train.common.context;

import cn.qiluno.train.common.resp.MemberLoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginMemberContext {
    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberContext.class);

    private static final ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    public static MemberLoginResp getMember() { return member.get(); }

    public static void setMember(MemberLoginResp member) { LoginMemberContext.member.set(member); }

    public static Long getId() {
        try {
            return member.get().getId();
        } catch (Exception e) {
            LOG.error("获取会员登录信息异常", e);
            throw e;
        }
    }
}
