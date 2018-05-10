<template>
  <Card :padding="0">
    <div class="card-head">
      <Icon type="ios-heart"></Icon>
      好人信箱
      <Icon type="ios-email"></Icon>
    </div>
  <div style="padding:0 5px;margin-top: 0" >
    <vue-seamless-scroll :data="items" :class-option="optionSingleHeightTime" class="seamless-warp">
      <ul>
        <li v-for="item in items" class="item">
          <Card class="button" :padding="3">
            <p class="title" v-text="item.name"></p>
          </Card>
        </li>
      </ul>
    </vue-seamless-scroll>
  </div>
  </Card>
</template>

<script>
  import vueSeamlessScroll from 'vue-seamless-scroll'
  export default {
    name: "letter-list",
    components: {
      vueSeamlessScroll
    },
    data() {
      return {
        animate: false,
        items: [
          {name: "这里是感谢信一哈哈哈这里是感谢信一哈哈1"},
          {name: "这里是感谢信一哈哈哈哈2"},
          {name: "这里是感谢信一哈哈这里是感谢信一哈哈哈3"},
          {name: "这里是感谢信一哈哈哈哈4"},
          {name: "这里是感谢信一哈哈哈哈5"},
          {name: "这里是感谢信一哈哈哈哈6"},
          {name: "这里是感谢信一里是感谢信里是感谢信里是感谢信里是感谢信里是感谢信哈哈哈哈7"},
          {name: "这里是感谢信一哈哈哈哈8"},
          {name: "这里是感谢信一哈哈哈这里是感谢信一哈哈9"},
          {name: "这里是感谢信一哈哈哈哈10"},
          {name: "这里是感谢信一哈哈哈哈11"},
          {name: "这里是感谢信一哈哈哈哈12"},
          {name: "这里是感谢信一哈哈哈哈131"},
          {name: "这里是感谢信一哈哈哈哈14"},
          {name: "这里是感谢信一哈哈哈哈15"},
          {name: "这里是感谢信一哈哈哈哈16"},
          {name: "这里是感谢信一哈哈哈哈17"},
          {name: "这里是感谢信一哈哈哈哈18"},
          {name: "这里是感谢信一哈哈哈哈19"},
          {name: "这里是感谢信一哈哈哈哈20"},
          {name: "这里是感谢信一哈哈哈哈21"},
          {name: "这里是感谢信一哈哈哈哈22"},
          {name: "这里是感谢信一哈哈哈哈23"},
          {name: "这里是感谢信一哈哈哈哈24"},
        ]
      }
    },
    created() {

    },
    computed: {
      optionSingleHeightTime () {
        return {
          singleHeight: 60,
          waitTime: 1500
        }
      }
    },
    methods: {

    }
  }
</script>

<style scoped>
  .seamless-warp {
    height: 450px;
    overflow: hidden;
  }
  .card-head{
    text-align: center;
    padding: 14px 15px;
    border-bottom: 1px solid transparent;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    //background: #19be6b;
    //border-bottom: 1px solid #e9eaec;
    //padding: 14px 16px;
    line-height: 1;
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
  }
  .item{
    height: 60px;
    padding: 2px 0;
  }
  .button{
    height: 56px;
    //border: 1px solid #2d8cf0;
    line-height: 28px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
  }
</style>
