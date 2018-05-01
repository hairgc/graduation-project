<template>
  <div style="background: aliceblue">
    <Row>
      <Col span="12">
        <Slider v-model="value1"></Slider>
        <Slider v-model="value2" range></Slider>
        <Slider v-model="value3" range disabled></Slider>
      </Col>
      <Col span="12"><Button>Default</Button>
        <Button type="primary">Primary</Button>
        <Button type="ghost">Ghost</Button>
        <Button type="dashed">Dashed</Button>
        <Button type="text">Text</Button>
        <br><br>
        <Button type="info">Info</Button>
        <Button type="success">Success</Button>
        <Button type="warning">Warning</Button>
        <Button type="error">Error</Button>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="8">
        <Button type="primary" @click="info">Display info prompt</Button>
      </Col>
      <Col span="8">
        <Button type="primary" @click="handleSpinShow">整页显示，3秒后关闭</Button>
      </Col>
      <Col span="8">col-8</Col>
    </Row>
    <br>
    <Row>
      <Col span="6">col-6</Col>
      <Col span="6">col-6</Col>
      <Col span="6">col-6</Col>
      <Col span="6">col-6</Col>
    </Row>
  </div>
</template>
<script>
    export default {
        data () {
            return {
                value1: 25,
                value2: [20, 50],
                value3: [20, 50]
            }
        },
        methods: {
            info () {
                this.$Message.info('This is a info tip');
            },
            handleSpinShow () {
                this.$Spin.show();
                setTimeout(() => {
                    this.$Spin.hide();
                }, 3000);
            },
        }
    }
</script>

