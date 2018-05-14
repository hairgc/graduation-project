<template>
  <div style="padding: 20px;">
    <Card class="card">
      <Form
        ref="userForm"
        :model="userForm"
        :label-width="100"
        label-position="right"
        :rules="formValidate"
      >
        <FormItem label="用户姓名：" prop="nickname">
          <div style="display:inline-block;width:300px;">
            <Input v-model="userForm.nickname"></Input>
          </div>
        </FormItem>
        <FormItem label="学号工号：" prop="username">
          <div style="display:inline-block;width:300px;">
            <Input v-model="userForm.username"></Input>
          </div>
        </FormItem>
        <FormItem label="用户手机：" prop="phoneNumber">
          <div style="display:inline-block;width:300px;">
            <Input v-model="userForm.phoneNumber"></Input>
          </div>
        </FormItem>
        <FormItem label="登录密码：" prop="password">
          <Input type="password" v-model="userForm.password" placeholder="请输入密码，至少6位字符"
                 style="display:inline-block;width:300px;"></Input>
        </FormItem>
        <FormItem label="确认密码：" prop="rePass">
          <Input type="password" v-model="userForm.rePass" placeholder="请再次输入密码"
                 style="display:inline-block;width:300px;"></Input>
        </FormItem>
        <FormItem label="用户性别：" prop="sexText">
          <RadioGroup v-model="userForm.sexText">
            <Radio label="0">
              <Icon type="male"></Icon>
              男
            </Radio>
            <Radio label="1">
              <Icon type="female"></Icon>
              女
            </Radio>
          </RadioGroup>
        </FormItem>
        <FormItem label="">
          <div>
            <Button type="default" style="width: 100px;" @click="resetForm">重置</Button>
            <Button type="primary" style="width: 100px;" :loading="save_loading" @click="saveEdit">注册</Button>
          </div>
        </FormItem>

      </Form>
    </Card>
  </div>
</template>

<script>
  import Cookies from 'js-cookie';
  export default {
    name: "register",
    data() {
      return {
        save_loading: false,
        userForm: {
          sexText: '0'
        },
        formValidate: {
          nickname: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ],
          username: [
            {required: true, message: '请输入学号/工号', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                this.axios.get('/api/user/isExist/' + value).then((res) => {
                  if (res.data) {
                    callback(new Error('学号或工号已经存在'));
                  } else {
                    callback();
                  }
                }).catch((err) => {
                  callback();
                })
              }, trigger: 'blur'
            }
          ],
          sexText: [
            {required: true, message: '请选择性别', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入新密码', trigger: 'blur'},
            {min: 6, message: '请至少输入6个字符', trigger: 'blur'},
            {max: 32, message: '最多输入32个字符', trigger: 'blur'}
          ],
          phoneNumber: [
            {required: true, message: '请输入手机号码'},
            {
              validator: (rule, value, callback) => {
                var re = /^1[0-9]{10}$/;
                if (!re.test(value)) {
                  callback(new Error('请输入正确格式的手机号'));
                } else {
                  callback();
                }
              }
            }
          ],
          rePass: [
            {required: true, message: '请再次输入密码', trigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                if (value !== this.userForm.password) {
                  callback(new Error('两次输入密码不一致'));
                } else {
                  callback();
                }
              }, trigger: 'blur'
            }
          ]
        },
      }
    },
    methods: {
      saveEdit() {
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            this.save_loading = true;
            this.userForm.sex=this.userForm.sexText=="1";
            this.axios.post('/api/auth/register',
              this.userForm
            ).then((res) => {
              if (res.data.token!=null) {
                Cookies.set('user', res.data.user);
                Cookies.set('token', res.data.token);
                this.$store.commit('login',res.data)
                this.resetForm()
                this.$router.push({
                  name: 'found',
                  params: { page: 1,rows:20 }
                })

              } else {
                this.$Message.error({
                  content: '注册失败,请稍后再试！',
                  duration: 3,
                  closable: true
                });
              }
              this.save_loading = false;
            }).catch((err)=>{
              this.save_loading = false;
            })
          }
        });
      },
      resetForm() {
        this.$refs['userForm'].resetFields();
      }
    }
  }
</script>

<style lang="less" scoped>
  .card {
    width: 500px;
    height: 430px;
    margin: auto;
  }
</style>
