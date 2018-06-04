<style lang="less">
  @import './message.less';
</style>

<template>
  <div class="message-main-con">
    <div class="message-mainlist-con">
      <div>
        <Button @click="setCurrentMesType('unread')" size="large" long type="text">
          <transition name="mes-current-type-btn">
            <Icon v-show="currentMessageType === 'unread'" type="checkmark"></Icon>
          </transition>
          <span class="mes-type-btn-text">未读消息</span>
          <Badge class="message-count-badge-outer" class-name="message-count-badge" :count="unreadCount"></Badge>
        </Button>
      </div>
      <div>
        <Button @click="setCurrentMesType('hasread')" size="large" long type="text">
          <transition name="mes-current-type-btn">
            <Icon v-show="currentMessageType === 'hasread'" type="checkmark"></Icon>
          </transition>
          <span class="mes-type-btn-text">已读消息</span>
          <Badge class="message-count-badge-outer" class-name="message-count-badge" :count="hasreadCount"></Badge>
        </Button>
      </div>
    </div>
    <div class="message-content-con">
      <transition name="view-message">
        <div v-if="showMesTitleList" class="message-title-list-con">
          <Table ref="messageList" :columns="mesTitleColumns" :data="currentMesList" :no-data-text="noDataText"></Table>
        </div>
      </transition>
      <transition name="back-message-list">
        <div v-if="!showMesTitleList" class="message-view-content-con">
          <div class="message-content-top-bar">
            <span class="mes-back-btn-con"><Button type="text" @click="backMesTitleList"><Icon
              type="chevron-left"></Icon>&nbsp;&nbsp;返回</Button></span>
            <h3 class="mes-title">消息详情</h3>
          </div>
          <div class="mes-title-con">
            <div>
              <Icon type="person"></Icon>&nbsp;&nbsp;{{ mes.nickname }}
            </div>
            <div>
              <Icon type="android-call"></Icon>&nbsp;&nbsp;{{ mes.phoneNumber }}
            </div>
            <div>
              <Icon type="android-time"></Icon>&nbsp;&nbsp;{{ mes.submitDatetime }}
            </div>
          </div>
          <div class="mes-button-con">
            <Button @click="confirmClick" type="success" long :disabled="mes.isConfirm">{{mes.type==1?'已沟通确认，他（她）是失主':'已经联系他（她）找回失物'}}</Button>
            <Button v-if="mes.type==2" icon="heart" @click="thanksLetter" type="warning" :disabled="!mes.isConfirm">感谢信</Button>
          </div>
          <div class="message-content-body">
            <p class="message-content">{{ mes.content }}</p>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
  import {mapState} from 'vuex'

  export default {
    name: 'message_index',
    data() {
      const markAsreadBtn = (h, params) => {
        return h('Button', {
          props: {
            size: 'small'
          },
          on: {
            click: () => {
              this.axios.get('/api/user/found/markmessage/'+params.row.id+"/"+params.row.type).then((res) => {
                if(res.data==1){
                  this.hasreadMesList.unshift(this.currentMesList.splice(params.index, 1)[0]);
                  this.$store.commit('setMessageCount', this.unreadMesList.length);
                }
              }).catch(function (err) {
                console.log(err)
              })

            }
          }
        }, '标为已读');
      };
      return {
        currentMesList: [],
        unreadMesList: [],
        hasreadMesList: [],
        currentMessageType: 'unread',
        showMesTitleList: true,
        unreadCount: 0,
        hasreadCount: 0,
        noDataText: '暂无未读消息',
        mes: {

        },
        mesTitleColumns: [
          {
            title: '内容',
            key: 'content',
            align: 'left',
            ellipsis: true,
            render: (h, params) => {
              return h('a', {
                on: {
                  click: () => {
                    this.showMesTitleList = false;
                    this.mes = params.row;
                    this.mes.content=params.row.content!=null&&params.row.content.length>0?params.row.content:'留言者没写什么哦！'
                    this.mes.submitDatetime=this.formatDate(params.row.submitDatetime)
                    this.mes.index=params.index;
                  }
                }
              }, params.row.content!=null&&params.row.content.length>0?params.row.content:'留言者没写什么哦！');
            }
          },
          {
            title: '来源',
            key: 'type',
            align: 'center',
            ellipsis: true,
            render: (h, params) => {
              return  h('a', {
                on: {
                  click: () => {
                    if(params.row.type==1){
                      this.$router.push({
                        name:'foundDetail',
                        params: { id: params.row.foreignId }
                      })
                    }else{
                      this.$router.push({
                        name:'lostDetail',
                        params: { id: params.row.foreignId }
                      })
                    }
                  }
                }
              }, params.row.type==1?'失物招领':'寻物启事')
            }
          },
          {
            title: '时间',
            key: 'submitDateTime',
            align: 'center',
            width: 160,
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
                }, this.formatDate(params.row.submitDatetime))
              ]);
            }
          },
          {
            title: '操作',
            key: 'asread',
            align: 'center',
            width: 100,
            render: (h, params) => {
              if (this.currentMessageType === 'unread') {
                return h('div', [
                  markAsreadBtn(h, params)
                ]);
              } else if (this.currentMessageType === 'hasread') {
                // return h('div', [
                //   deleteMesBtn(h, params)
                // ]);
              }
            }
          }
        ]
      };
    },
    computed:{
      ...mapState({
        // 传字符串参数 'count' 等同于 `state => state.count`
        user: 'user',
      })
    },
    methods: {
      getMassge(isRead) {
        this.axios.get('/api/user/found/message/' + this.user.id+'/'+isRead).then((res) => {
          if(isRead==1){
            this.hasreadMesList=res.data
            this.hasreadCount = this.hasreadMesList.length;
          }else{
            this.currentMesList=this.unreadMesList=res.data;
            this.unreadCount = this.unreadMesList.length;
          }
        }).catch(function (err) {
          console.log(err)
        })
      },
      confirmClick(){
        this.$Modal.confirm({
          title: '确认提示',
          content: '<p>请您再次确认自己的操作！</p>',
          onOk: () => {
            this.axios.get('/api/user/found/confirmmessage/'+this.mes.id+"/"+this.mes.type).then((res) => {
              this.$Message.info('操作成功！');
              if(this.currentMessageType=='unread'){
                this.mes.isConfirm=true;
                this.currentMesList[this.mes.index].isConfirm=true;
                this.unreadMesList[this.mes.index].isConfirm=true;
              }else{
                this.mes.isConfirm=true;
                this.currentMesList[this.mes.index].isConfirm=true;
                this.hasreadMesList[this.mes.index].isConfirm=true;
              }
            }).catch(function (err) {
              this.$Message.error('操作失败！');
            })
          }
        });
      },
      thanksLetter(){
        this.$router.push({ path: 'text-editor', query: { lostId:this.mes.foreignId,thanksId:this.mes.userId}})
      },
      formatDate(time) {
        let date = new Date(time);
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();
        return year + '/' + month + '/' + day + '  ' + hour + ':' + minute + ':' + second;
      },
      backMesTitleList() {
        this.showMesTitleList = true;
      },
      setCurrentMesType(type) {
        if (this.currentMessageType !== type) {
          this.showMesTitleList = true;
        }
        this.currentMessageType = type;
        if (type === 'unread') {
          this.noDataText = '暂无未读消息';
          this.currentMesList = this.unreadMesList;
        } else if (type === 'hasread') {
          this.noDataText = '暂无已读消息';
          this.currentMesList = this.hasreadMesList;
        }
      }
    },
    created() {
      this.getMassge(0)
      this.getMassge(1)
    },
    watch: {
      unreadMesList(arr) {
        this.unreadCount = arr.length;
      },
      hasreadMesList(arr) {
        this.hasreadCount = arr.length;
      }
    }
  };
</script>

