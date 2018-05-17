<template>
  <div>
    <Search @on-search="search" @on-cancel="cancel"
            :initKeyword="searchParams.keyword"
            :init-start-date="(searchParams.startDate==null|| searchParams.startDate.length==0)?null:Number.parseInt(searchParams.startDate)"
            :init-end-date="(searchParams.endDate==null|| searchParams.endDate.length==0)?null:Number.parseInt(searchParams.endDate)"
            buttonType="success"
            back-color="#38cc6b"></Search>
    <Row :gutter="12">
      <Col :xs="12" :sm="8" :md="6" :lg="4" v-for="row,index in rows" :key="row.id" :style="{marginTop:'10px'}">
        <Card :padding="0">
          <div class="card-head">
            <p class="card-head-inner">
              <Icon type="heart-broken"></Icon>
              {{row.lostName}}
            </p>
          </div>
          <div style="padding: 2px;">
            <p class="label" v-if="row.label">
              <Tag v-for="label in row.label.split('#')" :key="label" color="green">{{label}}</Tag>
            </p>
            <p v-else>
              <Tag>暂无标签</Tag>
            </p>
            <p class="content">
              {{row.lostDescription}}
            </p>
            <div class="tag">
              <div>
                <iSwitch size="large" :value="row.isFound" :disabled="true">
                  <span slot="open">找回</span>
                  <span slot="close">寻找</span>
                </iSwitch>
              </div>
              <div>
                {{row.lostDatetime|formatDate}}
              </div>
            </div>

            <p>
              <router-link :to="'/lost/'+row.id">
                <Button type="success" long>详情</Button>
              </router-link>
            </p>
          </div>
        </Card>
      </Col>
    </Row>
    <Spin size="large" fix v-if="spinShow"></Spin>
    <Page :style="{textAlign:'center',marginTop:'10px'}":total="total" :page-size-opts="page_opts" :current="page" :page-size="page_size" v-on:on-change="pageChange"
          v-on:on-page-size-change="pageSizeChange" show-sizer show-total show-elevator></Page>
    <BackTop></BackTop>

    <div class="add-button">
      <Button @click="addLost" type="warning" icon="plus-round">发布</Button>
    </div>
  </div>
</template>

<script>
  import {formatDate} from './../../util/date.js';
  import Search from '../search/search';
  export default {
    name: "lost",
    components:{
      Search
    },
    data(){
      return{
        total:100,
        spinShow:false,
        page:1,
        page_size:20,
        page_opts:[20,50,80],
        rows:[],
        searchParams:{}
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
        this.$router.push({
          name: 'lost',
          params: { page: this.page,rows:this.page_size },
          query:this.searchParams
        })
      },
      pageSizeChange:function (e) {
        this.page_size=e
        this.$router.push({
          name: 'lost',
          params: { page: this.page,rows:this.page_size },
          query:this.searchParams
        })
      },
      search(params){
        this.searchParams=params
        this.$router.push({
          name: 'lost',
          params: { page: 1,rows:20 },
          query:this.searchParams
        })
      },
      cancel(){
        this.searchParams={}
        this.$router.push({
          name: 'lost',
          params: { page: this.page,rows:this.page_size }
        })
      },
      getLost:function () {
        this.spinShow=true
        this.axios.get('/api/lost/'+this.page+'/'+this.page_size, {
          params:this.searchParams
        }).then((res) => {
          this.rows=res.data.list;
          this.total=res.data.total;
          this.spinShow=false
        }).catch(function(err){
          console.log(err)
        })
      },
      addLost(){
        this.$router.push({
          name:"lost-add"
        })
      }
    },
    created: function () {
      this.setPage(this.$route.params);
      this.searchParams=this.$route.query
      this.getLost()
    },
    watch: {
      '$route' (to, from) {
        this.setPage(this.$route.params);
        this.searchParams=this.$route.query
        this.getLost()
      }
    }
  }
</script>

<style scoped>
  .card-head{
    padding: 6px 15px;
    border-bottom: 1px solid transparent;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    line-height: 1;
    background-color: #dff0d8;
    border-color: #d6e9c6;
  }
  .card-head-inner {
    display: inline-block;
    width: 100%;
    height: 20px;
    line-height: 20px;
    font-size: 14px;
    color: #3c763d;
    font-weight: 700;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
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
  .add-button{
    position: fixed;
    top:150px;
    right: 0;
  }
</style>

