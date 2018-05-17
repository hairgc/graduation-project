<template>
  <Affix :offset-top="65" style="padding: 0">
    <div class="search" :style="{backgroundColor:backColor}">
      <Row>
        <Col :xs="24" :sm="12" :md="6" :lg="6">
          <Input v-model="keyword" size="small" placeholder="搜索关键字（钱包、U盘）" style="width: 150px"></Input>
        </Col>
        <Col :xs="24" :sm="12" :md="6" :lg="6">
          <DatePicker v-model="startDate" type="date" size="small" placeholder="开始日期" style="width: 150px"></DatePicker>
        </Col>
        <Col :xs="24" :sm="12" :md="6" :lg="6">
          <DatePicker v-model="endDate" type="date" size="small" placeholder="结束日期" style="width: 150px"></DatePicker>
        </Col>
        <Col :xs="24" :sm="12" :md="6" :lg="6">
          <Button :type="buttonType" size="small" shape="circle" icon="ios-search" @click="search"
                  :disabled="keyword.trim().length==0&&startDate.length==0&&endDate.length==0">搜索
          </Button>
          <Button style="margin-left: 5px" type="ghost" size="small" shape="circle"  @click="cancel">取消
          </Button>
        </Col>
      </Row>
    </div>
  </Affix>
</template>

<script>
  export default {
    name: "search",
    data() {
      return {
        keyword: this.initKeyword,
        startDate: this.initStartDate!=null&&this.initStartDate>0 ?new Date(this.initStartDate):'',
        endDate:this.initEndDate!=null&&this.initEndDate>0?new Date(this.initEndDate):''
      }
    },
    props: {
      initKeyword: {
        type: [String, Number],
        default: ''
      },
      initStartDate: {
        type: Number,
        default: null
      },
      initEndDate: {
        type: Number,
        default: null
      },
      backColor:{
        type:String,
        default:''
      },
      buttonType:{
        type:String,
        default:''
      }
    },
    methods: {
      search() {
        let params = {}
        if (this.keyword.trim().length > 0) {
          params.keyword = this.keyword
        }
        if (this.startDate instanceof Date) {
          params.startDate = this.startDate.getTime()
        }
        if (this.endDate instanceof Date) {
          params.endDate = this.endDate.getTime()
        }
        this.$emit('on-search', params);
      },
      cancel(){
        this.keyword= ''
        this.startDate= ''
        this.endDate=''
        this.$emit('on-cancel');
      }
    }
  }
</script>

<style scoped>
  .search {
    padding: 5px;
    background-color: #ffcc55;
    opacity: 99%;
  }
</style>
