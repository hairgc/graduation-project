<template>
  <Card :padding="0">
    <div class="card-head">
      <Icon type="ios-heart"></Icon>
      好人信箱
      <Icon type="ios-email"></Icon>
    </div>
    <div style="padding:0 5px;margin-top: 0">
      <vue-seamless-scroll :data="letters" :class-option="optionSingleHeightTime" class="seamless-warp">
        <ul>
          <li v-for="letter in letters" class="item">
            <Card :padding="0">
              <div @click="goDetail(letter.id)" class="button">
                <p class="title" v-text="letter.title"></p>
              </div>
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
        letters: []
      }
    },
    created() {
      this.getLetters()
    },
    computed: {
      optionSingleHeightTime() {
        return {
          singleHeight: 60,
          waitTime: 1500
        }
      }
    },
    methods: {
      getLetters() {
        this.axios.get('/api/thanks/letter',
        ).then((res) => {
          this.letters=res.data;
        }).catch(function (err) {

        })
      },
      goDetail(id){
        this.$router.push({
          name:'letter',
          params:{id:id}
        })
      }
    }
  }
</script>

<style scoped>
  .seamless-warp {
    height: 450px;
    overflow: hidden;
  }

  .card-head {
    text-align: center;
    padding: 14px 15px;
    border-bottom: 1px solid transparent;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
  //background: #19be6b; //border-bottom: 1px solid #e9eaec; //padding: 14px 16px; line-height: 1;
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
  }

  .item {
    height: 60px;
    padding: 2px 0;
  }

  .button {
    height: 56px;
    cursor: pointer;
  //border: 1px solid #2d8cf0; line-height: 28px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
  }
</style>
