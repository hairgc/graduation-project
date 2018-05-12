<template>
  <div>
    <div class="content-top-bar">
            <span class="back-btn-con"><Button type="text" @click="back"><Icon
              type="chevron-left"></Icon>&nbsp;&nbsp;返回</Button></span>
      <h3 class="title" v-text="letter.title"></h3>
    </div>
    <div class="title-con">
      <div>
        发送人：{{ letter.editorName }}
      </div>
      <div v-if="letter.thanksName">
        <Icon type="heart"></Icon>好心人：{{ letter.thanksName }}
      </div>
      <div>
        <Icon type="android-time"></Icon>&nbsp;&nbsp;{{ letter.submitDatetime|formatDateTime }}
      </div>
    </div>
    <div v-html="letter.content">
    </div>
  </div>

</template>

<script>
  import {formatDate} from './../../util/date.js';
    export default {
        name: "letter-detail",
      data(){
          return{
            letter:{}
          }
      },
      methods: {
        getLetter() {
          this.axios.get('/api/thanks/letter/'+this.$route.params.id,
          ).then((res) => {
            this.letter=res.data;
          }).catch(function (err) {

          })
        },
        back(){
          this.$router.go(-1);
        }
      },
      filters:{
        formatDateTime(time){
          var date = new Date(time);
          return formatDate(date,'yyyy-MM-dd hh:mm')
        }
      },
      created() {
        this.getLetter()
      },
      watch: {
        '$route' (to, from) {
          this.getLetter()
        }
      }
    }
</script>

<style scoped lang="less">
  .title-con{
    display: flex;
    justify-content: space-around;
    width: 100%;
    padding: 10px 0;
    text-align: center;
    font-size: 14px;
    color: #b7b7b5;
    font-weight: 500;
    margin-top: 25px;
  }
  .content-top-bar{
    height: 40px;
    width: 100%;
    background: white;
    position: absolute;
    left: 0;
    top: 0;
    border-bottom: 1px solid #dededb;
    .back-btn-con{
      position: absolute;
      width: 70px;
      height: 30px;
      left: 0;
      top: 5px;
    }
    .title{
      position: absolute;
      top: 0;
      right: 70px;
      bottom: 0;
      left: 70px;
      line-height: 40px;
      padding: 0 30px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      text-align: center;
    }
  }
</style>
