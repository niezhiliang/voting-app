//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    inputShowed: false,
    inputVal: "",
    voteList:null

  },
  onLoad: function () {
    var that = this;
    wx.request({
      url: 'https://t.fangxinqian.cn:8088/theme/list',
      method:"GET",
      success(res) {
        if (res.data.code == 1) {
          that.setData({
            voteList: res.data.data
          });
        }
      }
    })

  },
  hello: function (e) {
    wx.navigateTo({
      url: '../vote/detail',
    })
  },
  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    this.setData({
      inputVal: ""
    });
  },
  inputTyping: function (e) {
    this.setData({
      inputVal: e.detail.value
    });
  }
})
