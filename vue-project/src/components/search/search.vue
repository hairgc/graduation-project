<template>
  <Affix :offset-top="65" style="padding: 0">
    <div class="search">
      <Row>
        <Col :xs="24" :sm="12" :md="7" :lg="7">
          <Input v-model="keyword" size="small" placeholder="搜索关键字（钱包、U盘）" style="width: 150px"></Input>
        </Col>
        <Col :xs="24" :sm="12" :md="7" :lg="7">
          <DatePicker v-model="startDate" type="date" size="small" placeholder="开始日期" style="width: 150px"></DatePicker>
        </Col>
        <Col :xs="24" :sm="12" :md="7" :lg="7">
          <DatePicker v-model="endDate" type="date" size="small" placeholder="结束日期" style="width: 150px"></DatePicker>
        </Col>
        <Col :xs="24" :sm="12" :md="3" :lg="3">
          <Button type="success" size="small" shape="circle" icon="ios-search" @click="search"
                  :disabled="keyword.trim().length==0&&startDate.length==0&&endDate.length==0">搜索
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
      }
    },
    methods: {
      search() {
        let params = {}
        if (this.keyword.trim().length > 0) {
          params.keyword = this.keyword
        }
        console.log(this.startDate)
        if (this.startDate instanceof Date) {
          params.startDate = this.startDate.getTime()
        }
        if (this.endDate instanceof Date) {
          params.endDate = this.endDate.getTime()
        }
        this.$emit('on-search', params);
      }
    }
  }
</script>

<style scoped>
  .search {
    padding: 5px;
    background-color: #1ccf7c;
    opacity: 99%;
  }
</style>
