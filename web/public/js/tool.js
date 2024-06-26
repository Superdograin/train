Tool = {
    /**
     * 空校验 null 或 "" 都返回 true
     * @param obj
     */
    isEmpty: (obj) => {
        if ((typeof obj === 'string')) {
            return !obj || obj.replace(/\s+/g, "") === ""
        } else {
            return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
        }
    },

    /**
     * 非空校验
     * @param obj
     */
    isNotEmpty: (obj) => {
        return !Tool.isEmpty(obj);
    },

    /**
     * 对象复制
     * @param obj
     */
    copy: (obj) => {
        if (Tool.isNotEmpty(obj)) {
            return JSON.parse(JSON.stringify(obj));
        }
    },
}