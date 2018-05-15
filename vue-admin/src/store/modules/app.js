import {otherRouter, appRouter} from '../../router/router';

const app = {
  state: {
    cachePage: [],
    menuList: [...appRouter],
    routers: [
      otherRouter,
      ...appRouter
    ],
    currentPageName: '',
    openedSubmenuArr: [], // 要展开的菜单数组
    menuTheme: 'light', // 主题
    themeColor: '',
    pageOpenedList: [{
      title: '首页',
      path: '',
      name: 'home_index'
    }],
    currentPath: [
      {
        title: '首页',
        path: '',
        name: 'home_index'
      }
    ],
    // 面包屑数组
    tagsList: [...otherRouter.children],
  },
  mutations: {
    setTagsList (state, list) {
      state.tagsList.push(...list);
    },
    closePage (state, name) {
      state.cachePage.forEach((item, index) => {
        if (item === name) {
          state.cachePage.splice(index, 1);
        }
      });
    },
    removeTag (state, name) {
      state.pageOpenedList.map((item, index) => {
        if (item.name === name) {
          state.pageOpenedList.splice(index, 1);
        }
      });
    },
    updateMenulist (state) {
      state.menuList = appRouter;
    },
    setOpenedList (state) {
      state.pageOpenedList = localStorage.pageOpenedList ? JSON.parse(localStorage.pageOpenedList) : [otherRouter.children[0]];
    },
    setCurrentPageName (state, name) {
      state.currentPageName = name;
    },
    setCurrentPath (state, pathArr) {
      state.currentPath = pathArr;
    },
    addOpenSubmenu (state, name) {
      let hasThisName = false;
      let isEmpty = false;
      if (name.length === 0) {
        isEmpty = true;
      }
      if (state.openedSubmenuArr.indexOf(name) > -1) {
        hasThisName = true;
      }
      if (!hasThisName && !isEmpty) {
        state.openedSubmenuArr.push(name);
      }
    },
    increateTag (state, tagObj) {
      state.cachePage.push(tagObj.name);
      localStorage.cachePage = JSON.stringify(state.cachePage);
      state.pageOpenedList.push(tagObj);
      localStorage.pageOpenedList = JSON.stringify(state.pageOpenedList);
    },
    clearAllTags (state) {
      state.pageOpenedList.splice(1);
      state.cachePage.length = 0;
      localStorage.pageOpenedList = JSON.stringify(state.pageOpenedList);
    },
    clearOtherTags (state, vm) {
      let currentName = vm.$route.name;
      let currentIndex = 0;
      state.pageOpenedList.forEach((item, index) => {
        if (item.name === currentName) {
          currentIndex = index;
        }
      });
      if (currentIndex === 0) {
        state.pageOpenedList.splice(1);
      } else {
        state.pageOpenedList.splice(currentIndex + 1);
        state.pageOpenedList.splice(1, currentIndex - 1);
      }
      let newCachepage = state.cachePage.filter(item => {
        return item === currentName;
      });
      state.cachePage = newCachepage;
      localStorage.pageOpenedList = JSON.stringify(state.pageOpenedList);
    },
    pageOpenedList (state, get) {
      let openedPage = state.pageOpenedList[get.index];
      if (get.argu) {
        openedPage.argu = get.argu;
      }
      if (get.query) {
        openedPage.query = get.query;
      }
      state.pageOpenedList.splice(get.index, 1, openedPage);
      localStorage.pageOpenedList = JSON.stringify(state.pageOpenedList);
    },
    changeMenuTheme (state, theme) {
      state.menuTheme = theme;
    },
    changeMainTheme (state, mainTheme) {
      state.themeColor = mainTheme;
    },
  }
};

export default app;
