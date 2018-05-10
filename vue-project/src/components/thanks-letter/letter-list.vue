<template>

</template>

<script>
  export default {
    name: "letter-list",
    data() {
      return {
        animate: false,
        items: [
          {name: "这里是感谢信一哈哈哈哈1"},
          {name: "这里是感谢信一哈哈哈哈2"},
          {name: "这里是感谢信一哈哈哈哈3"},
          {name: "这里是感谢信一哈哈哈哈4"},
          {name: "这里是感谢信一哈哈哈哈5"},
          {name: "这里是感谢信一哈哈哈哈6"},
          {name: "这里是感谢信一哈哈哈哈7"},
          {name: "这里是感谢信一哈哈哈哈8"},
          {name: "这里是感谢信一哈哈哈哈9"},
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
      setInterval(this.scroll, 1000)
    },
    methods: {
      scroll() {
        this.animate = true;    // 因为在消息向上滚动的时候需要添加css3过渡动画，所以这里需要设置true
        setTimeout(() => {      //  这里直接使用了es6的箭头函数，省去了处理this指向偏移问题，代码也比之前简化了很多
          this.items.push(this.items[0]);  // 将数组的第一个元素添加到数组的
          this.items.shift();               //删除数组的第一个元素
          this.animate = false;  // margin-top 为0 的时候取消过渡动画，实现无缝滚动
        }, 500)
      }
    }
  }
</script>

<style scoped>
  *{
    margin: 0 ;
    padding: 0;
  }
  #box{
    width: 300px;
    height: 32px;
    overflow: hidden;
    padding-left: 30px;
    border: 1px solid black;
  }
  .anim{
    transition: all 0.5s;
    margin-top: -30px;
  }
  #con1 li{
    list-style: none;
    line-height: 30px;
    height: 30px;
  }
</style>
