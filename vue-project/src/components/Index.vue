<template>
  <div class="layout">
    <Layout>
      <Header :style="{position: 'fixed', width: '100%',zIndex:10,background:'#fff',padding:'0'}">
        <Menu mode="horizontal" theme="light" class="layout-menu">

          <div class="layout-logo">
            <img src="../assets/logo_min.png" class="ivu-avatar ivu-avatar-circle ivu-avatar-large ivu-avatar-image"/>
            矿大失物招领网站
          </div>
          <div class="layout-nav">
            <router-link to="/found/1/20">
            <MenuItem name="found">
              失物招领
            </MenuItem>
            </router-link>
            <router-link to="/lost/1/20">
            <MenuItem name="lost">
              寻物启事
            </MenuItem>
            </router-link>
            <div v-if="token==null">
              <router-link to="/login">
                <MenuItem name="login">
                  <Icon type="log-in"></Icon>
                  登录
                </MenuItem>
              </router-link>
              <router-link to="/register">
                <MenuItem name="register">
                  <Icon type="log-out"></Icon>
                  注册
                </MenuItem>
              </router-link>
            </div>
            <div v-else>
              <router-link to="/message">
                <MenuItem name="message">
                  <Tooltip :content="messageCount > 0 ? '有' + messageCount + '条未读消息' : '无未读消息'" placement="bottom">
                    <Badge :count="messageCount" dot>
                      <Icon type="ios-bell" :size="22"></Icon>
                    </Badge>
                  </Tooltip>
                </MenuItem>
              </router-link>
              <MenuItem name="dropdown">
                <Dropdown trigger="click">
                  <a href="javascript:void(0)" :style="{color:'#f56a00'}">
                    {{user.nickname}}
                    <Icon type="arrow-down-b"></Icon>
                  </a>
                  <Avatar :src="require('@/assets/user.jpg')" size="small"/>
                  <DropdownMenu slot="list">
                    <router-link to="/space">
                      <DropdownItem><Icon type="ios-person-outline"></Icon>个人中心</DropdownItem>
                    </router-link>

                    <div @click="logout">
                      <DropdownItem><Icon type="log-out"></Icon>退出登录</DropdownItem>
                    </div>
                  </DropdownMenu>
                </Dropdown>
              </MenuItem>
            </div>
          </div>
        </Menu>
      </Header>
      <Content :style="{margin: '70px 20px 0',padding: '0 50px'}">
        <Layout>
          <Sider hide-trigger :style="{background: '#fff',position: 'fixed',width:'100%'}">
                <letter-list></letter-list>
          </Sider>
          <Content :style="{marginLeft: '210px', minHeight: '450px', background: '#fff'}">
            <Card>
              <div style="min-height: 450px;">
                <router-view></router-view>
              </div>
            </Card>
          </Content>
        </Layout>
      </Content>
      <Footer class="layout-footer-center">2018 &copy; CUMT-SWZL</Footer>
    </Layout>
  </div>
</template>

<script>
  import { mapState } from 'vuex'
  import Cookies from 'js-cookie';
  import letterList from './thanks-letter/letter-list';
  export default {
    name: 'Index',
    components: {
      letterList
    },
    data () {
      return {

      }
    },
    computed: mapState({
      // 箭头函数可使代码更简练
      token:'token',

      // 传字符串参数 'count' 等同于 `state => state.count`
      user: 'user',

      messageCount:'messageCount'

    }),
    methods:{
      logout(){
        Cookies.remove('user')
        Cookies.remove('token')
        this.$store.commit('logout');
        this.$Notice.success({
          title: '注销成功！'
        });
        this.$router.push({
          name:'Index'
        })
      },
      getMessageCount(){
        this.axios.get('/api/user/found/countmessage/'+this.user.id).then((res) => {
            this.$store.commit('setMessageCount', res.data);
        }).catch(function (err) {
          console.log(err)
        })
      }
    },
    created: function () {
      if(this.user!=null){
        this.getMessageCount()
      }

    },
    watch: {
      '$route' (to, from) {
        if(this.user!=null){
          this.getMessageCount()
        }
      }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
  .layout-logo{
    font-size: 18px;
    width: 260px;
    height: 40px;
    border-radius: 3px;
    float: left;
    left: 25px;
    text-align: center;
    color:#002956;
    font-family: '隶书';
  }
  .layout-menu{
    display: flex;
    justify-content: space-between;
    padding:0 50px;
  }
  .layout-nav{
    text-align: right;
    width:400px;
  }
  .layout-footer-center{
    text-align: center;
  }
</style>
