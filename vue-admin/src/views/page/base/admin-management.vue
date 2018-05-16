<template>
  <div>
    <Row>
      <Input v-model="keyword" placeholder="请输入姓名、学号或手机号搜索..." style="width: 200px"/>
      <span style="margin: 0 10px;"><Button @click="handleSearch" type="primary" icon="search"
                                            :disabled="keyword.trim().length==0">搜索</Button></span>
      <Button @click="handleCancel" type="ghost">取消</Button>
      <span style="margin: 0 10px;"><Button @click="handleAdd" type="primary" icon="plus-round">新增</Button></span>
    </Row>
    <div class="margin-top-8">
      <Table highlight-row :loading="loading" :data="tableData" :columns="tableColumns" stripe height="450"></Table>
      <div style="margin: 10px;overflow: hidden">
        <div style="float: right;">
          <Page :total="total" :page-size-opts="pageOpts" :current="page" :page-size="pageSize"
                @on-change="changePage" @on-page-size-change="pageSizeChange" show-sizer show-total
                show-elevator></Page>
        </div>
      </div>
    </div>
    <Modal v-model="addAdminModal" :closable='false' :mask-closable='false' :width="500">
      <h3 slot="header" style="color:#2D8CF0">新增管理员</h3>
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
      </Form>
      <div slot="footer">
        <Button type="text" style="width: 100px;" @click="cancleSave">取消</Button>
        <Button type="primary" style="width: 100px;" :loading="save_loading" @click="saveAdmin">提交</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    name: "admin-management",
    data() {
      return {
        tableData: [],
        loading: false,
        save_loading: false,
        addAdminModal:false,
        page: 1,
        pageSize: 10,
        total: 0,
        pageOpts: [10, 50, 90],
        keyword: '',
        tableColumns: [
          {
            title: '学号(工号)',
            key: 'username',
            align: 'center'
          }, {
            title: '姓名',
            key: 'nickname',
            align: 'center'
          }, {
            title: '联系方式',
            key: 'phoneNumber',
            align: 'center'
          }, {
            title: '性别',
            key: 'sex',
            align: 'center',
            render: (h, params) => {
              const row = params.row;
              const icon = row.sex === false ? 'male' : 'female';
              const text = row.sex === false ? ' 男' : ' 女';
              return h('span', [
                h('Icon', {
                  props: {
                    type: icon
                  }
                }),
                h('span', text),
              ]);
            }
          },{
            title: '状态',
            key: 'deleted',
            align: 'center',
            render: (h, params) => {
              const row = params.row;
              const color = row.deleted === false ? 'green' : 'red';
              const text = row.deleted === false ? '正常' : '禁用';
              return h('Tag', {
                props: {
                  type: 'dot',
                  color: color
                }
              }, text);
            }
          }, {
            title: '操作',
            key: 'action',
            width: 100,
            align: 'center',
            render: (h, params) => {
              const row = params.row;
              const type = row.deleted === false ? 'error' : 'success';
              const text = row.deleted === false ? '删除' : '恢复';
              return h('div', [
                h('Button', {
                  props: {
                    type: type,
                    size: 'small',
                    disabled:row.id===this.user.id
                  },
                  on: {
                    click: () => {
                      this.axios.delete('/api/admin/' + row.id ).then((res) => {
                        this.getAdmin()
                      }).catch((err)=>{
                        this.$Message.error({
                          content: '操作失败',
                          duration: 2,
                          closable: true
                        });
                      })
                    }
                  }
                }, text)
              ]);
            }
          }
        ],
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
                this.axios.get('/api/admin/isExist/' + value).then((res) => {
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
        }
      }
    },
    computed:{
      user(){
        return this.$store.state.user.user
      }
    },
    methods: {
      getAdmin() {
        this.loading = true
        this.axios.get('/api/admin/' + this.page + '/' + this.pageSize, {
          params: {
            keyword: this.keyword
          }
        }).then((res) => {
          this.tableData = res.data.list;
          this.total = res.data.total;
          this.loading = false
        }).catch(function (err) {
          this.loading = false
        })
      },
      changePage(page) {
        this.page = page;
        this.getAdmin()
      },
      pageSizeChange(pageSize) {
        this.pageSize = pageSize
        this.getAdmin()
      },
      handleSearch() {
        this.getAdmin()
      },
      handleCancel() {
        if (this.keyword.trim().length > 0) {
          this.keyword = '';
          this.getAdmin()
        }
      },
      handleAdd(){
        this.addAdminModal=true
      },
      saveAdmin() {
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            this.userForm.sex=this.userForm.sexText=="1";
            this.axios.post('/api/admin',
              this.userForm
            ).then((res) => {
              if (res.data==1) {
                this.$Message.success({
                  content: '增加成功！',
                  duration: 3,
                  closable: true
                });
                this.cancleSave()
                this.getAdmin()
              } else {
                this.$Message.error({
                  content: '增加失败,请稍后再试！',
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
      cancleSave() {
        this.addAdminModal=false
        this.$refs['userForm'].resetFields();
      }
    },
    created() {
      this.getAdmin()
    }
  }
</script>

<style scoped>
  @import '../../../styles/common.less';
</style>
