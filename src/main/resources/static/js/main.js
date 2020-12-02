
/**
 * 获取指定的cookie的值
 * @param cname
 * @returns {string}
 */
function getCookie(cname){
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) === 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}

/**
 * 判断当前环境是不是中文
 * @returns {boolean}
 */
function isChinese(){
    var language = getCookie("customLanguage");
    return language === "zh";
}

/**
 * 将秒转换为 00:00格式
 * @param duration
 * @returns {string}
 */
function toClock(duration) {
    var timeStr = "00:00";
    if(!duration){
        return timeStr;
    }
    if(duration < 10){
        timeStr = "00:0" + duration;
    }else if(duration < 60){
        timeStr = "00:" + duration;
    }else{
        var minute = Math.floor(duration / 60);
        if(minute < 10){
            timeStr = "0" + minute + ":";
        }else{
            timeStr = minute + ":";
        }
        var secnd = Math.ceil(duration % 60);
        if(secnd < 10){
            timeStr += "0" + secnd;
        }else{
            timeStr += secnd;
        }
    }
    return timeStr;
}

/** 格式 YYYY/yyyy/YY/yy 表示年份  
 * MM/M 月份  
 * W/w 星期  
 * dd/DD/d/D 日期  
 * hh/HH/h/H 时间  
 * mm/m 分钟  
 * ss/SS/s/S 秒  
 **/
//---------------------------------------------------  
Date.prototype.Format = function(formatStr)  {
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];

    str=str.replace(/yyyy|YYYY/,this.getFullYear());
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

    str=str.replace(/MM/,(this.getMonth() + 1)>9?(this.getMonth() + 1).toString():'0' + (this.getMonth() + 1));
    str=str.replace(/M/g,this.getMonth());


    str=str.replace(/w|W/g,Week[this.getDay()]);

    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
    str=str.replace(/d|D/g,this.getDate());

    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
    str=str.replace(/h|H/g,this.getHours());
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
    str=str.replace(/m/g,this.getMinutes());

    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
    str=str.replace(/s|S/g,this.getSeconds());
    return str;
};

//封装ajax
function sendRequest(config, callbackS, callbackF) {
    // 设置常用的默认值
    var url = config.url || '/';
    var method = config.method || 'GET';
    var async = config.async === undefined ? true : config.async;
    var value = config.contentType || 'application/x-www-form-urlencoded; charset=UTF-8';
    var header = config.header || {};
    // 创建XMLHttpRequest对象
    var xhr = new XMLHttpRequest();
    // 初始化请求
    xhr.open(method, url, async);
    // 设置header的默认值
    xhr.setRequestHeader('Content-Type', value);
    // 设置其它header
    Object.keys(header).forEach(function(key){
        xhr.setRequestHeader(key, header[key]);
    });
    //设置token
    xhr.setRequestHeader("Token", localStorage.getItem("token"));
    var data = config.data;
    var params = "";
    Object.keys(data).forEach(function(key){
        if(params.length > 0){
            params += "&";
        }
        params += key + "=" + data[key];
    });
    // 发送请求
    xhr.send(params);
    // 处理响应
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var resp = JSON.parse(xhr.responseText);
                if(resp.code === -2){
                    window.top.location.replace("/manage/login.html");
                }else{
                    callbackS && callbackS(resp);
                }
            }else {
                callbackF && callbackF(xhr.status);
            }
        }
    }
}

//封装ajax
function uploadRequest(config, callbackS, callbackF) {
    // 创建XMLHttpRequest对象
    var xhr = new XMLHttpRequest();
    // 初始化请求
    xhr.open('POST', config.url, true);
    // 设置header的默认值
    //xhr.setRequestHeader('Content-Type', 'multipart/form-data');
    //设置token
    xhr.setRequestHeader("Token", localStorage.getItem("token"));
    // 发送请求
    var fd = new FormData();
    fd.append("file", config.file);
    fd.append("uploadMode", config.uploadMode);
    fd.append("order", config.order ? config.order : 1);
    xhr.send(fd);
    // 处理响应
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var resp = JSON.parse(xhr.responseText);
                if(resp.code === -2){
                    window.top.location.replace("/manage/login.html");
                }else{
                    callbackS && callbackS(resp);
                }
            }else {
                callbackF && callbackF(xhr.status);
            }
        }
    }
}