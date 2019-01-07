
let CookieUtil = {// 设置cookie<存>
  setCookie: function (cname, cvalue, exdays, path) {
    let d = new Date()
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000))
    let expires = 'expires=' + d.toUTCString()
    document.cookie = cname + '=' + cvalue + '; ' + expires + '; path=' + path
  },
  // 获取cookie
  getCookie: function (cname) {
    let arr
    let reg = new RegExp('(^| )' + cname + '=([^;]*)(;|$)')
    arr = document.cookie.match(reg)
    if (arr !== null) {
      return (arr[2])
    } else {
      return null
    }
  },
  delCookie: function (name) {
    let exp = new Date()
    exp.setTime(exp.getTime() - 1)
    let cval = this.getCookie(name)
    if (cval != null) {
      document.cookie = name + '=' + cval + '; expires=' + exp.toGMTString()
    }
  }
}

module.exports = {
  CookieUtil
}
