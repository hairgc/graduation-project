import Vue from 'vue';
import VueI18n from 'vue-i18n';
import Locales from './locale';
import zhLocale from 'iview/src/locale/lang/zh-CN';
import enLocale from 'iview/src/locale/lang/en-US';

// 自动设置语言
const navLang = navigator.language;
const localLang = (navLang === 'zh-CN' || navLang === 'en-US') ? navLang : false;
const lang = window.localStorage.lang || localLang || 'zh-CN';

Vue.use(VueI18n);
Vue.config.lang = lang;

// 多语言配置
const locales = Locales;
const mergeZH = Object.assign(zhLocale, locales['zh-CN']);
const mergeEN = Object.assign(enLocale, locales['en-US']);
const messages={
  'zh-CN':mergeZH,
  'en-US':mergeEN
}
// Vue.locale('zh-CN', mergeZH);
// Vue.locale('en-US', mergeEN);
const i18n = new VueI18n({
  locale: lang, // set locale
  messages, // set locale messages
})
export default i18n
