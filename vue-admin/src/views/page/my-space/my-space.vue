<style lang="less">
  @import './my-space.less';
</style>

<template>
  <div>
    <Card>
      <p slot="title">
        <Icon type="person"></Icon>
        个人信息
      </p>
      <div>
        <Form
          ref="userForm"
          :model="userForm"
          :label-width="100"
          label-position="right"
          :rules="inforValidate"
        >
          <FormItem label="用户姓名：" prop="nickname">
            <div style="display:inline-block;width:300px;">
              <Input v-model="userForm.nickname" ></Input>
            </div>
          </FormItem>
          <FormItem label="学号工号：" prop="username">
            <div style="display:inline-block;width:300px;">
              <Input disabled  v-model="userForm.username" ></Input>
            </div>
          </FormItem>
          <FormItem label="用户手机：" prop="phoneNumber" >
            <div style="display:inline-block;width:300px;">
              <Input v-model="userForm.phoneNumber"></Input>
            </div>
          </FormItem>
          <FormItem label="用户性别：" prop="sexText">
              <RadioGroup v-model="userForm.sexText">
                <Radio label="0">
                  <Icon type="male"></Icon>
                  男
                </Radio>
                <Radio label="1">
                  <Icon type="female"></Icon>
                  女</Radio>
              </RadioGroup>
          </FormItem>
          <FormItem label="登录密码：">
            <Button type="text" size="small" @click="showEditPassword">修改密码</Button>
          </FormItem>
          <FormItem label=" ">
            <div>
              <Button type="primary" style="width: 100px;" :loading="save_loading" @click="saveEdit">保存</Button>
            </div>
          </FormItem>

        </Form>
      </div>
    </Card>
    <Modal v-model="editPasswordModal" :closable='false' :mask-closable=false :width="500">
      <h3 slot="header" style="color:#2D8CF0">修改密码</h3>
      <Form ref="editPasswordForm" :model="editPasswordForm" :label-width="100" label-position="right" :rules="passwordValidate">
        <FormItem label="原密码" prop="oldPass">
          <Input type="password" v-model="editPasswordForm.oldPass" placeholder="请输入现在使用的密码" ></Input>
        </FormItem>
        <FormItem label="新密码" prop="newPass">
          <Input type="password" v-model="editPasswordForm.newPass" placeholder="请输入新密码，至少6位字符" ></Input>
        </FormItem>
        <FormItem label="确认新密码" prop="rePass">
          <Input type="password" v-model="editPasswordForm.rePass" placeholder="请再次输入新密码" ></Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancelEditPass">取消</Button>
        <Button type="primary" :loading="savePassLoading" @click="saveEditPass">保存</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import { mapState } from 'vuex'
  export default {
    name: 'my-space',
    data () {
      const validePhone = (rule, value, callback) => {
        var re = /^1[0-9]{10}$/;
        if (!re.test(value)) {
          callback(new Error('请输入正确格式的手机号'));
        } else {
          callback();
        }
      };
      const valideRePassword = (rule, value, callback) => {
        if (value !== this.editPasswordForm.newPass) {
          callback(new Error('两次输入密码不一致'));
        } else {
          callback();
        }
      };
      return {
        userForm: {
          sexText:''
        },
        save_loading: false,
        editPasswordModal: false, // 修改密码模态框显示
        savePassLoading: false,
        inforValidate: {
          nickname: [
            { required: true, message: '请输入姓名', trigger: 'blur' }
          ],
          username: [
            { required: true, message: '请输入学号/工号', trigger: 'blur' }
          ],
          sexText: [
            { required: true, message: '请选择性别', trigger: 'blur' }
          ],
          phoneNumber: [
            { required: true, message: '请输入手机号码' },
            { validator: validePhone }
          ]
        },
        editPasswordForm: {
          oldPass: '',
          newPass: '',
          rePass: '',
        },
        passwordValidate: {
          oldPass: [
            { required: true, message: '请输入原密码', trigger: 'blur' }
          ],
          newPass: [
            { required: true, message: '请输入新密码', trigger: 'blur' },
            { min: 6, message: '请至少输入6个字符', trigger: 'blur' },
            { max: 32, message: '最多输入32个字符', trigger: 'blur' }
          ],
          rePass: [
            { required: true, message: '请再次输入新密码', trigger: 'blur' },
            { validator: valideRePassword, trigger: 'blur' }
          ]
        }
      };
    },
    computed: {
      ...mapState({
      // 传字符串参数 'count' 等同于 `state => state.count`
        user: 'user'
      })
    },
    methods: {
      showEditPassword () {
        this.editPasswordModal = true;
      },
      saveEdit () {
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            this.$Modal.confirm({
              title: '确认提示',
              content: '<p>确定提交吗？</p>',
              onOk: () => {
                this.save_loading = true;
                this.userForm.sex=this.userForm.sexText=='1'
                this.axios.put('/api/admin',
                  this.userForm
                ).then((res) => {
                  if (res.data == 1) {
                    this.$Message.success('保存成功');
                  } else {
                    this.$Message.error({
                      content: '保存失败！',
                      duration: 3,
                      closable: true
                    });
                  }
                  this.save_loading = false;
                }).catch(function (err) {
                  this.save_loading = false;
                })
              }
            });
          }
        });
      },
      cancelEditPass () {
        this.editPasswordModal = false;
        this.$refs['editPasswordForm'].resetFields();
      },
      saveEditPass () {
        this.$refs['editPasswordForm'].validate((valid) => {
          if (valid) {
            this.$Modal.confirm({
              title: '确认提示',
              content: '<p>确定提交吗？</p>',
              onOk: () => {
                this.savePassLoading = true;
                this.axios.put('/api/admin/changePwd',
                  this.editPasswordForm
                ).then((res) => {
                  if (res.data.status == "success") {
                    this.$Message.success('保存成功');
                    this.editPasswordModal=false;
                    this.$refs['editPasswordForm'].resetFields();
                  } else {
                    this.$Message.error({
                      content: '保存失败,原密码错误！',
                      duration: 3,
                      closable: true
                    });
                  }
                  this.savePassLoading = false;
                }).catch(function (err) {
                  this.savePassLoading = false;
                })
              }
            });
          }
        });
      }
    },
    created () {
      let sex={
        sexText:this.user.sex?"1":"0"
      }
      Object.assign(this.userForm, this.user.user,sex);
      this.editPasswordForm.id=this.user.user.id
    }
  };
</script>

