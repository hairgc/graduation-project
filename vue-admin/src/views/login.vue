<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login" @keydown.enter="handleSubmit">
    <div class="login-con">
      <Card :bordered="false">
        <p slot="title">
          <Icon type="log-in"></Icon>
          欢迎登录失物招领后台
        </p>
        <div class="form-con">
          <Form ref="loginForm" :model="form" :rules="rules">
            <FormItem prop="username">
              <Input v-model="form.username" placeholder="请输入用户名">
              <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
              </Input>
            </FormItem>
            <FormItem prop="password">
              <Input type="password" v-model="form.password" placeholder="请输入密码">
              <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
              </Input>
            </FormItem>
            <FormItem>
              <Button @click="handleSubmit" type="primary" long>登录</Button>
            </FormItem>
          </Form>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
  import Cookies from 'js-cookie';

  export default {
    data() {
      return {
        form: {
          username: '',
          password: '',
          type: 2
        },
        rules: {
          username: [
            {required: true, message: '账号不能为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      handleSubmit() {
        var that=this;
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.axios.get('/api/auth/login',
              {
                params: this.form}).then((res) => {
              Cookies.set('admin', res.data.user);
              Cookies.set('admin_token', res.data.token);
              this.$store.commit('login',res.data)
              this.$router.push({
                name: 'home_index'
              })
            }).catch(function(err){
              if(err.response.status==602||err.response.status==601){
                that.$Message.error('用户名或密码错误');
              }else{
                that.$Message.error('登录失败');
              }
            })
          }
        });
      }
    }
  };
</script>

<style>

</style>
