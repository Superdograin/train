// 定义 session key

SessionStorage = {
    get: function (key) {
        const val = sessionStorage.getItem(key)
        if (val && typeof(val) !== "undefined" && val !== "undefined") {
            return JSON.parse(val)
        }
    },
    set: function (key, data) {
        sessionStorage.setItem(key, JSON.stringify(data))
    },
    remove: function (key) {
        sessionStorage.removeItem(key)
    },
    removeAll: function () {
        sessionStorage.clear()
    }
}