<template>
  <div>
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
    name: "thanks-letter",
    data() {
      return {
        tableData: [],
        loading: false,
        page: 1,
        pageSize: 10,
        total: 0,
        pageOpts: [10, 50, 90],
        tableColumns: [
          {
            type: 'expand',
            width: 20,
            render: (h, params) => {
              return h('div', {
                domProps: {
                  innerHTML: params.row.content
                }
              })
            }
          },
          {
            title: '标题',
            key: 'title',
            align: 'center'
          }, {
            title: '发送人',
            key: 'editorName',
            align: 'center'
          }, {
            title: '发送日期',
            key: 'submitDatetime',
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
                }, this.formatDateTime(params.row.submitDatetime))
              ]);
            }
          },{
            title: '接收人',
            key: 'thanksName',
            align: 'center'
          }, {
            title: '状态',
            key: 'deleted',
            align: 'center',
            render: (h, params) => {
              const row = params.row;
              const color = row.deleted === false ? 'green' : 'red';
              const text = row.deleted === false ? '正常' : '删除';
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
                      this.axios.delete('/api/thanks/letter/' + row.id ).then((res) => {
                        this.getLetter()
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
      getLetter() {
        this.loading = true
        this.axios.get('/api/thanks/letter/admin/' + this.page + '/' + this.pageSize,).then((res) => {
          this.tableData = res.data.list;
          this.total = res.data.total;
          this.loading = false
        }).catch(function (err) {
          this.loading = false
        })
      },
      changePage(page) {
        this.page = page;
        this.getLetter()
      },
      pageSizeChange(pageSize) {
        this.pageSize = pageSize
        this.getLetter()
      },
      formatDateTime(time){
        var date = new Date(time);
        return formatDate(date,'yyyy-MM-dd hh:mm')
      }
    },
    created() {
      this.getLetter()
    }
  }
</script>

<style scoped>
  @import '../../../styles/common.less';
</style>
