
//日期插件
var dateTimePicker = require('../../utils/dateTimePicker.js');

var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: null,
    descript: null,
    many_selected: false,
    count: 1,
    beginTime: null,
    endTime: null,
    beginAt: null,
    endAt: '2019-07-01',
    array: [0],//默认显示一个
    inputVal: [],//所有input的内容
    dateTimeArray: null,
    startYear: 2019,
    endYear: 2050
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    // 获取完整的年月日 时分秒，以及默认显示的数组
    var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);

    // 精确到分的处理，将数组的秒去掉
    var lastArray = obj.dateTimeArray.pop();
    var lastTime = obj.dateTime.pop();

    this.setData({
      beginAt: obj.dateTime,
      endAt: obj.dateTime,
      dateTimeArray: obj.dateTimeArray
    });  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  },
  //单多项选择开关
  changeSwitch: function (e) {
    var flag = true;
    if (this.data.many_selected) {
        flag = false;
    }
    this.setData({
      many_selected: flag
    })
  },
  //动态添加input
  addInput: function (e) {
    var old = this.data.array;
    old.push(1);//这里不管push什么，只要数组长度增加1就行
    this.setData({
      array: old
    })
  },
  addMembers: function (e) {
    var nowidx = e.currentTarget.dataset.idx;//当前索引
    console.log(nowidx)
    var oldInputVal = this.data.inputVal;//所有的input值
    var value = e.detail.value;

    var obj = {};
    obj.name = value;

    oldInputVal.splice(nowidx, 1, obj)
    this.setData({
      inputVal: oldInputVal
    })
  },
  //动态删除input
  delInput: function (e) {
    var nowidx = e.currentTarget.dataset.idx;//当前索引
    var oldInputVal = this.data.inputVal;//所有的input值
    var oldarr = this.data.array;//循环内容
    oldarr.splice(nowidx, 1);    //删除当前索引的内容，这样就能删除view了
    oldInputVal.splice(nowidx, 1);//view删除了对应的input值也要删掉

    if (oldarr.length < 1) {
      oldarr = [0]  //如果循环内容长度为0即删完了，必须要留一个默认的。这里oldarr只要是数组并且长度为1，里面的值随便是什么
    }
    this.setData({
      array: oldarr,
      inputVal: oldInputVal
    })
  },
  //标题
  titleInput: function (e) {
    this.setData({
      title: e.detail.value
    })
  },
  //补充描述
  descriptInput: function (e) {
    this.setData({
      descript: e.detail.value
    })
  },
  //多选数量
  countInput: function (e) {
      if (this.data.many_selected) {
        this.setData({
          count: e.detail.value
        })
      }
  },
  //提交表单
  addVote: function (e) {

    var type = 1;
    var count = 1;

    //是否多选
    if (this.data.many_selected) {
        type = 2;
        count = this.data.count;
    }

    wx.request({
      url: 'https://t.fangxinqian.cn:8088/theme/add',
      data: {
        "token": "wx13b92d5b8923bcb9",
        "title": this.data.title,
        "descript": this.data.descript,
        "begin": this.data.beginTime,
        "end":this.data.endTime,
        "type": type,
        "count": count,
        "candidates": this.data.inputVal
      },
      method: "POST",
      success(res) {
        if (res.data.code == 1) {
          wx.switchTab({
            url: '/pages/user/user'
          })
        }
      }
    })

    console.log(this.data.inputVal)
  },
   //改变开始时间时触发
  changeDateTimeColumn(e) {
    var arr = this.data.beginAt, dateArr = this.data.dateTimeArray;


    arr[e.detail.column] = e.detail.value;

    console.log(e.detail.column + "---" + e.detail.value);

    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

    this.setData({
      dateTimeArray: dateArr,
      beginAt: arr
    });
  },
  //改变截止时间时触发
  changeEndTimeColumn(e) {
    var arr = this.data.endAt, dateArr = this.data.dateTimeArray;


    arr[e.detail.column] = e.detail.value;


    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

    this.setData({
      dateTimeArray: dateArr,
      endAt: arr
    });
  },
  //开始时间设置
  changeBeginAt(e) {
    var arr = this.data.beginAt, dateArr = this.data.dateTimeArray;

    arr[e.detail.column] = e.detail.value;
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

    var time = dateArr[0][arr[0]] + "-" + dateArr[1][arr[1]] + "-" + dateArr[2][arr[2]]

      + "T" + dateArr[3][arr[3]] + ":" + dateArr[4][arr[4]];

    this.setData({
      dateTimeArray: dateArr,
      beginAt: arr,
      beginTime: time
    });
  },
  //截止时间设置
  changeEndAt(e) {
    var arr = this.data.endAt, dateArr = this.data.dateTimeArray;

    arr[e.detail.column] = e.detail.value;
    dateArr[2] = dateTimePicker.getMonthDay(dateArr[0][arr[0]], dateArr[1][arr[1]]);

    var time = dateArr[0][arr[0]] + "-" + dateArr[1][arr[1]] + "-" + dateArr[2][arr[2]]

      + "T" + dateArr[3][arr[3]] + ":" + dateArr[4][arr[4]];

    this.setData({
      dateTimeArray: dateArr,
      endAt: arr,
      endTime: time
    });
  }
})