<template>
  <div class="home">
      <Header/>

    <!--<HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->
    <Products v-bind:products="products"/>
  </div>

</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Header from '@/components/layout/Header.vue'
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import Products from '@/components/Products.vue'
import Coins from '@/components/Coin.vue'
import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue)
@Component({
  components: {
      Header,
    HelloWorld,
      Products,
  },
})
export default class Home extends Vue {}
</script>
