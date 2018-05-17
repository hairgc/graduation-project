<template>
  <div>
    <Row>
      <search @on-search="handleSearch" @on-cancel="handleCancel"></search>
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
  import expandRow from '@/views/self-components/table-expand/table-expand.vue';
  import search from '@/views/self-components/search/search.vue';
  export default {
    name: "found-management",
    components: {
      expandRow,
      search
    },
    data() {
      return {
        tableData: [],
        loading: false,
        user:this.$store.state.user.user,
        page: 1,
        pageSize: 10,
        total: 0,
        pageOpts: [10, 50, 90],
        searchParams:{},
        tableColumns: [
          {
            type: 'expand',
            width: 20,
            render: (h, params) => {
              return h(expandRow, {
                props: {
                  row: params.row,
                  type:'found'
                }
              })
            }
          },
          {
            title: '拾取物品',
            key: 'foundName',
            align: 'center'
          }, {
            title: '详细描述',
            key: 'foundDescription',
            align: 'center',
            width:250
          }, {
            title: '图片',
            key: 'foundPicture',
            align: 'center',
            width:80,
            render: (h, params) => {
              const row = params.row;
              if(row.foundPicture){
                return h('img', {
                  attrs:{
                    src: 'http://images.mahui53541.com/'+row.foundPicture,
                    style:'height:44px;border-radius: 3px;margin:3px;'
                  }
                });
              }
            }
          },{
            title: '发布人',
            key: 'userName',
            width:80
          }, {
            title: '发布日期',
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
          }, {
            title: '状态',
            key: 'deleted',
            align: 'center',
            render: (h, params) => {
              const row = params.row;
              const color = row.deleted === true ? 'red' : row.hasPassed === true?'green':'yellow';
              const text = row.deleted === true ? '删除' : row.hasPassed === false?"驳回":row.isFound?'领回':'寻找';
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
            width: 130,
            align: 'center',
            render: (h, params) => {
              const row = params.row;
              const type1 = row.deleted === false ? 'error' : 'success';
              const text1 = row.deleted === false ? '删除' : '恢复';
              const type2 = row.hasPassed === true ? 'warning' : 'success';
              const text2 = row.hasPassed === true ? '驳回' : '通过';
              return h('div', [
                h('Button', {
                  props: {
                    type: type1,
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.axios.delete('/api/found/' + row.id ).then((res) => {
                        this.getFound()
                      }).catch((err)=>{
                        this.$Message.error({
                          content: '操作失败',
                          duration: 2,
                          closable: true
                        });
                      })
                    }
                  }
                }, text1),
                h('Button', {
                  props: {
                    type: type2,
                    size: 'small',
                    disabled:row.deleted
                  },
                  on: {
                    click: () => {
                      this.axios.put('/api/found/'+this.user.id+"/"+ row.id ).then((res) => {
                        this.getFound()
                      }).catch((err)=>{
                        this.$Message.error({
                          content: '操作失败',
                          duration: 2,
                          closable: true
                        });
                      })
                    }
                  }
                }, text2)
              ]);
            }
          }
        ]
      }
    },
    methods: {
      getFound() {
        this.loading = true
        this.axios.get('/api/found/admin/' + this.page + '/' + this.pageSize, {
          params: this.searchParams
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
        this.getFound()
      },
      pageSizeChange(pageSize) {
        this.pageSize = pageSize
        this.getFound()
      },
      handleSearch(params) {
        this.searchParams=params
        this.getFound()
      },
      handleCancel() {
        if (this.searchParams.keyword!=null&&this.searchParams.keyword.trim().length > 0 ||
          (this.searchParams.startDate!=null && this.searchParams.startDate.length>0)||
          (this.searchParams.endDate!=null && this.searchParams.endDate.length>0)) {
          this.searchParams={}
          this.getFound()
        }
      },
      formatDateTime(time){
        var date = new Date(time);
        return formatDate(date,'yyyy-MM-dd hh:mm')
      }
    },
    created() {
      this.getFound()
    }
  }
</script>

<style scoped>
  @import '../../../styles/common.less';
</style>
