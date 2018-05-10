<style lang="less">
  @import '../../assets/loading.less';
  .save-button{
    position: fixed;
    top:150px;
    right: 0;
  }
</style>

<template>
  <div>
    <Card shadow>
      <Form ref="form" :model="form" :rules="ruleInline">
        <FormItem prop="title">
          <Input type="text" v-model="form.title" placeholder="标题"/>
        </FormItem>
        <FormItem prop="content">
          <tinymce id="d1" :plugins="plugins" v-model="form.content" :other_options="initOptions" :editorInit="editorInit()" ref="tm"></tinymce>
        </FormItem>
        <FormItem>
          <div class="save-button">
            <Button @click="submitLetter" type="warning">发送</Button>
          </div>
        </FormItem>
      </Form>


    </Card>
    <Spin fix v-if="spinShow"></Spin>
  </div>
</template>

<script>
  import tinymce from 'vue-tinymce-editor';
  import { mapState } from 'vuex'
  import * as language from '../../assets/tinymce/langs/zh_CN.GB2312'
  export default {
    name: 'text-editor',
    components: {tinymce},
    data() {
      return {
        form:{
          content: '',
          title:''
        },
        ruleInline: {
          content: [
            { required: true, message: '请输入内容', trigger: 'blur' },
            { type: 'string', min: 100, message: '字数太少了', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' },
            { type: 'string', min: 6,max:50, message: '最少6个字,最多50个字', trigger: 'blur' }
          ]
        },
        lostId:this.$route.query.lostId,
        foundId:this.$route.query.foundId,
        thanksId:this.$route.query.thanksId,
        spinShow:true,
        plugins: [
          'advlist autolink lists link image charmap print preview hr anchor pagebreak imagetools',
          'searchreplace visualblocks visualchars code fullpage',
          'insertdatetime media nonbreaking save table contextmenu directionality',
          'paste textcolor colorpicker textpattern codesample'
        ],
        initOptions: {
          elementpath: false,
          height: document.body.offsetHeight-200,
          menubar: 'edit insert view format table tools',
          toolbar1: 'preview | undo redo | insert | styleselect | forecolor backcolor bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image emoticons media codesample',
          autosave_interval: '10s',
          image_advtab: true,
          table_default_styles: {
            width: '100%',
            borderCollapse: 'collapse'
          }
        }
      };
    },
    computed:{ ...mapState({
      // 箭头函数可使代码更简练
      user: 'user'
    }),
      thanksLetter(){
      return{
        title:this.form.title,
        lostId:this.lostId,
        editorId:this.user.id,
        foundId:this.foundId,
        deleted:false,
        content: this.form.content,
        thanksId:this.thanksId,
        submitDatetime:new Date().getTime(),
      }
      },
    },
    methods: {
      editorInit:function (Editor) {
        this.spinShow=false
      },
      submitLetter(){
        //var that=this;
        this.$refs.form.validate((valid) => {
          if (valid) {
              this.$Modal.confirm({
                title: '确认提示',
                content: '<p>确定发送吗？</p>',
                onOk: () => {
                  this.spinShow=true
                  this.axios.post('/api/thanks/letter',
                    this.thanksLetter
                  ).then((res) => {
                    this.spinShow=false
                    if(res.data==1){
                      this.$router.go(-1);
                    }else{
                      this.$Message.error({
                            content: '保存失败！',
                            duration: 3,
                            closable: true
                          });
                    }
                  }).catch(function(err){
                    console.log(err)
                  })
                }
              });
          }
        });
        // if(this.content.trim().length<10){
        //   this.$Message.warning({
        //     content: '字数太少了，多写点吧！',
        //     duration: 3,
        //     closable: true
        //   });
        // }else{

        // }
      }
    },
    mounted() {
    }
  };
</script>

<style>

</style>
