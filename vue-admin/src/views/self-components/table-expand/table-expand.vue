<style scoped>
  .expand-row{
    margin-bottom: 16px;
  }
</style>
<template>
  <div>
    <div v-if="type==='found'">
      <Row class="expand-row">
        <Col span="8">
          <span class="expand-key">是否领回: </span>
          <span class="expand-value">{{ row.isFound?"是":"否" }}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">失主姓名: </span>
          <span class="expand-value">{{ row.lostName }}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">领回时间: </span>
          <span class="expand-value">{{ row.foundLostDatetime | formatDateTime}}</span>
        </Col>
      </Row>
      <Row>
        <Col span="8">
          <span class="expand-key">是否审核: </span>
          <span class="expand-value">{{row.hasReviewed?"已审核":"未审核"}}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">审核结果: </span>
          <span class="expand-value">{{row.hasReviewed?row.hasPassed?"通过":"驳回":"默认"}}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">审核人员: </span>
          <span class="expand-value">{{ row.adminName }}</span>
        </Col>
      </Row>
    </div>
    <div v-else>
      <Row class="expand-row">
        <Col span="8">
          <span class="expand-key">是否找回: </span>
          <span class="expand-value">{{ row.isFound?"是":"否" }}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">拾取人姓名: </span>
          <span class="expand-value">{{ row.foundName }}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">找回时间: </span>
          <span class="expand-value">{{ row.foundDatetime | formatDateTime}}</span>
        </Col>
      </Row>
      <Row>
        <Col span="8">
          <span class="expand-key">是否审核: </span>
          <span class="expand-value">{{row.hasReviewed?"已审核":"未审核"}}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">审核结果: </span>
          <span class="expand-value">{{row.hasReviewed?row.hasPassed?"通过":"驳回":"默认"}}</span>
        </Col>
        <Col span="8">
          <span class="expand-key">审核人员: </span>
          <span class="expand-value">{{ row.adminName }}</span>
        </Col>
      </Row>
    </div>
  </div>
</template>
<script>
  import {formatDate} from '@/util/date.js';
  export default {
    name:'table-expand',
    props: {
      row: Object,
      type:String
    },
    filters:{
      formatDateTime(time){
        var date = new Date(time);
        return formatDate(date,'yyyy-MM-dd hh:mm')
      }
    },
  };
</script>
