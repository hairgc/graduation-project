<template>
    <div>
      <Row :gutter="12">
        <Col :xs="12" :sm="8" :md="6" :lg="4" v-for="row,index in rows" :key="row.id" :style="{marginTop:'10px'}">
          <Card>
            <p slot="title">
              {{row.foundName}}
            </p>
            <p class="label" v-if="row.label">
              <Tag v-for="label in row.label.split('#')" :key="label" color="red">{{label}}</Tag>
            </p>
            <p v-else>
              <Tag>暂无标签</Tag>
            </p>
            <p class="content">
              {{row.foundDescription}}
            </p>
            <div class="tag">
              <div>
                <iSwitch size="large" :value="row.isFound" :disabled="true">
                  <span slot="open">已找回</span>
                  <span slot="close">寻找</span>
                </iSwitch>
              </div>
              <div>
                {{row.foundDatetime|formatDate}}
              </div>
            </div>

            <p>
              <Button type="success" long>详情</Button>
            </p>
          </Card>
        </Col>
      </Row>
      <Spin size="large" fix v-if="spinShow"></Spin>
      <Page :style="{textAlign:'center',marginTop:'10px'}":total="total" :page-size-opts="page_opts" :page-size="page_size" v-on:on-change="pageChange"
            v-on:on-page-size-change="pageSizeChange" show-sizer show-total show-elevator></Page>
    </div>
</template>

<script>
  import {formatDate} from './../../util/date.js';
    export default {
      name: "found",
      data(){
        return{
          total:100,
          spinShow:false,
          page:1,
          page_size:20,
          page_opts:[20,50,80],
          rows:[]
        }
      },
      computed:{

      },
      filters:{
        formatDate(time){
          var date = new Date(time);
          return formatDate(date,'yyyy-MM-dd')
        },
        formatDateTime(time){
          var date = new Date(time);
          return formatDate(date,'yyyy-MM-dd hh:mm')
        }
      },
      methods:{
        setPage:function (params) {
          this.page=Number.parseInt(params.page);
          this.page_size=Number.parseInt(params.rows);
        },
        pageChange:function (e) {
          this.page=e
          this.$router.push({ name: 'found', params: { page: this.page,rows:this.page_size }})
        },
        pageSizeChange:function (e) {
          this.page_size=e
          this.$router.push({ name: 'found', params: { page: this.page,rows:this.page_size }})
        },
        getFound:function () {
          this.spinShow=true
          this.axios.get('/api/found/'+this.page+'/'+this.page_size).then((res) => {
            this.rows=res.data.list;
            this.total=res.data.total;
            this.spinShow=false
          }).catch(function(err){
            console.log(err)
          })
        }
      },
      created: function () {
        this.setPage(this.$route.params);
        this.getFound()
      },
      watch: {
        '$route' (to, from) {
          this.getFound()
        }
      }
    }
</script>

<style scoped>
  /*.content{*/
    /*display: -webkit-box;*/
    /*-webkit-box-orient: vertical;*/
    /*-webkit-line-clamp: 3;*/
    /*overflow: hidden;*/
  /*}*/
  .content {
    position:relative;
    line-height:1.4em;
    /* 3 times the line-height to show 3 lines */
    height:5.6em;
    overflow:hidden;
  }

  .content::after {
    content:"...";
    position:absolute;
    bottom:0;
    right:0;
    padding:0 20px 1px 45px;
    background:url(http://newimg88.b0.upaiyun.com/newimg88/2014/09/ellipsis_bg.png) repeat-y;
  }
  .label{
    overflow: hidden; text-overflow:ellipsis; white-space: nowrap;
  }
  .tag{
    display: flex;
    justify-content: space-around;
    margin: 5px 0;
    color: darkgrey;
    font-size: 11px;
  }
</style>
