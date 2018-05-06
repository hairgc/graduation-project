<template>
  <div class="layout">
    <Layout>
      <Header :style="{position: 'fixed', width: '100%',zIndex:10}">
        <Menu mode="horizontal" theme="dark" active-name="1">
          <div class="layout-logo"></div>
          <div class="layout-nav">
            <router-link to="/found/1/20">
            <MenuItem name="1">
              失物招领
            </MenuItem>
            </router-link>
            <router-link to="/lost/1/20">
            <MenuItem name="2">
              寻物启事
            </MenuItem>
            </router-link>
            <MenuItem name="3">
              <Icon type="log-in"></Icon>
              登录
            </MenuItem>
            <MenuItem name="4">
              <Icon type="log-out"></Icon>
              注册
            </MenuItem>
          </div>
        </Menu>
      </Header>
      <Content :style="{margin: '88px 20px 0',padding: '0 50px'}">
        <Layout>
          <Sider hide-trigger :style="{background: '#fff',position: 'fixed',width:'100%'}">
            <Card>
              <div style="min-height: 450px;">
                thanks
              </div>
            </Card>
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
export default {
  name: 'Index',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods:{
    getHttp(){
      this.axios.get('/api/user/all/1/0').then((res) => {
        console.log(res.data)
      }).catch(function(err){
        console.log(err)
      })
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
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }
  .layout-nav{
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
  }
  .layout-footer-center{
    text-align: center;
  }
</style>
