<template>
  <div>
    <Row>
      <Col span="16">
        <Row>
          <Col span="24">
            <div class="label" v-if="lost.label">
              <Tag v-for="label in lost.label.split('#')" :key="label" color="red">{{label}}</Tag>
            </div>
            <div v-else>
              <Tag>暂无标签</Tag>
            </div>
          </Col>
          <Col span="24">
            <Row class="content">
              <Col span="8" class="content-name"><h3>物品名称:</h3></Col>
              <Col span="16" class="content-detail">{{lost.lostName}}</Col>
            </Row>
            <Row class="content">
              <Col span="8" class="content-name"><h3>丢失时间:</h3></Col>
              <Col span="16" class="content-detail">{{lost.lostDatetime|formatDateTime}}</Col>
            </Row>
          </Col>
        </Row>

      </Col>
      <Col span="8">
        <iCircle :percent="100" stroke-color="#5cb85c" v-if="lost.isFound">
          <Icon type="ios-checkmark-empty" size="60" style="color:#5cb85c"></Icon>
          <div style="color:#5cb85c">
            已找回
          </div>
        </iCircle>
        <iCircle :percent="80" stroke-color="#ff5500" v-else>
          <Icon type="ios-close-empty" size="60" style="color:#ff5500"></Icon>
          <div style="color:#ff5500">
            寻找中
          </div>
        </iCircle>
      </Col>
    </Row>
    <Row>
      <Col span="12" style="padding: 10px">
        <Card>
          <img v-if="lost.lostPicture" :src="'http://images.mahui53541.com/'+lost.lostPicture" class="image"/>
          <img v-else src="../../assets/no_picture.png" class="image"/>
        </Card>
      </Col>
      <Col span="12">
        <Row class="content">
          <Col span="8" class="content-name"><h3>失主:</h3></Col>
          <Col span="16" class="content-detail">{{lost.nickname}}</Col>
        </Row>
        <Row class="content">
          <Col span="8" class="content-name"><h3>联系方式:</h3></Col>
          <Col span="16" class="content-detail">{{phoneNumber}}</Col>
        </Row>
        <Row class="content">
          <Col span="24" class="content-name">{{lost.lostDescription}}</Col>
        </Row>
        <Row class="content">
          <Col span="24" class="content-name">
            <Button type="warning" long @click="clickButton" :disabled="lost.isFound">在我这，点此联系一下！</Button>
          </Col>
        </Row>
      </Col>
    </Row>
    <Spin size="large" fix v-if="spinShow"></Spin>
    <Modal v-model="submitMessageModal" :closable='false' :mask-closable=false :width="400">
      <Form ref="formValidate" :model="msgForm"  label-position="right" :rules="ruleValidate">
        <FormItem prop="message">
          <Input type="textarea" :rows="4" :autofocus="true" v-model="msgForm.message" :placeholder="placeholder" ></Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button type="text" @click="cancelSubmit">取消</Button>
        <Button type="primary" :loading="saveLoading" @click="submitMessage">发送</Button>
      </div>
    </Modal>
  </div>

</template>

<script>
  import {formatDate} from './../../util/date.js';
  import { mapState } from 'vuex'

  export default {
    name: "lostDetail",
    data(){
      return{
        spinShow:true,
        id:null,
        hasClick:false,
        lost:{},
        submitMessageModal:false,
        saveLoading:false,
        placeholder: '请输入你的留言,建议您详细说明拾取物品的外貌特征，以便丢失人确定...',
        msgForm:{
          message:''
        },
        ruleValidate: {
          message: [
            {required: true, message: '留言不能为空', trigger: 'blur'}
          ],
        }
      }
    },
    computed:{
      ...mapState({
        // 传字符串参数 'count' 等同于 `state => state.count`
        user: 'user',
      }),
      phoneNumber(){
        if(this.hasClick){
          return this.lost.phoneNumber;
        }else{
          return '***********';
        }

      }
    },
    methods:{
      getLost:function () {
        this.spinShow=true
        this.axios.get('/api/lost/'+this.id).then((res) => {
          this.spinShow=false
          this.lost=res.data
        }).catch(function(err){
          console.log(err)
        })
      },
      cancelSubmit () {
        this.submitMessageModal = false;
        this.$refs['formValidate'].resetFields();
      },
      submitMessage:function(){
        this.$refs['formValidate'].validate((valid) => {
          if (valid) {
            this.spinShow=true
            this.saveLoading=true
            if(this.user!=null){
              this.axios.post('/api/user/lost',
                {
                  lostId:this.id,
                  userId:this.user.id,
                  isConfirm:false,
                  content:this.msgForm.message,
                  isRead:false,
                  submitDatetime:new Date().getTime()
                }
              ).then((res) => {
                this.spinShow=false
                this.submitMessageModal=false
                this.saveLoading=false
                this.hasClick=true
                this.$refs['formValidate'].resetFields();
                this.$Notice.success({
                  title: '发送成功',
                  desc:  '消息已成功发送至失主！ ',
                  duration: 2
                });
              }).catch(function(err){
                this.saveLoading=false
                this.$Notice.error({
                  title: '发送失败',
                  desc:  '消息发送失败，请稍后再试！ ',
                  duration: 2
                });
              })
            }else{
              this.$router.push({
                name: 'login'
              })
            }
          }
        })
      },
      clickButton:function () {
        this.submitMessageModal=true
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
      this.getLost()
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
