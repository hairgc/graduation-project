<template>
  <div>
    <Row>
      <Input v-model="keyword" placeholder="请输入姓名、学号或手机号搜索..." style="width: 200px"/>
      <span style="margin: 0 10px;"><Button @click="handleSearch" type="primary" icon="search"
                                            :disabled="keyword.trim().length==0">搜索</Button></span>
      <Button @click="handleCancel" type="ghost">取消</Button>
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

  </div>
</template>

<script>
  import {formatDate} from '@/util/date.js';
  export default {
    name: "user-management",
    data() {
      return {
        tableData: [],
        loading: false,
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
          }, {
            title: '注册日期',
            key: 'registerTime',
            align: 'center',
            render: (h, params) => {
              return h('span', [
                h('Icon', {
                  props: {
                    type: 'android-time',
                    size: 12
                  },
                  style: {
                    margin: '0 5px'
                  }
                }),
                h('span', {
                  props: {
                    type: 'android-time',
                    size: 12
                  }
                }, this.formatDateTime(params.row.registerTime))
              ]);
            }
          }, {
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
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.axios.delete('/api/user/' + row.id ).then((res) => {
                        this.getUser()
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
        ]
      }
    },
    methods: {
      getUser() {
        this.loading = true
        this.axios.get('/api/user/' + this.page + '/' + this.pageSize, {
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
        this.getUser()
      },
      pageSizeChange(pageSize) {
        this.pageSize = pageSize
        this.getUser()
      },
      handleSearch() {
        this.getUser()
      },
      handleCancel() {
        if (this.keyword.trim().length > 0) {
          this.keyword = '';
          this.getUser()
        }
      },
      formatDateTime(time){
        var date = new Date(time);
        return formatDate(date,'yyyy-MM-dd hh:mm')
      }
    },
    created() {
      this.getUser()
    }
  }
</script>

<style scoped>
  @import '../../../styles/common.less';
</style>
