<template>
  <div class="home">
      <Header/>
      <AddProduct/>
    <!--<HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->
    <Products/>
      <Coins/>
  </div>

</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Header from '@/components/layout/Header.vue'
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import Products from '@/components/Products.vue'
import AddProduct from '@/components/AddProduct.vue'
import Coins from '@/components/Coin.vue'
@Component({
  components: {
      Header,
    HelloWorld,
      Products,
      AddProduct,
      Coins,
  },
})
export default class Home extends Vue {}
</script>
