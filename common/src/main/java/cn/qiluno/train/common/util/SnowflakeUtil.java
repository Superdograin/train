package cn.qiluno.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * 封装雪花算法
 */
public class SnowflakeUtil {
    private static final long dataCenterId = 1; // 数据中心
    private static final long workerId = 1;     // 机器标识

    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextId();
    }

    public static String getSnowflakeNextIdStr() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextIdStr();
    }
}
