<style scoped>
  .login{
    width: 100%;
    min-height: 500px;
    background-image: url('https://file.iviewui.com/iview-admin/login_bg.jpg');
    background-size: cover;
  }
  .login-con{
    position: fixed;
    top: 50px;
    right: 160px;
    width: 300px;
  }
  /*&-header{*/
    /*font-size: 16px;*/
    /*font-weight: 300;*/
    /*text-align: center;*/
    /*padding: 30px 0;*/
  /*}*/
  .form-con{
    padding: 10px 0 0;
  }
</style>
<template>
    <div class="login" @keydown.enter="handleSubmit" >
      <div class="login-con">
        <Card :bordered="false">
          <p slot="title">
            <Icon type="log-in"></Icon>
            欢迎登录
          </p>
          <div class="form-con">
            <Form ref="form" :model="form" :rules="rules">
              <FormItem prop="username">
                <Input v-model="form.username" placeholder="请输入用户名"/>
                <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                </Input>
              </FormItem>
              <FormItem prop="password">
                <Input type="password" v-model="form.password" placeholder="请输入密码"/>
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
    name:'login',
    data () {
      return {
        form: {
          username: '',
          password: '',
          type:1
        },
        rules: {
          username: [
            { required: true, message: '账号不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      handleSubmit () {
        var that=this;
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.axios.get('/api/auth/login',
              {
                params: this.form}).then((res) => {
              Cookies.set('user', res.data.user);
              Cookies.set('token', res.data.token);
              this.$store.commit('login',res.data)
              this.$router.push({
                name: 'found',
                params: { page: 1,rows:20 }
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
