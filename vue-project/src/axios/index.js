/**
 *  axios配置,输出为vue组建
 * */
import axios from 'axios';
import qs from 'qs'
import router from '../router'
import Cookies from 'js-cookie';
// const Axios = axios.create({
//   baseURL: "/", // 因为我本地做了反向代理
//   timeout: 10000,
//   responseType: "json",
//   withCredentials: true, // 是否允许带cookie这些
//   headers: {
//     "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
//   }
// });

// http request 拦截器
axios.interceptors.request.use(
  config => {
    if(config.headers!=null&&config.headers['Content-Type']!=null){

    }else{
      config.headers = {
        //'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        'Content-Type': 'application/json;charset=UTF-8'
      };
    }
    // 在发送请求之前做某件事
    if (
      config.method === "post" ||
      config.method === "put" ||
      config.method === "delete"
    ) {
      // 序列化
      //config.data = qs.stringify(config.data);
    }
    // 若是有做鉴权token , 就给头部带上token
    if (Cookies.get('token')) {
      config.headers.Authorization = Cookies.get('token');
    }
    console.log('bef:request:success')
    return config;
  },
  err => {
    console.log('bef:request:error')
    return Promise.reject(err);
  });

// http response 拦截器
axios.interceptors.response.use(
  response => {
    console.log('after:request:success')
    return response;
  },
  error => {
    if (error.response) {
      console.log("请求错误");
      console.log(error.response.status);
      switch (error.response.status) {
        case 401:
          // store.dispatch('logout');
          console.log("401");
          break;
        case 403:
          router.push('/403');
          break;
        case 404:
          router.push('/404');
          break;
        case 500:
          router.push('/500');
      }

    }
    return Promise.reject(error);// 返回接口返回的错误信息
  });

export default axios
// {
//   install: function(Vue, Option) {
//     Object.defineProperty(Vue.prototype, "$http", { value: Axios });
//   }
// };
