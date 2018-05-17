<style lang="less">
  @import "./main.less";
</style>
<template>
  <div class="main" :class="{'main-hide-text': shrink}">
    <div class="sidebar-menu-con" :style="{width: shrink?'60px':'200px', overflow: shrink ? 'visible' : 'auto'}">
      <scroll-bar ref="scrollBar" :disScrollY="true">
        <shrinkable-menu
          :shrink="shrink"
          @on-change="handleSubmenuChange"
          :theme="menuTheme"
          :before-push="beforePush"
          :open-names="openedSubmenuArr"
          :menu-list="menuList">
          <div slot="top" class="logo-con">
            <img v-show="!shrink" src="../assets/images/logo.png" key="max-logo"/>
            <img v-show="shrink" src="../assets/images/logo-min.png" key="min-logo"/>
          </div>
        </shrinkable-menu>
      </scroll-bar>
    </div>
    <div class="main-header-con" :style="{paddingLeft: shrink?'60px':'200px'}">
      <div class="main-header">
        <div class="navicon-con">
          <Button :style="{transform: 'rotateZ(' + (this.shrink ? '-90' : '0') + 'deg)'}" type="text"
                  @click="toggleClick">
            <Icon type="navicon" size="32"></Icon>
          </Button>
        </div>
        <div class="header-avator-con">
          <div class="switch-theme switch-theme-con">
            <theme-switch></theme-switch>
          </div>
          <div class="user-dropdown-menu-con">
            <Row type="flex" justify="end" align="middle" class="user-dropdown-innercon">
              <Dropdown transfer trigger="click" @on-click="handleClickUserDropdown">
                <a href="javascript:void(0)">
                  <span class="main-user-name">{{user.nickname}}</span>
                  <Icon type="arrow-down-b"></Icon>
                </a>
                <DropdownMenu slot="list">
                  <router-link to="/space">
                    <DropdownItem name="ownSpace">个人中心</DropdownItem>
                  </router-link>
                  <DropdownItem name="loginout1" divided>
                     <span @click="logout">
                        退出登录
                     </span>
                  </DropdownItem>
                </DropdownMenu>
              </Dropdown>
              <Avatar icon="person" style="margin-left:10px;"></Avatar>
            </Row>
          </div>
        </div>
      </div>
      <div class="tags-con">
        <tags-page-opened :pageTagsList="pageTagsList"></tags-page-opened>
      </div>
    </div>
    <div class="single-page-con" :style="{left: shrink?'60px':'200px'}">
      <div class="single-page">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
<script>
  import Cookies from 'js-cookie';
  import shrinkableMenu from './main-components/shrinkable-menu/shrinkable-menu.vue';
  import tagsPageOpened from './main-components/tags-page-opened/tags-page-opened.vue';
  import scrollBar from './self-components/scroll-bar/vue-scroller-bars';
  import themeSwitch from './main-components/theme-switch/theme-switch.vue';
  import util from '@/libs/util.js';

  export default {
    components: {
      tagsPageOpened,
      shrinkableMenu,
      scrollBar,
      themeSwitch
    },
    data() {
      return {
        shrink: false,
        openedSubmenuArr: this.$store.state.app.openedSubmenuArr
      };
    },
    computed: {
      menuList() {
        return this.$store.state.app.menuList;
      },
      user() {
        return this.$store.state.user.user
      },
      pageTagsList() {
        return this.$store.state.app.pageOpenedList; // 打开的页面的页面对象
      },
      menuTheme() {
        console.log(this.$store.state.app.menuTheme)
        return this.$store.state.app.menuTheme;
      },
    },
    methods: {
      init() {
        let pathArr = util.setCurrentPath(this, this.$route.name);
        this.$store.commit('updateMenulist');
        if (pathArr.length >= 2) {
          this.$store.commit('addOpenSubmenu', pathArr[1].name);
        }
        this.userName = Cookies.get('user');
        this.checkTag(this.$route.name);
      },
      toggleClick() {
        this.shrink = !this.shrink;
      },
      checkTag(name) {
        let openpageHasTag = this.pageTagsList.some(item => {
          if (item.name === name) {
            return true;
          }
        });
        if (!openpageHasTag) { //  解决关闭当前标签后再点击回退按钮会退到当前页时没有标签的问题
          util.openNewPage(this, name, this.$route.params || {}, this.$route.query || {});
        }
      },
      handleClickUserDropdown(name) {
        this.$router.push({
          name: 'login'
        });
      },
      handleSubmenuChange(val) {
        // console.log(val)
      },
      beforePush(name) {
        return true;
      },
      scrollBarResize() {
        this.$refs.scrollBar.resize();
      },
      logout() {
        this.$store.commit('logout');
        this.$router.push({
          name:'login'
        })
      }
    },
    watch: {
      '$route'(to) {
        this.$store.commit('setCurrentPageName', to.name);
        let pathArr = util.setCurrentPath(this, to.name);
        if (pathArr.length > 2) {
          this.$store.commit('addOpenSubmenu', pathArr[1].name);
        }
        this.checkTag(to.name);
        localStorage.currentPageName = to.name;
      },
      lang() {
        util.setCurrentPath(this, this.$route.name); // 在切换语言时用于刷新面包屑
      },
      openedSubmenuArr() {
        setTimeout(() => {
          this.scrollBarResize();
        }, 300);
      }
    },
    mounted() {
      this.init();
      window.addEventListener('resize', this.scrollBarResize);
    },
    created() {
      // 显示打开的页面的列表
      this.$store.commit('setOpenedList');
    },
  };
</script>
