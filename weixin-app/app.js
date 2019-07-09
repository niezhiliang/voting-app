//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        wx.request({
          url: 'https://t.fangxinqian.cn:8088/user/openId',
          data: {
            "appid": "wx13b92d5b8923bcb9",
            "secret": "08ae21513a6e3c3030950732748f7384",
            "js_code": res.code,
            "grant_type": "authorization_code",
            "nickName": this.globalData.userInfo.nickName,
            "sex": this.globalData.userInfo.gender,
            "avatarUrl": this.globalData.userInfo.avatarUrl
          },
          method: "POST",
          success(res) {
            console.log(res.data)
            wx.setStorageSync('openId', res.data.openid)
            wx.setStorageSync('sessionKey', res.data.session_key)
          }
        })
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    openId: null
  }
})