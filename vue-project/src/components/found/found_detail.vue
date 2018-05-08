<template>
  <div>
    <Row>
      <Col span="16">
        <Row>
          <Col span="24">
            <div class="label" v-if="found.label">
              <Tag v-for="label in found.label.split('#')" :key="label" color="red">{{label}}</Tag>
            </div>
            <div v-else>
              <Tag>暂无标签</Tag>
            </div>
          </Col>
          <Col span="24">
            <Row class="content">
              <Col span="8" class="content-name"><h3>物品名称:</h3></Col>
              <Col span="16" class="content-detail">{{found.foundName}}</Col>
            </Row>
            <Row class="content">
              <Col span="8" class="content-name"><h3>拾取时间:</h3></Col>
              <Col span="16" class="content-detail">{{found.foundDatetime|formatDateTime}}</Col>
            </Row>
          </Col>
        </Row>

      </Col>
      <Col span="8">
        <iCircle :percent="100" stroke-color="#5cb85c" v-if="found.isFound">
          <Icon type="ios-checkmark-empty" size="60" style="color:#5cb85c"></Icon>
          <div style="color:#5cb85c">
            已领回
          </div>
        </iCircle>
        <iCircle :percent="80" stroke-color="#ff5500" v-else>
          <Icon type="ios-close-empty" size="60" style="color:#ff5500"></Icon>
          <div style="color:#ff5500">
            待招领
          </div>
        </iCircle>
      </Col>
    </Row>
    <Row>
      <Col span="12" style="padding: 10px">
        <Card>
          <img v-if="found.foundPicture" :src="found.foundPicture" class="image"/>
          <img v-else src="../../assets/no_picture.png" class="image"/>
        </Card>
      </Col>
      <Col span="12">
        <Row class="content">
          <Col span="8" class="content-name"><h3>拾取人:</h3></Col>
          <Col span="16" class="content-detail">{{found.nickname}}</Col>
        </Row>
        <Row class="content">
          <Col span="8" class="content-name"><h3>联系方式:</h3></Col>
          <Col span="16" class="content-detail">{{phoneNumber}}</Col>
        </Row>
        <Row class="content">
          <Col span="24" class="content-name">{{found.foundDescription}}</Col>
        </Row>
        <Row class="content">
          <Col span="24" class="content-name">
            <Button type="warning" long @click="clickButton">是我的，点我联系一下！</Button>
          </Col>
        </Row>
      </Col>
    </Row>
    <Spin size="large" fix v-if="spinShow"></Spin>
  </div>

</template>

<script>
  import {formatDate} from './../../util/date.js';
  import { mapState } from 'vuex'

    export default {
      name: "foundDetail",
      data(){
        return{
          spinShow:true,
          id:null,
          hasClick:false,
          found:{},
          message:''
        }
      },
      computed:{
        ...mapState({
           // 传字符串参数 'count' 等同于 `state => state.count`
           user: 'user',
         }),
        phoneNumber(){
          if(this.hasClick){
            return this.found.phoneNumber;
          }else{
            return '***********';
          }

        }
      },
      methods:{
        getFound:function () {
          this.spinShow=true
          this.axios.get('/api/found/'+this.id).then((res) => {
            this.spinShow=false
            this.found=res.data
          }).catch(function(err){
            console.log(err)
          })
        },
        submitMessage:function(){
          this.spinShow=true
          if(this.user!=null){
            this.axios.post('/api/user/found',
              {
                foundId:this.id,
                userId:this.user.id,
                isConfirm:false,
                content:this.message,
                isRead:false,
                submitDatetime:new Date().getTime()
              }
            ).then((res) => {
              this.spinShow=false
              console.log(res)
            }).catch(function(err){
              console.log(err)
            })
          }else{
            this.$router.push({
              name: 'login'
            })
          }

        },
        clickButton:function () {
          this.$Modal.confirm({
            render: (h) => {
              return h('Input', {
                props: {
                  value: this.message,
                  type:'textarea',
                  rows:4,
                  autofocus: true,
                  placeholder: '请输入你的留言,建议您详细说明丢失物品的外貌特征，以便拾取人判断...'
                },
                on: {
                  input: (val) => {
                    this.message = val;
                  }
                }
              })
            },
            okText: '提交',
            onOk: () => {
              this.submitMessage()
            },
            onCancel: () => {
              this.message=''
            }
          })
        }
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
      created: function () {
        this.id=Number.parseInt(this.$route.params.id);
        this.getFound()
      },
    }
</script>

<style scoped>
  .content{
    padding:10px 0;
  }
  .image{
    width: 100%;
  }
</style>
