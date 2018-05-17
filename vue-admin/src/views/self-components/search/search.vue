<template>
    <div>
      <Row>
        <Col :xs="24" :sm="12" :md="7" :lg="7">
          <Input v-model="keyword" size="small" placeholder="搜索关键字（钱包、U盘）" style="width: 200px"></Input>
        </Col>
        <Col :xs="24" :sm="12" :md="7" :lg="7">
          <DatePicker v-model="startDate" type="date" size="small" placeholder="开始日期" style="width: 150px"></DatePicker>
        </Col>
        <Col :xs="24" :sm="12" :md="7" :lg="7">
          <DatePicker v-model="endDate" type="date" size="small" placeholder="结束日期" style="width: 150px"></DatePicker>
        </Col>
        <Col :xs="24" :sm="12" :md="3" :lg="3">
          <Button size="small" icon="ios-search" @click="search" type="primary"
                  :disabled="keyword.trim().length==0&&startDate.length==0&&endDate.length==0">搜索
          </Button>
          <Button @click="cancel" size="small" type="ghost">取消</Button>
        </Col>
      </Row>
    </div>
</template>

<script>
  export default {
    name: "search",
    data() {
      return {
        keyword: '',
        startDate: '',
        endDate:''
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

</style>
