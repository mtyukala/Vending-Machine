<template>
  <div class="home">
      <Header/>

    <!--<HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->
    <Products />
      <Footer/>
  </div>

</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Header from '@/components/layout/Header.vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import Products from '@/components/Products.vue';
import Footer from '@/components/layout/Footer.vue';
import BootstrapVue from 'bootstrap-vue';
Vue.use(BootstrapVue);
@Component({
  components: {
      Header,
    HelloWorld,
      Products,
      Footer,
  },
})
export default class Home extends Vue {}
</script>

<style>
    *{
        margin: 0;
        padding: 0;
    }

    body, html
    {
        height: 100%;
    }
</style>
